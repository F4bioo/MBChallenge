package com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.fappslab.mbchallenge.core.data.remote.model.ErrorType
import com.fappslab.mbchallenge.core.data.remote.network.exception.model.HttpThrowable
import com.fappslab.mbchallenge.core.domain.usecase.InsertAllExchangesUseCase
import com.fappslab.mbchallenge.core.domain.usecase.SelectAllExchangesUseCase
import com.fappslab.mbchallenge.features.exchanges.domain.usecase.GetExchangesUseCase
import com.fappslab.mbchallenge.libraries.arch.viewmodel.ViewIntent
import com.fappslab.mbchallenge.libraries.arch.viewmodel.ViewModel
import kotlinx.coroutines.launch

internal class ExchangesViewModel(
    private val insertAllExchangesUseCase: InsertAllExchangesUseCase,
    private val selectAllExchangesUseCase: SelectAllExchangesUseCase,
    private val getExchangesUseCase: GetExchangesUseCase
) : ViewModel<ExchangesViewState, ExchangesViewEffect>(ExchangesViewState()),
    ViewIntent<ExchangesViewIntent> {

    override fun onViewIntent(intent: ExchangesViewIntent) {
        when (intent) {
            ExchangesViewIntent.OnGetExchanges -> handleGetExchanges()
            ExchangesViewIntent.OnCLoseErrorModal -> handleCloseErrorModal()
            is ExchangesViewIntent.OnExchangeClicked -> handleNavigateToDetails(intent.exchangeId)
        }
    }

    private fun handleGetExchanges() {
        viewModelScope.launch {
            onState { copy(shouldShowLoading = true, shouldShowError = false) }
                .runCatching {
                    val localExchanges = selectAllExchangesUseCase()
                    onState { copy(exchanges = localExchanges) }
                    val remoteExchanges = getExchangesUseCase()
                    onState { copy(exchanges = remoteExchanges) }
                    insertAllExchangesUseCase(remoteExchanges)
                }.getOrElse { getExchangesFailure(cause = it) }
                .apply { onState { copy(shouldShowLoading = false) } }
        }
    }

    private fun getExchangesFailure(cause: Throwable) {
        val errorType = when (cause) {
            is HttpThrowable -> cause.errorType
            else -> ErrorType.Generic
        }
        onState { copy(errorType = errorType, shouldShowError = true) }
    }

    private fun handleNavigateToDetails(exchangeId: String) {
        onEffect { ExchangesViewEffect.NavigateToDetails(exchangeId) }
    }

    private fun handleCloseErrorModal() {
        onState { copy(shouldShowError = false) }
    }
}
