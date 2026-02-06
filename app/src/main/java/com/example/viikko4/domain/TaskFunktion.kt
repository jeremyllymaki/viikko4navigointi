package com.example.viikko4.domain

fun addTask(taskList: List<Task>, newTask: Task): List<Task> {
    return taskList + newTask
}

fun toggleDone(list: List<Task>, id: Int): List<Task> {
    return list.map { task ->
        if (task.id == id) {
            task.copy(done = !task.done)
        } else {
            task
        }
    }
}

fun removeTask(list: List<Task>, id: Int): List<Task> {
    return list.filter { it.id != id }
}

fun filterByDone(list: List<Task>, done: Boolean): List<Task> {
    return list.filter { it.done == done }
}

fun sortByDueDate(list: List<Task>): List<Task> {
    return list.sortedBy { it.dueDate }
}