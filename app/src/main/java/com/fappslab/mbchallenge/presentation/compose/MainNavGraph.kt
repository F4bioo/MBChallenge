package com.fappslab.mbchallenge.presentation.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.fappslab.mbchallenge.core.navigation.ExchangesRoute
import com.fappslab.mbchallenge.libraries.arch.navigation.FeatureRoute

@Composable
fun MainNavGraph(
    navController: NavHostController,
    featureRoutes: List<FeatureRoute>
) {

    NavHost(
        navController = navController,
        startDestination = ExchangesRoute
    ) {
        featureRoutes.forEach { featureRoute ->
            featureRoute.register(navGraphBuilder = this)
        }
    }
}
