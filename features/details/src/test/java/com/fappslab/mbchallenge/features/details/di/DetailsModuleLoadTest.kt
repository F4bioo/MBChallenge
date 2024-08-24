package com.fappslab.mbchallenge.features.details.di

import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository
import com.fappslab.mbchallenge.features.details.presentation.viewmodel.DetailsViewModel
import com.fappslab.mbchallenge.libraries.testing.rules.KoinModuleTest
import com.fappslab.mbchallenge.libraries.testing.rules.MainCoroutineTestRule
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

internal class DetailsModuleLoadTest : KoinModuleTest(DetailsModuleLoad) {

    @get:Rule
    override val coroutineTestRule: MainCoroutineTestRule = MainCoroutineTestRule()

    override val mockedModules = module {
        factory<MBChallengeRepository> { mockk(relaxed = true) }
    }

    @Test
    fun `checkModules Should Koin provides dependencies When invoke DetailsModuleLoad`() {
        val exchangeId = "BINANCE"
        checkModules { withParameters<DetailsViewModel> { parametersOf(exchangeId) } }
    }
}
