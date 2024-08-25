package com.fappslab.mbchallenge.features.details.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.toRoute
import com.fappslab.mbchallenge.core.navigation.DetailsNavigation
import com.fappslab.mbchallenge.core.navigation.DetailsRoute
import com.fappslab.mbchallenge.features.details.presentation.compose.DetailsScreen
import com.fappslab.mbchallenge.libraries.arch.navigation.extension.animatedComposable

internal class DetailsNavigationImpl : DetailsNavigation {

    override fun register(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.animatedComposable<DetailsRoute> {
            val args = it.toRoute<DetailsRoute>()
            DetailsScreen(args.exchangeId)
        }
    }
}
