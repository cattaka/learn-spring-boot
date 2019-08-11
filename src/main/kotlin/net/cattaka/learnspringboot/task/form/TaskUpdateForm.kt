package net.cattaka.learnspringboot.task.form

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class TaskUpdateForm(
    @field:NotBlank
    @field:Size(max = 20)
    var content: String? = null,
    var done: Boolean = false
)
