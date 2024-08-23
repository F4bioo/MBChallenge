package com.fappslab.mbchallenge.features.exchanges.data.source

import com.fappslab.mbchallenge.features.exchanges.domain.model.Exchange


internal interface ExchangesRemoteDataSource {
    suspend fun getExchanges(): List<Exchange>
}
