package com.fappslab.mbchallenge.core.data.local.database

interface DatabaseClient<T> {
    fun create(): T
}
