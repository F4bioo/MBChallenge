package com.fappslab.mbchallenge.features.details.di

import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository
import com.fappslab.mbchallenge.features.details.presentation.viewmodel.DetailsViewModel
import io.mockk.mockk
import org.junit.After
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import kotlin.test.Test

internal class DetailsModuleLoadTest2 : KoinTest {

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should check Koin modules with correct parameter type`() {
        val exchangeId = "BINANCE"

        val mockedModules = module {
            factory<MBChallengeRepository> { mockk(relaxed = true) }
        }

        startKoin {
            modules(DetailsModuleLoad.modules + mockedModules)
        }.checkModules {
            withParameters<DetailsViewModel> { parametersOf(exchangeId) }
        }
    }
}
