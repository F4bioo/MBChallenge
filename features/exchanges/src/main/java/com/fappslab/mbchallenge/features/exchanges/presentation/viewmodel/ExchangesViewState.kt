package com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel

import com.fappslab.mbchallenge.core.data.remote.model.ErrorType
import com.fappslab.mbchallenge.features.exchanges.domain.model.Exchange

internal data class ExchangesViewState(
    val shouldShowLoading: Boolean = false,
    val shouldShowError: Boolean = false,
    val exchanges: List<Exchange> = emptyList(),
    val errorType: ErrorType = ErrorType.Generic
)
