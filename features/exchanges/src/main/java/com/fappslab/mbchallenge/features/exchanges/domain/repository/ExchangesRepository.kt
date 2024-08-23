package com.fappslab.mbchallenge.features.exchanges.domain.repository

import com.fappslab.mbchallenge.features.exchanges.domain.model.Exchange

internal interface ExchangesRepository {
    suspend fun getExchanges(): List<Exchange>
}
