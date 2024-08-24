package com.fappslab.mbchallenge.features.details.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.features.details.R
import com.fappslab.mbchallenge.features.details.presentation.compose.content.DetailsVolumeBarComponent
import com.fappslab.mbchallenge.features.details.presentation.viewmodel.DetailsViewIntent
import com.fappslab.mbchallenge.features.details.presentation.viewmodel.DetailsViewState
import com.fappslab.mbchallenge.libraries.design.theme.PlutoTheme

@Composable
internal fun DetailsContent(
    modifier: Modifier = Modifier,
    state: DetailsViewState,
    intent: (DetailsViewIntent) -> Unit,
) {

    LaunchedEffect(Unit) {
        intent(DetailsViewIntent.OnInitView)
    }
    state.exchange?.let { exchange ->
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(PlutoTheme.radius.large),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PlutoTheme.dimen.dp16)
            ) {
                Header(exchange = exchange)
                Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp16))
                Text(
                    text = "USD Volume",
                    style = PlutoTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.secondary
                )
                DetailsVolumeBarComponent(
                    label = "1hr",
                    value = exchange.volume1hrsUsd,
                    color = MaterialTheme.colorScheme.primary
                )
                DetailsVolumeBarComponent(
                    label = "1day",
                    value = exchange.volume1dayUsd,
                    color = MaterialTheme.colorScheme.secondary
                )
                DetailsVolumeBarComponent(
                    label = "1mth",
                    value = exchange.volume1mthUsd,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp16))
                InfoPair(
                    title = "Website",
                    subtitle = exchange.website,
                    onClick = { intent(DetailsViewIntent.OnNavigateToWebsite(exchange.website)) }
                )
            }
        }
    }
}

@Composable
private fun Header(
    exchange: Exchange,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            modifier = Modifier
                .size(PlutoTheme.dimen.dp40)
                .clip(RoundedCornerShape(PlutoTheme.radius.small)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(exchange.iconUrl)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.illu_placeholder),
            error = painterResource(R.drawable.illu_placeholder),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                text = exchange.name,
                overflow = TextOverflow.Ellipsis,
                style = PlutoTheme.typography.titleLarge,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                text = "ID: ${exchange.exchangeId}",
                overflow = TextOverflow.Ellipsis,
                style = PlutoTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun ColumnScope.InfoPair(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
) {

    Text(
        maxLines = 1,
        text = title,
        overflow = TextOverflow.Ellipsis,
        style = PlutoTheme.typography.bodyLarge,
    )
    Text(
        modifier = Modifier.clickable { onClick() },
        maxLines = 1,
        text = subtitle,
        overflow = TextOverflow.Ellipsis,
        style = PlutoTheme.typography.labelLarge.copy(
            textDecoration = TextDecoration.Underline
        ),
        color = PlutoTheme.colors.blueLink
    )
}

@Preview
@Composable
private fun DetailsContentPreview() {
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
    val state = DetailsViewState(
        exchangeId = "BINANCE",
        shouldShowLoading = false,
        exchange = exchange
    )
    PlutoTheme(
        darkTheme = false
    ) {
        DetailsContent(
            state = state,
            intent = {},
        )
    }
}
