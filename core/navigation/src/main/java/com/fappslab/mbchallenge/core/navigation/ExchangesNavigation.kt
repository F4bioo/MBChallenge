package com.fappslab.mbchallenge.core.navigation

import com.fappslab.mbchallenge.libraries.arch.navigation.FeatureRoute
import com.fappslab.mbchallenge.libraries.arch.navigation.NavigateRoute
import kotlinx.serialization.Serializable

interface ExchangesNavigation : FeatureRoute, NavigateRoute<ExchangesRoute>

@Serializable
object ExchangesRoute
