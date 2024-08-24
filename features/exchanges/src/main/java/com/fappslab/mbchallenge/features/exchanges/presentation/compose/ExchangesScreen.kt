package com.fappslab.mbchallenge.features.exchanges.presentation.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fappslab.mbchallenge.core.navigation.DetailsNavigation
import com.fappslab.mbchallenge.core.navigation.DetailsRoute
import com.fappslab.mbchallenge.features.exchanges.di.ExchangesModuleLoad
import com.fappslab.mbchallenge.features.exchanges.presentation.compose.component.ExchangeCircularLoadingIndicator
import com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel.ExchangesViewEffect
import com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel.ExchangesViewModel
import com.fappslab.mbchallenge.libraries.arch.koin.koinlazy.KoinLazyModuleInitializer
import com.fappslab.mbchallenge.libraries.arch.navigation.extension.LocalNavController
import com.fappslab.mbchallenge.libraries.arch.viewmodel.extension.observeAsEvents
import com.fappslab.mbchallenge.libraries.design.theme.PlutoTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ExchangesScreen() {
    KoinLazyModuleInitializer(ExchangesModuleLoad)
    val viewModel: ExchangesViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    ExchangesObserve(viewModel)

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                TopAppBar(
                    modifier = Modifier.weight(1f),
                    title = {
                        Text(
                            text = "Exchanges",
                            style = PlutoTheme.typography.titleLarge,
                        )
                    }
                )
                ExchangeCircularLoadingIndicator(
                    modifier = Modifier.padding(end = PlutoTheme.dimen.dp16),
                    shouldShow = state.shouldShowLoading
                )
            }
        },
        content = {
            ExchangesContent(
                modifier = Modifier.padding(it),
                state = state,
                intent = viewModel::onViewIntent
            )
        }
    )
}

@Composable
private fun ExchangesObserve(
    viewModel: ExchangesViewModel,
    detailsNavigation: DetailsNavigation = koinInject()
) {
    val navController = LocalNavController.current

    viewModel.effect.observeAsEvents { effect ->
        when (effect) {
            is ExchangesViewEffect.NavigateToDetails -> {
                val route = DetailsRoute(effect.exchangeId)
                detailsNavigation.navigateToFeature(navController, route)
            }
        }
    }
}
