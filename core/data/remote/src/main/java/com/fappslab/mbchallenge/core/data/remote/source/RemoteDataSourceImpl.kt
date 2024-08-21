package com.fappslab.mbchallenge.core.data.remote.source

import com.fappslab.mbchallenge.core.data.coordinator.datasource.RemoteDataSource
import com.fappslab.mbchallenge.core.data.remote.api.CoinAPIService
import com.fappslab.mbchallenge.core.data.remote.model.extension.toExchangeList
import com.fappslab.mbchallenge.core.data.remote.network.exception.extension.parseHttpError
import com.fappslab.mbchallenge.core.data.remote.network.monitor.NetworkMonitor
import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.core.domain.model.NetworkStateType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class RemoteDataSourceImpl(
    private val service: CoinAPIService,
    private val networkMonitor: NetworkMonitor,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteDataSource {

    override fun watchNetworkState(): Flow<NetworkStateType> {
        return networkMonitor.watchNetworkState()
    }

    override suspend fun getExchanges(): List<Exchange> {
        return runCatching {
            withContext(dispatcher) {
                val iconsResponseDeferred = async { service.getExchangesIcons() }
                val exchangesResponseDeferred = async { service.getExchanges() }

                val iconsResponse = iconsResponseDeferred.await()
                val exchangesResponse = exchangesResponseDeferred.await()

                exchangesResponse.toExchangeList(iconsResponse)
            }
        }.getOrElse { cause ->
            throw cause.parseHttpError()
        }
    }
}
