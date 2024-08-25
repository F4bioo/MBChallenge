package com.fappslab.mbchallenge.di

import com.fappslab.mbchallenge.core.data.remote.BuildConfig
import com.fappslab.mbchallenge.core.data.remote.di.COIN_API_INTERCEPTORS_QUALIFIER
import com.fappslab.mbchallenge.core.data.remote.network.interceptor.ApiKeyInterceptor
import com.fappslab.mbchallenge.core.data.remote.network.interceptor.HeadersInterceptor
import com.fappslab.mbchallenge.libraries.arch.koin.koinshot.KoinShot
import okhttp3.Interceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

class MainModuleShot : KoinShot() {

    override val dataModule = module {
        single(named(COIN_API_INTERCEPTORS_QUALIFIER)) {
            listOf<Interceptor>(
                ApiKeyInterceptor(BuildConfig.COIN_API_KEY),
                HeadersInterceptor(androidApplication()),
            )
        }
    }
}
