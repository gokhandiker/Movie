package com.melisa.loodos.util

import android.app.Application
import com.melisa.loodos.di.appModule
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}