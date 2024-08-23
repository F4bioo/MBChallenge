package com.fappslab.mbchallenge.core.domain.usecase

import com.fappslab.mbchallenge.core.domain.model.NetworkStateType
import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository
import kotlinx.coroutines.flow.Flow

class WatchNetworkStateUseCase(
    private val repository: MBChallengeRepository
) {

    operator fun invoke(): Flow<NetworkStateType> {
        return repository.watchNetworkState()
    }
}
