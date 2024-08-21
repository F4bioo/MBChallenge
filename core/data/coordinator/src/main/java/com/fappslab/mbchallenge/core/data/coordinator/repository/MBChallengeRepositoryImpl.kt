package com.fappslab.mbchallenge.core.data.coordinator.repository

import com.fappslab.mbchallenge.core.data.coordinator.datasource.RemoteDataSource
import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository

internal class MBChallengeRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : MBChallengeRepository {

    override suspend fun getExchanges(): List<Exchange> {
        return remoteDataSource.getExchanges()
    }
}
