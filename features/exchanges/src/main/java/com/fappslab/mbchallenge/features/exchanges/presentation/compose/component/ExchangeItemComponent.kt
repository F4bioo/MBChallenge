package com.fappslab.mbchallenge.features.exchanges.presentation.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.libraries.design.extension.clickable
import com.fappslab.mbchallenge.libraries.design.theme.PlutoTheme
import com.fappslab.mbchallenge.libraries.design.theme.tokens.lightBlue
import com.fappslab.mbchallenge.libraries.design.theme.tokens.lightPurple
import com.fappslab.mbchallenge.libraries.design.theme.tokens.lightRed

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
            modifier = modifier.padding(PlutoTheme.dimen.dp16)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    text = exchange.name,
                    overflow = TextOverflow.Ellipsis,
                    style = PlutoTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
                AsyncImage(
                    modifier = Modifier.size(PlutoTheme.dimen.dp28),
                    model = exchange.iconUrl,
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
            Text(
                text = "USD Volume",
                style = PlutoTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
            Column {
                VolumeBar(
                    label = "1hr",
                    value = exchange.volume1hrsUsd,
                    color = lightBlue
                )
                Spacer(modifier = Modifier.height(PlutoTheme.dimen.dp8))
                VolumeBar(
                    label = "1day",
                    value = exchange.volume1dayUsd,
                    color = lightRed
                )
                Spacer(modifier = Modifier.height(PlutoTheme.dimen.dp8))
                VolumeBar(
                    label = "1mth",
                    value = exchange.volume1mthUsd,
                    color = lightPurple
                )
            }
        }
    }
}

@Composable
private fun VolumeBar(
    label: String,
    value: String,
    color: Color
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = PlutoTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
        Text(
            modifier = Modifier
                .padding(horizontal = PlutoTheme.dimen.dp4)
                .background(color = color, shape = MaterialTheme.shapes.small)
                .padding(horizontal = PlutoTheme.dimen.dp8),
            text = value,
            style = PlutoTheme.typography.labelSmall,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun ExchangeItemComponentPreview() {
    ExchangeItemComponent(
        exchange = Exchange(
            exchangeId = "BINANCE",
            name = "Binance",
            volume1hrsUsd = "246,0M",
            volume1dayUsd = "6,1B",
            volume1mthUsd = "333,8B",
            website = "https://www.binance.com/",
            iconUrl = "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_64/74eaad903814407ebdfc3828fe5318ba.png"
        ),
        itemClicked = {}
    )
}
