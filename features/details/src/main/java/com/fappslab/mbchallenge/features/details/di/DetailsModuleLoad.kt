package com.fappslab.mbchallenge.features.details.di

import com.fappslab.mbchallenge.core.domain.usecase.SelectExchangeUseCase
import com.fappslab.mbchallenge.features.details.presentation.viewmodel.DetailsViewModel
import com.fappslab.mbchallenge.libraries.arch.koin.koinload.KoinLoad
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal object DetailsModuleLoad : KoinLoad() {

    override val presentationModule: Module = module {
        viewModel { (exchangeId: String) ->
            println("<L> exchangeId: $exchangeId")
            DetailsViewModel(
                exchangeId = exchangeId,
                selectExchangeUseCase = SelectExchangeUseCase(repository = get())
            )
        }
    }
}
