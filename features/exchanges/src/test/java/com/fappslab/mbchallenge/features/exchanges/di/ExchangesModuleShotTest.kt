package com.fappslab.mbchallenge.features.exchanges.di

import com.fappslab.mbchallenge.libraries.testing.rules.KoinModuleTest
import com.fappslab.mbchallenge.libraries.testing.rules.MainCoroutineTestRule
import org.junit.Rule
import org.junit.Test

internal class ExchangesModuleShotTest : KoinModuleTest(ExchangesModuleShot()) {

    @get:Rule
    override val coroutineTestRule: MainCoroutineTestRule = MainCoroutineTestRule()

    @Test
    fun `checkModules Should Koin provides dependencies When invoke ExchangesModuleShot`() {
        checkModules()
    }
}
