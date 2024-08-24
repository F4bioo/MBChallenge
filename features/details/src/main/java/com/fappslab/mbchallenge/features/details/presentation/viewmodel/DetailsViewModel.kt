package com.fappslab.mbchallenge.features.details.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.fappslab.mbchallenge.core.domain.usecase.SelectExchangeUseCase
import com.fappslab.mbchallenge.libraries.arch.viewmodel.ViewIntent
import com.fappslab.mbchallenge.libraries.arch.viewmodel.ViewModel
import kotlinx.coroutines.launch

internal class DetailsViewModel(
    exchangeId: String,
    private val selectExchangeUseCase: SelectExchangeUseCase
) : ViewModel<DetailsViewState, DetailsViewEffect>(DetailsViewState(exchangeId)),
    ViewIntent<DetailsViewIntent> {

    override fun onViewIntent(intent: DetailsViewIntent) {
        when (intent) {
            DetailsViewIntent.OnInitView -> handleSelectExchange()
            DetailsViewIntent.OnBackClicked -> handleOnBackClicked()
            is DetailsViewIntent.OnNavigateToWebsite -> handleOnNavigateToWebsite(intent.url)
        }
    }

    private fun handleSelectExchange() {
        viewModelScope.launch {
            onState { copy(shouldShowLoading = true) }
                .runCatching { selectExchangeUseCase(viewState.exchangeId) }
                .onFailure { }
                .onSuccess { onState { copy(shouldShowLoading = false, exchange = it) } }
                .apply { onState { copy(shouldShowLoading = false) } }
        }
    }

    private fun handleOnBackClicked() {
        onEffect { DetailsViewEffect.NavigateToExchanges }
    }

    private fun handleOnNavigateToWebsite(url: String) {
        onEffect { DetailsViewEffect.NavigateToWebsite(url) }
    }
}
