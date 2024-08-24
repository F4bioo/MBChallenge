package com.fappslab.mbchallenge.features.exchanges.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fappslab.mbchallenge.core.data.remote.model.ErrorType
import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.features.exchanges.R
import com.fappslab.mbchallenge.features.exchanges.presentation.compose.component.ExchangeEmptyScreenComponent
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
        LazyVerticalGrid(
            modifier = Modifier.padding(PlutoTheme.dimen.dp8),
            columns = GridCells.Adaptive(minSize = PlutoTheme.dimen.dp160),
            horizontalArrangement = Arrangement.spacedBy(PlutoTheme.dimen.dp8),
            verticalArrangement = Arrangement.spacedBy(PlutoTheme.dimen.dp8)
        ) {
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
        exchangeId = "BINANCE",
        website = "https://www.binance.com/",
        name = "Binance",
        dataQuoteStart = "18/12/17 00:00",
        dataQuoteEnd = "20/08/24 00:00",
        dataOrderBookStart = "18/12/17 21:50",
        dataOrderBookEnd = "07/07/23 00:00",
        dataTradeStart = "14/07/17 00:00",
        dataTradeEnd = "21/08/24 00:00",
        dataSymbolsCount = "2731",
        volume1hrsUsd = "246,0M",
        volume1dayUsd = "6,1B",
        volume1mthUsd = "333,8B",
        iconUrl = "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_64/74eaad903814407ebdfc3828fe5318ba.png"
    )
    val state = ExchangesViewState(
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
