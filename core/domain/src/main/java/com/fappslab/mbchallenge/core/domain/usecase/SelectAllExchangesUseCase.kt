package com.fappslab.mbchallenge.core.domain.usecase

import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository

class SelectAllExchangesUseCase(
    private val repository: MBChallengeRepository
) {

    suspend operator fun invoke(): List<Exchange> {
        return repository.selectAllExchanges()
    }
}
