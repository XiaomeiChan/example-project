package com.example.exampleproject.ui.add

import androidx.lifecycle.ViewModel
import com.example.exampleproject.database.Task
import com.example.exampleproject.repository.TaskRepository

class AddTaskViewModel(private val repository : TaskRepository): ViewModel() {
    suspend fun newTask(task : Task) = repository.insertTask(task)
}