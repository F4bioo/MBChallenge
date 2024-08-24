package com.fappslab.mbchallenge.core.data.local.repository

import com.fappslab.mbchallenge.core.data.local.source.LocalDataSource
import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository

internal class LocalRepositoryImpl(
    private val dataSource: LocalDataSource,
) : MBChallengeRepository {

    override suspend fun selectExchange(exchangeId: String): Exchange {
        return dataSource.selectExchange(exchangeId)
    }

    override suspend fun insertAllExchanges(exchanges: List<Exchange>) {
        dataSource.insertAllExchanges(exchanges)
    }

    override suspend fun selectAllExchanges(): List<Exchange> {
        return dataSource.selectAllExchanges()
    }
}
