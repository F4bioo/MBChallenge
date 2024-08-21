package com.fappslab.mbchallenge.core.domain.repository

import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.core.domain.model.NetworkStateType
import kotlinx.coroutines.flow.Flow

interface MBChallengeRepository {
    fun watchNetworkState(): Flow<NetworkStateType>

    suspend fun getExchanges(): List<Exchange>
}
