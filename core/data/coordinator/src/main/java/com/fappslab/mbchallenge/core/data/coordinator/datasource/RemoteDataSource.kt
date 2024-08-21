package com.fappslab.mbchallenge.core.data.coordinator.datasource

import com.fappslab.mbchallenge.core.domain.model.Exchange

interface RemoteDataSource {
    suspend fun getExchanges(): List<Exchange>
}
