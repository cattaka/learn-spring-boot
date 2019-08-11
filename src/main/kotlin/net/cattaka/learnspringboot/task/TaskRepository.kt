package net.cattaka.learnspringboot.task

import net.cattaka.learnspringboot.data.Task

interface TaskRepository {
    fun create(content: String): Task

    fun update(task:Task)

    fun findAll(): List<Task>

    fun findById(id: Long): Task?
}