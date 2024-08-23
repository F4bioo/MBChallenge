package com.fappslab.mbchallenge.core.data.coordinator.datasource

import com.fappslab.mbchallenge.core.domain.model.NetworkStateType
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun watchNetworkState(): Flow<NetworkStateType>
}
