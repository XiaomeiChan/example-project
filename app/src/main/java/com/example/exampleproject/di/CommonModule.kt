package com.example.exampleproject.di

import com.example.exampleproject.ui.list.TaskViewModel
import com.example.exampleproject.repository.TaskRepository
import com.example.exampleproject.ui.add.AddTaskViewModel
import com.example.exampleproject.ui.detail.DetailTaskViewModel
import org.koin.dsl.module


val repositoryModule = module {
    single { TaskRepository(get()) }
}

val viewModule = module {
    single { DetailTaskViewModel(get()) }
    single { AddTaskViewModel(get()) }
    single { TaskViewModel(get()) }
}