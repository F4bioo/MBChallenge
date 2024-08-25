package com.fappslab.mbchallenge.features.exchanges.data.source

import com.fappslab.mbchallenge.core.domain.model.Exchange


internal interface ExchangesRemoteDataSource {
    suspend fun getExchanges(): List<Exchange>
}
