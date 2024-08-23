package com.fappslab.mbchallenge.features.exchanges.domain.usecase

import com.fappslab.mbchallenge.features.exchanges.domain.model.Exchange
import com.fappslab.mbchallenge.features.exchanges.domain.repository.ExchangesRepository

internal class GetExchangesUseCases(
    private val repository: ExchangesRepository
) {

    suspend operator fun invoke(): List<Exchange> {
        return repository.getExchanges()
    }
}
