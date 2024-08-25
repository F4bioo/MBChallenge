package com.fappslab.mbchallenge.core.domain.repository

import com.fappslab.mbchallenge.core.domain.model.Exchange

interface MBChallengeRepository {
    suspend fun selectExchange(exchangeId: String): Exchange
    suspend fun insertAllExchanges(exchanges: List<Exchange>)
    suspend fun selectAllExchanges(): List<Exchange>
}
