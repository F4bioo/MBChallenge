package com.fappslab.mbchallenge.features.exchanges.data.repository

import com.fappslab.mbchallenge.core.data.remote.model.ErrorType
import com.fappslab.mbchallenge.core.data.remote.network.exception.model.HttpThrowable
import com.fappslab.mbchallenge.features.exchanges.data.source.ExchangesRemoteDataSourceImpl
import com.fappslab.mbchallenge.features.exchanges.domain.repository.ExchangesRepository
import com.fappslab.mbchallenge.libraries.testing.extension.readJsonFile
import com.fappslab.mbchallenge.libraries.testing.rules.MainCoroutineTestRule
import com.fappslab.mbchallenge.libraries.testing.rules.RemoteTestRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

private const val SUCCESS_EXCHANGE_RESPONSE = "coin_api_exchanges_success_response.json"
private const val SUCCESS_ICONS_RESPONSE = "coin_api_icons_success_response.json"

internal class ExchangesRepositoryImplIntegrationTest {

    @get:Rule
    val remoteTestRule = RemoteTestRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineTestRule()

    private lateinit var subject: ExchangesRepository

    @Before
    fun setUp() {
        subject = ExchangesRepositoryImpl(
            dataSource = ExchangesRemoteDataSourceImpl(
                service = remoteTestRule.createTestService(),
                dispatcher = mainCoroutineRule.testDispatcher
            )
        )
    }

    @Test
    fun `When getExchanges is invoked Then should return success response`() = runTest {
        // Given
        val expectedSize = 2
        val expectedExchangeId = "MERCADOBITCOIN"
        remoteTestRule.enqueueResponse(SUCCESS_EXCHANGE_RESPONSE.readJsonFile(), code = 200)
        remoteTestRule.enqueueResponse(SUCCESS_ICONS_RESPONSE.readJsonFile(), code = 200)

        // When
        val result = subject.getExchanges()

        // Then
        assertEquals(expectedSize, result.size)
        assertEquals(expectedExchangeId, result.first().exchangeId)
    }

    @Test
    fun `When getExchanges is invoked Then Should return BadRequest error type`() = runTest {
        // Given
        val expectedErrorType = ErrorType.BadRequest

        // When
        val result = commonErrorScenarios(code = 400)

        // Then
        assertEquals(expectedErrorType, result.errorType)
    }

    @Test
    fun `When getExchanges is invoked Then Should return Unauthorized error type`() = runTest {
        // Given
        val expectedErrorType = ErrorType.Unauthorized

        // When
        val result = commonErrorScenarios(code = 401)

        // Then
        assertEquals(expectedErrorType, result.errorType)
    }

    @Test
    fun `When getExchanges is invoked Then Should return Forbidden error type`() = runTest {
        // Given
        val expectedErrorType = ErrorType.Forbidden

        // When
        val result = commonErrorScenarios(code = 403)

        // Then
        assertEquals(expectedErrorType, result.errorType)
    }

    @Test
    fun `When getExchanges is invoked Then Should return TooManyRequests error type`() = runTest {
        // Given
        val expectedErrorType = ErrorType.TooManyRequests

        // When
        val result = commonErrorScenarios(code = 429)

        // Then
        assertEquals(expectedErrorType, result.errorType)
    }

    @Test
    fun `When getExchanges is invoked Then Should return NoData error type`() = runTest {
        // Given
        val expectedErrorType = ErrorType.NoData

        // When
        val result = commonErrorScenarios(code = 550)

        // Then
        assertEquals(expectedErrorType, result.errorType)
    }

    @Test
    fun `When getExchanges is invoked Then Should return Generic error type`() = runTest {
        // Given
        val expectedErrorType = ErrorType.Generic

        // When
        val result = commonErrorScenarios(code = 500)

        // Then
        assertEquals(expectedErrorType, result.errorType)
    }

    @Test
    fun `When getExchanges is invoked Then Should return Internet error type`() = runTest {
        // Given
        val expectedErrorType = ErrorType.Internet
        val exception = listOf(UnknownHostException(), SocketTimeoutException(), IOException())
        remoteTestRule.simulateException(exception.random())

        // When
        val result = assertFailsWith<HttpThrowable> { subject.getExchanges() }

        // Then
        assertEquals(expectedErrorType, result.errorType)
    }

    private suspend fun commonErrorScenarios(code: Int): HttpThrowable {
        remoteTestRule.enqueueResponse(SUCCESS_EXCHANGE_RESPONSE.readJsonFile(), code)
        remoteTestRule.enqueueResponse(SUCCESS_ICONS_RESPONSE.readJsonFile(), code)
        return assertFailsWith<HttpThrowable> { subject.getExchanges() }
    }
}
