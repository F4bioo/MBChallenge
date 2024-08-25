package com.fappslab.mbchallenge.core.data.local.di

import android.app.Application
import com.fappslab.mbchallenge.libraries.testing.rules.KoinModuleTest
import com.fappslab.mbchallenge.libraries.testing.rules.MainCoroutineTestRule
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module

internal class LocalModuleShotTest : KoinModuleTest(LocalModuleShot()) {

    @get:Rule
    override val coroutineTestRule: MainCoroutineTestRule = MainCoroutineTestRule()

    override val mockedModules = module {
        factory<Application> { mockk(relaxed = true) }
    }

    @Test
    fun `checkModules Should Koin provides dependencies When invoke LocalModuleShot`() {
        checkModules()
    }
}
