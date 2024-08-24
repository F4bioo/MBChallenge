package com.fappslab.mbchallenge.core.data.local.source

import com.fappslab.mbchallenge.core.data.local.dao.ExchangesDao
import com.fappslab.mbchallenge.core.data.local.model.extension.toExchange
import com.fappslab.mbchallenge.core.data.local.model.extension.toExchangeList
import com.fappslab.mbchallenge.core.data.local.model.extension.toExchangeListEntity
import com.fappslab.mbchallenge.core.domain.model.Exchange
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class LocalDataSourceImpl(
    private val dao: ExchangesDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalDataSource {

    override suspend fun selectExchange(exchangeId: String): Exchange {
        return dao.selectExchange(exchangeId).toExchange()
    }

    override suspend fun insertAllExchanges(exchanges: List<Exchange>) {
        withContext(dispatcher) {
            dao.insertAllExchanges(exchanges.toExchangeListEntity())
        }
    }

    override suspend fun selectAllExchanges(): List<Exchange> {
        return withContext(dispatcher) {
            dao.selectAllExchanges().toExchangeList()
        }
    }
}
