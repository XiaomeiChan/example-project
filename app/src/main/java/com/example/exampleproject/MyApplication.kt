package com.example.exampleproject

import android.app.Application
import com.example.exampleproject.di.localModule
import com.example.exampleproject.di.repositoryModule
import com.example.exampleproject.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin{
            androidContext(this@MyApplication)
            modules (
                 viewModule, repositoryModule, localModule
            )
        }
    }
}


