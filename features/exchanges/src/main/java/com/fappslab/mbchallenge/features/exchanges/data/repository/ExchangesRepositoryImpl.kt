package com.fappslab.mbchallenge.features.exchanges.data.repository

import com.fappslab.mbchallenge.features.exchanges.data.source.ExchangesRemoteDataSource
import com.fappslab.mbchallenge.features.exchanges.domain.model.Exchange
import com.fappslab.mbchallenge.features.exchanges.domain.repository.ExchangesRepository

internal class ExchangesRepositoryImpl(
    private val dataSource: ExchangesRemoteDataSource
) : ExchangesRepository {

    override suspend fun getExchanges(): List<Exchange> {
        return dataSource.getExchanges()
    }
}
