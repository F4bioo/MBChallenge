package com.fappslab.mbchallenge.core.data.coordinator.datasource

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllData(): Flow<List<String>>
}
