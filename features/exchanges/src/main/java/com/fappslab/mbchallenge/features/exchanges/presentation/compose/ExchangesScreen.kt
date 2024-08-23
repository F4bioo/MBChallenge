package com.fappslab.mbchallenge.features.exchanges.presentation.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.fappslab.mbchallenge.features.exchanges.di.ExchangesModuleLoad
import com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel.ExchangesViewEffect
import com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel.ExchangesViewModel
import com.fappslab.mbchallenge.libraries.arch.koin.koinlazy.KoinLazyModuleInitializer
import com.fappslab.mbchallenge.libraries.arch.navigation.extension.LocalNavController
import com.fappslab.mbchallenge.libraries.arch.viewmodel.extension.observeAsEvents
import com.fappslab.mbchallenge.libraries.design.theme.PlutoTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ExchangesScreen() {
    KoinLazyModuleInitializer(ExchangesModuleLoad)
    val viewModel: ExchangesViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    ExchangesObserve(viewModel)

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = "Exchanges",
                        style = PlutoTheme.typography.titleLarge,
                    )
                }
            )
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
) {
    val navController = LocalNavController.current

    viewModel.effect.observeAsEvents { effect ->
        when (effect) {
            is ExchangesViewEffect.NavigateToDetails -> {}
        }
    }
}
