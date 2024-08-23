package com.fappslab.mbchallenge.core.data.remote.network

import retrofit2.Retrofit

class HttpClientImpl(
    private val retrofit: Retrofit
) : HttpClient {

    override fun <T> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}
