package net.cattaka.learnspringboot.task

import net.cattaka.learnspringboot.data.Task
import net.cattaka.learnspringboot.exception.NotFoundException
import net.cattaka.learnspringboot.task.form.TaskCreateForm
import net.cattaka.learnspringboot.task.form.TaskUpdateForm
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("tasks")
class TaskController(private val taskRepository: TaskRepository) {
    @GetMapping("")
    fun index(model: Model): String {
        val tasks = taskRepository.findAll()
        model.addAttribute("tasks", tasks)
        return "tasks/index"
    }

    @PostMapping("")
    fun create(@Validated form: TaskCreateForm, bindingResult: BindingResult) : String {
        if (bindingResult.hasErrors()) {
            return "tasks/new"
        }

        val content = requireNotNull(form.content)
        taskRepository. create(content)
        return "redirect:/tasks"
    }

    @GetMapping("new")
    fun new(form: TaskCreateForm): String {
        return "tasks/new"
    }

    @GetMapping("{id}/edit")
    fun edit(@PathVariable("id") id:Long,
             form:TaskUpdateForm) : String{
        val task = taskRepository.findById(id) ?: throw NotFoundException()
        form.content = task.content
        form.done = task.done
        return "tasks/edit"
    }

    @PatchMapping("{id}")
    fun update(@PathVariable("id") id:Long,
            @Validated form: TaskUpdateForm,
            bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "tasks/edit"
        }

        val task = taskRepository.findById(id) ?: throw NotFoundException()
        val newTask = task.copy(
                content = requireNotNull(form.content),
                done =  requireNotNull(form.done)
        )
        taskRepository.update(newTask)
        return "redirect:/tasks"
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException() = "tasks/not_found"
}
