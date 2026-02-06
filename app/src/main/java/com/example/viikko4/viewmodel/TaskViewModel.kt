package com.example.viikko4.viewmodel

import androidx.lifecycle.ViewModel
import com.example.viikko4.domain.Task
import com.example.viikko4.domain.mockTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow(mockTasks)
    val tasks: StateFlow<List<Task>> = _tasks

    fun addTask(task: Task) {
        _tasks.update { it + task }
    }

    fun toggleDone(id: Int) {
        _tasks.update { list ->
            list.map {
                if (it.id == id) it.copy(done = !it.done) else it
            }
        }
    }

    fun removeTask(id: Int) {
        _tasks.update { it.filter { task -> task.id != id } }
    }

    fun updateTask(updated: Task) {
        _tasks.update { list ->
            list.map {
                if (it.id == updated.id) updated else it
            }
        }
    }
}