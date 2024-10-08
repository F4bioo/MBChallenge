package com.fappslab.mbchallenge.features.exchanges.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fappslab.mbchallenge.core.data.remote.model.ErrorType
import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.features.exchanges.R
import com.fappslab.mbchallenge.features.exchanges.presentation.compose.component.ExchangeEmptyScreenComponent
import com.fappslab.mbchallenge.features.exchanges.presentation.compose.component.ExchangeHorizontalLoadingIndicator
import com.fappslab.mbchallenge.features.exchanges.presentation.compose.component.ExchangeItemComponent
import com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel.ExchangesViewIntent
import com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel.ExchangesViewState
import com.fappslab.mbchallenge.libraries.design.components.modal.PlutoModalComponent
import com.fappslab.mbchallenge.libraries.design.theme.PlutoTheme

@Composable
internal fun ExchangesContent(
    modifier: Modifier = Modifier,
    state: ExchangesViewState,
    intent: (ExchangesViewIntent) -> Unit,
) {

    LaunchedEffect(Unit) {
        intent(ExchangesViewIntent.OnGetExchanges)
    }
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        ExchangeHorizontalLoadingIndicator(
            modifier = Modifier.testTag(LOADING_INDICATOR_TEST_TAG),
            shouldShow = state.shouldShowLoading
        )
        LazyVerticalStaggeredGrid(
            modifier = Modifier.padding(PlutoTheme.dimen.dp8),
            columns = StaggeredGridCells.Fixed(count = 2),
            verticalItemSpacing = PlutoTheme.dimen.dp8,
            horizontalArrangement = Arrangement.spacedBy(PlutoTheme.dimen.dp8),
        ) {
            item(span = StaggeredGridItemSpan.FullLine) {
                CardTopBar(modifier = Modifier.fillMaxWidth())
            }
            items(state.exchanges) { exchange ->
                ExchangeItemComponent(
                    exchange = exchange,
                    itemClicked = { intent(ExchangesViewIntent.OnExchangeClicked(it.exchangeId)) }
                )
            }
        }
        ExchangeEmptyScreenComponent(
            shouldShowEmptyScreen = state.exchanges.isEmpty(),
            onTryAgainClicked = { intent(ExchangesViewIntent.OnGetExchanges) }
        )
        ErrorModal(
            shouldShowError = state.shouldShowError,
            errorType = state.errorType,
            intent = intent
        )
    }
}

@Composable
internal fun CardTopBar(
    modifier: Modifier = Modifier,
) {

    Card(
        modifier = modifier
            .testTag(TOP_BAR_TEST_TAG)
            .statusBarsPadding(),
        shape = RoundedCornerShape(PlutoTheme.radius.medium),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
    ) {
        Column(
            modifier = Modifier.padding(PlutoTheme.dimen.dp16)
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = PlutoTheme.typography.titleLarge,
            )
            Text(
                text = stringResource(R.string.exchanges),
                style = PlutoTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun ErrorModal(
    shouldShowError: Boolean,
    errorType: ErrorType,
    intent: (ExchangesViewIntent) -> Unit
) {

    PlutoModalComponent(
        isSheetSwipeEnabled = false,
        shouldShow = shouldShowError,
        titleRes = errorType.titleRes,
        messageRes = errorType.messageRes,
        image = {
            Image(
                modifier = Modifier.size(PlutoTheme.dimen.dp64),
                painter = painterResource(errorType.illuRes),
                colorFilter = ColorFilter.tint(PlutoTheme.colors.stealthGray),
                contentDescription = null
            )
        },
        onDismiss = {
            intent(ExchangesViewIntent.OnCLoseErrorModal)
        },
        primaryButton = if (errorType != ErrorType.TooManyRequests) {
            {
                buttonTextRes = R.string.common_try_again
                onCLicked = { intent(ExchangesViewIntent.OnGetExchanges) }
            }
        } else null,
        secondaryButton = {
            buttonTextRes = R.string.common_close
            onCLicked = { intent(ExchangesViewIntent.OnCLoseErrorModal) }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ExchangesContentPreview() {
    val exchange = Exchange(
        exchangeId = "MERCADOBITCOIN",
        website = "https://www.mercadobitcoin.com.br/",
        name = "Mercado Bitcoin",
        dataQuoteStart = "15/03/17 00:00",
        dataQuoteEnd = "24/08/24 00:00",
        dataOrderBookStart = "14/03/17 20:05",
        dataOrderBookEnd = "06/07/23 00:00",
        dataTradeStart = "07/03/17 00:00",
        dataTradeEnd = "24/08/24 00:00",
        dataSymbolsCount = "462",
        volume1hrsUsd = "228.67",
        volume1dayUsd = "829.7K",
        volume1mthUsd = "192.7M",
        iconUrl = "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_64/5fbfbd742fb64c67a3963ebd7265f9f3.png",
        isEditorChoice = true
    )
    val state = ExchangesViewState(
        shouldShowLoading = false,
        shouldShowError = false,
        exchanges = List(5) { exchange }
    )
    PlutoTheme(
        darkTheme = false
    ) {
        ExchangesContent(
            state = state,
            intent = {},
        )
    }
}
