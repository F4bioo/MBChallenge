package com.fappslab.mbchallenge.core.data.local.source

import com.fappslab.mbchallenge.core.domain.model.Exchange

interface LocalDataSource {
    suspend fun selectExchange(exchangeId: String): Exchange
    suspend fun insertAllExchanges(exchanges: List<Exchange>)
    suspend fun selectAllExchanges(): List<Exchange>
}
