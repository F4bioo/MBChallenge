package com.fappslab.mbchallenge.features.details.presentation.viewmodel

internal sealed interface DetailsViewEffect {
    data object NavigateToExchanges : DetailsViewEffect
    data class NavigateToWebsite(val url: String) : DetailsViewEffect
}
