package com.fappslab.mbchallenge.features.exchanges.presentation.setupkoin

import androidx.test.core.app.ApplicationProvider
import com.fappslab.mbchallenge.features.exchanges.di.ExchangesModuleLoad
import com.fappslab.mbchallenge.features.exchanges.di.ExchangesModuleShot
import org.junit.AfterClass
import org.junit.BeforeClass
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.module.Module

internal open class KoinTestBase {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            startKoin {
                androidContext(ApplicationProvider.getApplicationContext())
                modules(provideTestModules())
            }
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            stopKoin()
        }

        private fun provideTestModules(): List<Module> {
            return ExchangesModuleLoad.modules + ExchangesModuleShot().modules
        }
    }
}
