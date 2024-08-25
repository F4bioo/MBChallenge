package com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.fappslab.mbchallenge.core.data.remote.model.ErrorType
import com.fappslab.mbchallenge.core.data.remote.network.exception.model.HttpThrowable
import com.fappslab.mbchallenge.core.domain.usecase.InsertAllExchangesUseCase
import com.fappslab.mbchallenge.core.domain.usecase.SelectAllExchangesUseCase
import com.fappslab.mbchallenge.features.exchanges.domain.usecase.GetExchangesUseCase
import com.fappslab.mbchallenge.libraries.testing.rules.MainCoroutineTestRule
import com.fappslab.mbchallenge.libraries.testing.stub.exchangesStub
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

internal class ExchangesViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val initialState = ExchangesViewState()
    private val insertAllExchangesUseCase = mockk<InsertAllExchangesUseCase>(relaxed = true)
    private val selectAllExchangesUseCase = mockk<SelectAllExchangesUseCase>(relaxed = true)
    private val getExchangesUseCase = mockk<GetExchangesUseCase>(relaxed = true)
    private lateinit var subject: ExchangesViewModel

    @Before
    fun setUp() {
        subject = ExchangesViewModel(
            insertAllExchangesUseCase = insertAllExchangesUseCase,
            selectAllExchangesUseCase = selectAllExchangesUseCase,
            getExchangesUseCase = getExchangesUseCase,
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `When onViewIntent is invoked with OnCLoseErrorModal Then should expose expected state`() =
        runTest {
            // Given
            val expectedState = initialState.copy(shouldShowError = false)

            // When
            subject.onViewIntent(ExchangesViewIntent.OnCLoseErrorModal)

            // Then
            subject.state.test {
                assertEquals(expectedState, awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `When onViewIntent is invoked with OnExchangeClicked Then should expose expected state`() =
        runTest {
            // Given
            val exchangeId = "MERCADOBITCOIN"
            val expectedEffect = ExchangesViewEffect.NavigateToDetails(exchangeId)

            // When
            subject.onViewIntent(ExchangesViewIntent.OnExchangeClicked(exchangeId))

            // Then
            subject.effect.test {
                assertEquals(expectedEffect, awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `When onViewIntent is invoked with OnGetExchanges Then should expose expected success state`() =
        runTest {
            // Given
            val exchanges = exchangesStub()
            val expectedFirstState = initialState.copy(shouldShowLoading = true)
            val expectedSecondState = expectedFirstState.copy(exchanges = exchanges)
            val expectedFinalState = expectedSecondState.copy(shouldShowLoading = false)
            coEvery { getExchangesUseCase() } returns exchanges

            // When
            subject.onViewIntent(ExchangesViewIntent.OnGetExchanges)

            // Then
            subject.state.test {
                assertEquals(initialState, awaitItem())
                assertEquals(expectedFirstState, awaitItem())
                assertEquals(expectedSecondState, awaitItem())
                assertEquals(expectedFinalState, awaitItem())
                cancelAndConsumeRemainingEvents()
            }
            coVerify { getExchangesUseCase() }
        }

    @Test
    fun `When onViewIntent is invoked with OnGetExchanges Then should expose expected failure state with BadRequest type`() =
        runTest { commonErrorScenarios(errorType = ErrorType.BadRequest) }

    @Test
    fun `When onViewIntent is invoked with OnGetExchanges Then should expose expected failure state with Unauthorized type`() =
        runTest { commonErrorScenarios(errorType = ErrorType.Unauthorized) }

    @Test
    fun `When onViewIntent is invoked with OnGetExchanges Then should expose expected failure state with Forbidden type`() =
        runTest { commonErrorScenarios(errorType = ErrorType.Forbidden) }

    @Test
    fun `When onViewIntent is invoked with OnGetExchanges Then should expose expected failure state with TooManyRequests type`() =
        runTest { commonErrorScenarios(errorType = ErrorType.TooManyRequests) }

    @Test
    fun `When onViewIntent is invoked with OnGetExchanges Then should expose expected failure state with NoData type`() =
        runTest { commonErrorScenarios(errorType = ErrorType.NoData) }

    @Test
    fun `When onViewIntent is invoked with OnGetExchanges Then should expose expected failure state with Generic type`() =
        runTest { commonErrorScenarios(errorType = ErrorType.Generic) }

    @Test
    fun `When onViewIntent is invoked with OnGetExchanges Then should expose expected failure state with Internet type`() =
        runTest { commonErrorScenarios(errorType = ErrorType.Internet) }

    private suspend fun commonErrorScenarios(errorType: ErrorType) {
        // Given
        val exchanges = exchangesStub()
        val expectedFirstState = initialState.copy(shouldShowLoading = true)
        val expectedSecondState = expectedFirstState.copy(exchanges = exchanges)
        val expectedThirdState = expectedSecondState
            .copy(errorType = errorType, shouldShowError = true)
        val expectedFinalState = expectedThirdState.copy(shouldShowLoading = false)
        coEvery { insertAllExchangesUseCase(exchanges) } just Runs
        coEvery { selectAllExchangesUseCase() } returns exchanges
        coEvery { getExchangesUseCase() } throws HttpThrowable(errorType)

        // When
        subject.onViewIntent(ExchangesViewIntent.OnGetExchanges)

        // Then
        subject.state.test {
            assertEquals(initialState, awaitItem())
            assertEquals(expectedFirstState, awaitItem())
            assertEquals(expectedSecondState, awaitItem())
            assertEquals(expectedThirdState, awaitItem())
            assertEquals(expectedFinalState, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
        coVerify { getExchangesUseCase() }
    }
}
