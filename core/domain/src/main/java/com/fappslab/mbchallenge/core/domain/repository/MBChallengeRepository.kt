package com.fappslab.mbchallenge.core.domain.repository

import com.fappslab.mbchallenge.core.domain.model.Exchange

interface MBChallengeRepository {
    suspend fun getExchanges(): List<Exchange>
}
