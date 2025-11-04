package com.subhamkumar.boxboxapp

import android.app.Application
import com.subhamkumar.boxboxapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class F1App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@F1App)
            modules(appModule)
        }
    }
}