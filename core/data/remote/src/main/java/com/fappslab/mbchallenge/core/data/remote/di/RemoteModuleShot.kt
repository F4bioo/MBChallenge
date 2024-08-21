package com.fappslab.mbchallenge.core.data.remote.di

import android.content.Context
import android.net.ConnectivityManager
import com.fappslab.mbchallenge.core.data.coordinator.datasource.RemoteDataSource
import com.fappslab.mbchallenge.core.data.remote.BuildConfig
import com.fappslab.mbchallenge.core.data.remote.api.CoinAPIService
import com.fappslab.mbchallenge.core.data.remote.network.HttpClient
import com.fappslab.mbchallenge.core.data.remote.network.HttpClientImpl
import com.fappslab.mbchallenge.core.data.remote.network.monitor.NetworkMonitor
import com.fappslab.mbchallenge.core.data.remote.network.monitor.NetworkMonitorImpl
import com.fappslab.mbchallenge.core.data.remote.network.retrofit.RetrofitClient
import com.fappslab.mbchallenge.core.data.remote.source.RemoteDataSourceImpl
import com.fappslab.mbchallenge.libraries.arch.koin.koinshot.KoinShot
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

const val COIN_API_INTERCEPTORS_QUALIFIER = "COIN_API_INTERCEPTORS_QUALIFIER"
const val COIN_API_RETROFIT_QUALIFIER = "COIN_API_RETROFIT_QUALIFIER"
const val COIN_API_HTTP_CLIENT_QUALIFIER = "COIN_API_HTTP_CLIENT_QUALIFIER"

internal class RemoteModuleShot : KoinShot() {

    override val dataModule: Module = module {
        single<Retrofit>(named(COIN_API_RETROFIT_QUALIFIER)) {
            RetrofitClient(
                baseUrl = BuildConfig.BASE_URL,
                interceptors = get(named(COIN_API_INTERCEPTORS_QUALIFIER))
            ).create()
        }
        single<HttpClient>(named(COIN_API_HTTP_CLIENT_QUALIFIER)) {
            HttpClientImpl(retrofit = get(named(COIN_API_RETROFIT_QUALIFIER)))
        }
        single<NetworkMonitor> {
            NetworkMonitorImpl(
                connectivityManager = get<Context>().getSystemService(
                    Context.CONNECTIVITY_SERVICE
                ) as ConnectivityManager
            )
        }

        factory<RemoteDataSource> {
            RemoteDataSourceImpl(
                service = get<HttpClient>(
                    named(COIN_API_HTTP_CLIENT_QUALIFIER)
                ).create(CoinAPIService::class.java),
                networkMonitor = get<NetworkMonitor>()
            )
        }
    }
}
