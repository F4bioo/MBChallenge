package com.fappslab.mbchallenge.core.domain.usecase

import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository

class SelectExchangeUseCase(
    private val repository: MBChallengeRepository
) {

    suspend operator fun invoke(exchangeId: String): Exchange {
        return repository.selectExchange(exchangeId)
    }
}
