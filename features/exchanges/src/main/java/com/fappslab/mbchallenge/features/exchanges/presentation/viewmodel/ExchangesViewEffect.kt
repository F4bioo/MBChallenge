package com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel

internal sealed interface ExchangesViewEffect {
    data class NavigateToDetails(val exchangeId: String) : ExchangesViewEffect
}
