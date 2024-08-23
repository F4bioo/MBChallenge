package com.fappslab.mbchallenge.features.exchanges.di

import com.fappslab.mbchallenge.core.data.remote.di.COIN_API_HTTP_CLIENT_QUALIFIER
import com.fappslab.mbchallenge.core.data.remote.network.HttpClient
import com.fappslab.mbchallenge.features.exchanges.data.api.CoinAPIService
import com.fappslab.mbchallenge.features.exchanges.data.repository.ExchangesRepositoryImpl
import com.fappslab.mbchallenge.features.exchanges.data.source.ExchangesRemoteDataSourceImpl
import com.fappslab.mbchallenge.features.exchanges.domain.repository.ExchangesRepository
import com.fappslab.mbchallenge.features.exchanges.domain.usecase.GetExchangesUseCases
import com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel.ExchangesViewModel
import com.fappslab.mbchallenge.libraries.arch.koin.koinload.KoinLoad
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal object ExchangesModuleLoad : KoinLoad() {

    override val dataModule: Module = module {
        factory<ExchangesRepository> {
            ExchangesRepositoryImpl(
                ExchangesRemoteDataSourceImpl(
                    service = get<HttpClient>(
                        named(COIN_API_HTTP_CLIENT_QUALIFIER)
                    ).create(CoinAPIService::class.java),
                )
            )
        }
    }

    override val presentationModule: Module = module {
        viewModel {
            ExchangesViewModel(
                getExchangesUseCases = GetExchangesUseCases(
                    repository = get()
                )
            )
        }
    }
}
