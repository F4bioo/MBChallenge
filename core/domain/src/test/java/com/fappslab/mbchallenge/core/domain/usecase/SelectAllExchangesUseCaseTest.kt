package com.fappslab.mbchallenge.core.domain.usecase

import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository
import com.fappslab.mbchallenge.core.domain.stub.exchangesStub
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

internal class SelectAllExchangesUseCaseTest {

    private val repository: MBChallengeRepository = mockk()
    private val subject = SelectAllExchangesUseCase(repository)

    @Test
    fun `When selectAllExchanges is invoked Then should return success`() =
        runTest {
            // Given
            val expectedExchanges = exchangesStub()
            coEvery { repository.selectAllExchanges() } returns expectedExchanges

            // When
            val result = subject()

            // Then
            assertEquals(expectedExchanges, result)
            coVerify { repository.selectAllExchanges() }
        }

    @Test
    fun `When selectAllExchanges is invoked Then should return failure`() =
        runTest {
            // Given
            val expectedMessage = "Test exception"
            val cause = Throwable(expectedMessage)
            coEvery { repository.selectAllExchanges() } throws cause

            // Then
            val result = assertFailsWith<Throwable> { subject() }
            assertEquals(expectedMessage, result.message)
            coVerify { repository.selectAllExchanges() }
        }
}
