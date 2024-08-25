package com.fappslab.mbchallenge.features.exchanges.domain.usecase

import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.features.exchanges.domain.repository.ExchangesRepository

internal class GetExchangesUseCase(
    private val repository: ExchangesRepository
) {

    suspend operator fun invoke(): List<Exchange> {
        return repository.getExchanges()
    }
}
