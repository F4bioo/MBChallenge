package com.fappslab.mbchallenge.features.exchanges.domain.usecase

import com.fappslab.mbchallenge.features.exchanges.domain.repository.ExchangesRepository
import com.fappslab.mbchallenge.libraries.testing.stub.exchangesStub
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

internal class GetExchangesUseCaseTest {

    private val repository: ExchangesRepository = mockk()
    private val subject = GetExchangesUseCase(repository)

    @Test
    fun `When getExchanges is invoked Then should return exchanges list`() =
        runTest {
            // Given
            val expectedResult = exchangesStub()
            coEvery { repository.getExchanges() } returns expectedResult

            // When
            val result = subject()

            // Then
            assertEquals(expectedResult, result)
            coVerify { repository.getExchanges() }
        }

    @Test
    fun `When getExchanges is invoked Then should return failure`() =
        runTest {
            // Given
            val expectedMessage = "Test exception"
            val cause = Throwable(expectedMessage)
            coEvery { repository.getExchanges() } throws cause

            // Then
            val result = assertFailsWith<Throwable> { subject() }
            assertEquals(expectedMessage, result.message)
            coVerify { repository.getExchanges() }
        }
}
