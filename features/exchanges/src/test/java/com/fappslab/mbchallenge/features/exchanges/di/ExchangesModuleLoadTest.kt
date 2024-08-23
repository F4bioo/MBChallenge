package com.fappslab.mbchallenge.features.exchanges.di

import com.fappslab.mbchallenge.features.exchanges.domain.repository.ExchangesRepository
import com.fappslab.mbchallenge.libraries.testing.rules.KoinModuleTest
import com.fappslab.mbchallenge.libraries.testing.rules.MainCoroutineTestRule
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module

internal class ExchangesModuleLoadTest : KoinModuleTest(ExchangesModuleLoad) {

    @get:Rule
    override val coroutineTestRule: MainCoroutineTestRule = MainCoroutineTestRule()

    override val mockedModules = module {
        factory<ExchangesRepository> { mockk(relaxed = true) }
    }

    @Test
    fun `checkModules Should Koin provides dependencies When invoke ExchangesModuleLoad`() {
        checkModules()
    }
}
