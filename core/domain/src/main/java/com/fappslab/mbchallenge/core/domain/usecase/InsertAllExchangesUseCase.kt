package com.fappslab.mbchallenge.core.domain.usecase

import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository

class InsertAllExchangesUseCase(
    private val repository: MBChallengeRepository
) {

    suspend operator fun invoke(exchanges: List<Exchange>) {
        repository.insertAllExchanges(exchanges)
    }
}
