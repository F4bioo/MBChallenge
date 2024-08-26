package com.fappslab.mbchallenge.core.domain.usecase

import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository
import com.fappslab.mbchallenge.core.domain.stub.exchangesStub
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

internal class InsertAllExchangesUseCaseTest {

    private val repository: MBChallengeRepository = mockk()
    private val subject = InsertAllExchangesUseCase(repository)

    @Test
    fun `When insertAllExchanges is invoked Then should return success`() =
        runTest {
            // Given
            val exchanges = exchangesStub()
            coEvery { repository.insertAllExchanges(exchanges) } just Runs

            // When
            subject(exchanges)

            // Then
            coVerify { repository.insertAllExchanges(exchanges) }
        }

    @Test
    fun `When insertAllExchanges is invoked Then should return failure`() =
        runTest {
            // Given
            val expectedMessage = "Test exception"
            val exchanges = exchangesStub()
            val cause = Throwable(expectedMessage)
            coEvery { repository.insertAllExchanges(exchanges) } throws cause

            // When
            val result = assertFailsWith<Throwable> { subject(exchanges) }

            // Then
            assertEquals(expectedMessage, result.message)
            coVerify { repository.insertAllExchanges(exchanges) }
        }
}
