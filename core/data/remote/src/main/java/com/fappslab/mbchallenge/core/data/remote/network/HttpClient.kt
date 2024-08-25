package com.fappslab.mbchallenge.core.data.remote.network

interface HttpClient {
    fun <T> create(clazz: Class<T>): T
}
