package com.fappslab.mbchallenge.features.details.presentation.viewmodel

import com.fappslab.mbchallenge.core.domain.model.Exchange

internal data class DetailsViewState(
    val exchangeId: String,
    val shouldShowLoading: Boolean = false,
    val exchange: Exchange? = null
)
