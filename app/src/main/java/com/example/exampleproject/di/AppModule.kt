package com.example.exampleproject.di

import androidx.room.Room
import com.example.exampleproject.database.TaskDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    factory { get<TaskDatabase>().taskDao() }
    single {
        Room.databaseBuilder(
            androidContext().applicationContext,
            TaskDatabase::class.java, "App.db"
        ).build()
    }
}
