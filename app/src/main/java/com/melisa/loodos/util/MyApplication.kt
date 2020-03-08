package com.melisa.loodos.util


import androidx.multidex.MultiDexApplication
import com.melisa.loodos.di.appModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}