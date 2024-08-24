package com.fappslab.mbchallenge.core.domain.usecase

import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository
import com.fappslab.mbchallenge.core.domain.stub.exchangeStub
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

internal class SelectExchangeUseCaseTest {

    private val repository: MBChallengeRepository = mockk()
    private val subject = SelectExchangeUseCase(repository)

    @Test
    fun `When selectExchange is invoked Then should return success`() =
        runTest {
            // Given
            val exchangeId = "BINANCE"
            val expectedExchange = exchangeStub()
            coEvery { repository.selectExchange(exchangeId) } returns expectedExchange

            // When
            val result = subject(exchangeId)

            // Then
            assertEquals(expectedExchange, result)
            coVerify { repository.selectExchange(exchangeId) }
        }

    @Test
    fun `When selectExchange is invoked Then should return failure`() =
        runTest {
            // Given
            val exchangeId = "BINANCE"
            val expectedMessage = "Test exception"
            val cause = Throwable(expectedMessage)
            coEvery { repository.selectExchange(exchangeId) } throws cause

            // Then
            val result = assertFailsWith<Throwable> { subject(exchangeId) }
            assertEquals(expectedMessage, result.message)
            coVerify { repository.selectExchange(exchangeId) }
        }
}
