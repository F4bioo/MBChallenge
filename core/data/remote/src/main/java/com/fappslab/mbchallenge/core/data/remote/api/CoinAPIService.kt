package com.fappslab.mbchallenge.core.data.remote.api

import com.fappslab.mbchallenge.core.data.remote.model.ExchangeResponse
import com.fappslab.mbchallenge.core.data.remote.model.IconResponse
import retrofit2.http.GET

internal interface CoinAPIService {

    @GET("/v1/exchanges")
    suspend fun getExchanges(): List<ExchangeResponse>

    @GET("/v1/exchanges/icons/64")
    suspend fun getExchangesIcons(): List<IconResponse>
}
