package com.example.memeitupapp

import android.app.Application
import com.example.memeitupapp.di.modelsModule
import com.example.memeitupapp.di.viewModelsModule
import com.globant.di.servicesModule
import com.globant.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(servicesModule, viewModelsModule, modelsModule, useCasesModule))
        }
    }
}