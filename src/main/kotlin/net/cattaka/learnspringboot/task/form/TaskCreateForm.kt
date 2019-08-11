package net.cattaka.learnspringboot.task.form

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class TaskCreateForm(
        @field:NotBlank
        @field:Size(max = 20)
        var content : String? = null
)
