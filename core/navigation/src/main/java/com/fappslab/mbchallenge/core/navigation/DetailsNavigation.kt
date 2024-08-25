package com.fappslab.mbchallenge.core.navigation

import com.fappslab.mbchallenge.libraries.arch.navigation.FeatureRoute
import com.fappslab.mbchallenge.libraries.arch.navigation.NavigateRoute
import kotlinx.serialization.Serializable

interface DetailsNavigation : FeatureRoute, NavigateRoute<DetailsRoute>

@Serializable
data class DetailsRoute(
    val exchangeId: String
)
