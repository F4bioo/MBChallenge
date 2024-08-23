package com.fappslab.mbchallenge.core.data.coordinator.repository

import com.fappslab.mbchallenge.core.data.coordinator.datasource.RemoteDataSource
import com.fappslab.mbchallenge.core.domain.model.NetworkStateType
import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository
import kotlinx.coroutines.flow.Flow

internal class MBChallengeRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : MBChallengeRepository {

    override fun watchNetworkState(): Flow<NetworkStateType> {
        return remoteDataSource.watchNetworkState()
    }
}
