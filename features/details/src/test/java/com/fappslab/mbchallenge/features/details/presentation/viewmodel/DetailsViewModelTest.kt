package com.fappslab.mbchallenge.features.details.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.fappslab.mbchallenge.core.domain.usecase.SelectExchangeUseCase
import com.fappslab.mbchallenge.libraries.testing.rules.MainCoroutineTestRule
import com.fappslab.mbchallenge.libraries.testing.stub.exchangeStub
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class DetailsViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val exchangeId = "BINANCE"
    private val initialState = DetailsViewState(exchangeId)
    private val selectExchangeUseCase = mockk<SelectExchangeUseCase>(relaxed = true)
    private lateinit var subject: DetailsViewModel

    @Before
    fun setUp() {
        subject = DetailsViewModel(
            exchangeId = exchangeId,
            selectExchangeUseCase = selectExchangeUseCase,
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `When onViewIntent is invoked with OnInitView Then should expose expected state`() =
        runTest {
            // Given
            val exchange = exchangeStub()
            coEvery { selectExchangeUseCase(exchangeId) } returns exchange
            val expectedState = initialState.copy(exchange = exchange)

            // When
            subject.onViewIntent(DetailsViewIntent.OnInitView)

            // Then
            subject.state.test {
                assertEquals(initialState, awaitItem())
                assertEquals(expectedState, awaitItem())
                cancelAndConsumeRemainingEvents()
            }
            coVerify { selectExchangeUseCase(exchangeId) }
        }

    @Test
    fun `When onViewIntent is invoked with OnNavigateToWebsite Then should expose expected effect`() =
        runTest {
            // Given
            val url = "https://www.binance.com/"
            val expectedEffect = DetailsViewEffect.NavigateToWebsite(url)

            // When
            subject.onViewIntent(DetailsViewIntent.OnNavigateToWebsite(url))

            // Then
            subject.effect.test {
                assertEquals(expectedEffect, awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `When onViewIntent is invoked with OnBackClicked Then should expose expected effect`() =
        runTest {
            // Given
            val expectedEffect = DetailsViewEffect.NavigateToExchanges

            // When
            subject.onViewIntent(DetailsViewIntent.OnBackClicked)

            // Then
            subject.effect.test {
                assertEquals(expectedEffect, awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }
}
