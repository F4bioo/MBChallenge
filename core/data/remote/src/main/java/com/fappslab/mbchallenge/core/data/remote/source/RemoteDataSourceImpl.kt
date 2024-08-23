package com.fappslab.mbchallenge.core.data.remote.source

import com.fappslab.mbchallenge.core.data.coordinator.datasource.RemoteDataSource
import com.fappslab.mbchallenge.core.data.remote.network.monitor.NetworkMonitor
import com.fappslab.mbchallenge.core.domain.model.NetworkStateType
import kotlinx.coroutines.flow.Flow

internal class RemoteDataSourceImpl(
    private val networkMonitor: NetworkMonitor,
) : RemoteDataSource {

    override fun watchNetworkState(): Flow<NetworkStateType> {
        return networkMonitor.watchNetworkState()
    }
}
