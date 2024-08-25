package com.fappslab.mbchallenge.features.details.presentation.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.fappslab.mbchallenge.core.navigation.DetailsRoute
import com.fappslab.mbchallenge.features.details.R
import com.fappslab.mbchallenge.features.details.di.DetailsModuleLoad
import com.fappslab.mbchallenge.features.details.presentation.viewmodel.DetailsViewEffect
import com.fappslab.mbchallenge.features.details.presentation.viewmodel.DetailsViewIntent
import com.fappslab.mbchallenge.features.details.presentation.viewmodel.DetailsViewModel
import com.fappslab.mbchallenge.libraries.arch.extension.openBrowser
import com.fappslab.mbchallenge.libraries.arch.koin.koinlazy.KoinLazyModuleInitializer
import com.fappslab.mbchallenge.libraries.arch.navigation.extension.LocalNavController
import com.fappslab.mbchallenge.libraries.arch.viewmodel.extension.observeAsEvents
import com.fappslab.mbchallenge.libraries.design.components.button.ButtonType
import com.fappslab.mbchallenge.libraries.design.components.button.PlutoButtonComponent
import com.fappslab.mbchallenge.libraries.design.theme.PlutoTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

internal const val WEBSITE_CLICKED_TEST_TAG = "WEBSITE_CLICKED_TEST_TAG"
internal const val ON_BACK_CLICKED_TEST_TAG = "ON_BACK_CLICKED_TEST_TAG"


@Composable
internal fun DetailsScreen(
    exchangeId: String,
    vm: DetailsViewModel? = null
) {
    KoinLazyModuleInitializer(DetailsModuleLoad)
    val viewModel = vm ?: koinViewModel { parametersOf(exchangeId) }
    val state by viewModel.state.collectAsState()
    DetailsObserve(viewModel)

    Scaffold(
        modifier = Modifier.padding(PlutoTheme.dimen.dp16),
        bottomBar = {
            PlutoButtonComponent(
                modifier = Modifier
                    .testTag(ON_BACK_CLICKED_TEST_TAG)
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                text = stringResource(R.string.common_go_back),
                buttonType = ButtonType.Primary,
                onClick = { viewModel.onViewIntent(DetailsViewIntent.OnBackClicked) }
            )
        },
        content = {
            DetailsContent(
                modifier = Modifier.padding(it),
                state = state,
                intent = viewModel::onViewIntent
            )
        }
    )
}

@Composable
private fun DetailsObserve(
    viewModel: DetailsViewModel,
) {
    val context = LocalContext.current
    val navController = LocalNavController.current

    viewModel.effect.observeAsEvents { effect ->
        when (effect) {
            is DetailsViewEffect.NavigateToExchanges -> {
                navController.popBackStack<DetailsRoute>(inclusive = true)
            }

            is DetailsViewEffect.NavigateToWebsite -> {
                context.openBrowser(effect.url)
            }
        }
    }
}
