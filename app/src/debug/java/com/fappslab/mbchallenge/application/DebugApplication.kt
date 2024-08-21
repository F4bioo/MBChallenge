package com.fappslab.mbchallenge.application

import com.fappslab.mbchallenge.debugtools.FlipperSetup
import timber.log.Timber

class DebugApplication : MainApplication() {

    override fun onCreate() {
        super.onCreate()
        FlipperSetup.start()
        Timber.plant(Timber.DebugTree())
    }
}
