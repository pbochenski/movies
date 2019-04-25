package com.netguru.testmovies

import android.app.Application
import com.netguru.testmovies.di.restModule
import com.netguru.testmovies.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(viewModelsModule, restModule)
        }
    }
}