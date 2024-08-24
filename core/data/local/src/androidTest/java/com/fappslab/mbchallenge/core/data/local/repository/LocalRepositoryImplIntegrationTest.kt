package com.fappslab.mbchallenge.core.data.local.repository

import com.fappslab.mbchallenge.core.data.local.dao.ExchangesDao
import com.fappslab.mbchallenge.core.data.local.database.room.MBChallengeDatabase
import com.fappslab.mbchallenge.core.data.local.model.extension.toExchangeList
import com.fappslab.mbchallenge.core.data.local.model.extension.toExchangeListEntity
import com.fappslab.mbchallenge.core.data.local.source.LocalDataSourceImpl
import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository
import com.fappslab.mbchallenge.libraries.testing.rules.LocalTestRule
import com.fappslab.mbchallenge.libraries.testing.rules.MainCoroutineTestRule
import com.fappslab.mbchallenge.libraries.testing.stub.exchangeStub
import com.fappslab.mbchallenge.libraries.testing.stub.exchangesStub
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

internal class LocalRepositoryImplIntegrationTest {

    @get:Rule
    val localTestRule = LocalTestRule()

    @get:Rule
    val mainCoroutineTestRule = MainCoroutineTestRule()

    private lateinit var dao: ExchangesDao
    private lateinit var subject: MBChallengeRepository

    @Before
    fun setUp() {
        val database = localTestRule
            .createTestDatabase<MBChallengeDatabase>()
        dao = database.exchangesDao()

        subject = LocalRepositoryImpl(
            dataSource = LocalDataSourceImpl(
                dao = dao
            )
        )
    }

    @Test
    fun when_selectExchange_is_invoked_THen_should_return_exchange() = runTest {
        // Given
        val exchangeId = "BINANCE"
        val exchanges = exchangesStub()
        val exchangeEntity = exchangeStub()
        dao.insertAllExchanges(exchanges.toExchangeListEntity())

        // When
        val result = subject.selectExchange(exchangeId)

        // Then
        assertEquals(exchangeEntity, result)
    }

    @Test
    fun when_insertAllExchanges_is_invoked_THen_should_insert_all_exchanges() = runTest {
        // Given
        val expectedResult = exchangesStub()

        // When
        subject.insertAllExchanges(expectedResult)

        // Then
        val result = dao.selectAllExchanges()
        assertEquals(expectedResult, result.toExchangeList())
    }

    @Test
    fun when_selectAllExchanges_is_invoked_THen_should_return_all_exchanges() = runTest {
        // Given
        val expectedResult = exchangesStub()
        dao.insertAllExchanges(expectedResult.toExchangeListEntity())

        // When
        val result = subject.selectAllExchanges()

        // Then
        assertEquals(expectedResult, result)
    }
}
