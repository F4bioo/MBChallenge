package com.fappslab.mbchallenge.core.data.remote.network.monitor

import com.fappslab.mbchallenge.core.domain.model.NetworkStateType
import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    fun watchNetworkState(): Flow<NetworkStateType>
}
