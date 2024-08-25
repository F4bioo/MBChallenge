package com.fappslab.mbchallenge.features.exchanges.navigation

import androidx.navigation.NavGraphBuilder
import com.fappslab.mbchallenge.core.navigation.ExchangesNavigation
import com.fappslab.mbchallenge.core.navigation.ExchangesRoute
import com.fappslab.mbchallenge.features.exchanges.presentation.compose.ExchangesScreen
import com.fappslab.mbchallenge.libraries.arch.navigation.extension.animatedComposable

internal class ExchangesNavigationImpl : ExchangesNavigation {

    override fun register(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.animatedComposable<ExchangesRoute> {
            ExchangesScreen()
        }
    }
}
