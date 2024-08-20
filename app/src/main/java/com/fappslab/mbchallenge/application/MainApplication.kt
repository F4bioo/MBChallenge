package com.fappslab.mbchallenge.application

import android.app.Application
import android.content.Context
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

open class MainApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }

    private fun startKoin() {
        startKoin(KoinDeclaration.get(this))
        koinLoad()
    }

    private fun koinLoad() {

    }
}
