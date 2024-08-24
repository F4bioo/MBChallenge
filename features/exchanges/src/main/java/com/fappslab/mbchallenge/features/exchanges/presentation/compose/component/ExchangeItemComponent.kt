package com.fappslab.mbchallenge.features.exchanges.presentation.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.features.exchanges.R
import com.fappslab.mbchallenge.libraries.design.extension.clickable
import com.fappslab.mbchallenge.libraries.design.theme.PlutoTheme

@Composable
internal fun ExchangeItemComponent(
    modifier: Modifier = Modifier,
    exchange: Exchange,
    itemClicked: (Exchange) -> Unit
) {

    Card(
        modifier = Modifier.clickable { itemClicked(exchange) },
        shape = RoundedCornerShape(PlutoTheme.radius.medium),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
    ) {
        Column(
            modifier = modifier.padding(PlutoTheme.dimen.dp8)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                AsyncImage(
                    modifier = modifier
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
                        style = PlutoTheme.typography.titleMedium,
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        text = "ID: ${exchange.exchangeId}",
                        overflow = TextOverflow.Ellipsis,
                        style = PlutoTheme.typography.labelSmall,
                    )
                }
            }
            Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
            Text(
                text = "USD Volume",
                style = PlutoTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "1hr",
                    style = PlutoTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.outline
                )
                Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
                Text(
                    modifier = Modifier
                        .padding(horizontal = PlutoTheme.dimen.dp4)
                        .background(
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colorScheme.primary
                        )
                        .padding(horizontal = PlutoTheme.dimen.dp8),
                    text = exchange.volume1hrsUsd,
                    style = PlutoTheme.typography.labelSmall,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun ExchangeItemComponentPreview() {
    ExchangeItemComponent(
        exchange = Exchange(
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
        ),
        itemClicked = {}
    )
}
