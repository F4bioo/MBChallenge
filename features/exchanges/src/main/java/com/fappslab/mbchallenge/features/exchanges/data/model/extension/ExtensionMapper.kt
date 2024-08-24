package com.fappslab.mbchallenge.features.exchanges.data.model.extension

import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.features.exchanges.data.model.ExchangeResponse
import com.fappslab.mbchallenge.features.exchanges.data.model.IconResponse
import com.fappslab.mbchallenge.libraries.arch.extension.orZero
import java.util.Locale

private const val THOUSAND = 1_000
private const val MILLION = 1_000_000
private const val BILLION = 1_000_000_000
private const val TRILLION = 1_000_000_000_000

internal fun ExchangeResponse.toExchange(iconUrl: String?): Exchange {
    return Exchange(
        exchangeId = requireNotNull(exchangeId) { "Exchange ID cannot be null" },
        name = name ?: exchangeId,
        volume1hrsUsd = volume1hrsUsd.formatVolume(),
        volume1dayUsd = volume1dayUsd.formatVolume(),
        volume1mthUsd = volume1mthUsd.formatVolume(),
        website = website.orEmpty(),
        iconUrl = iconUrl.orEmpty()
    )
}

internal fun List<ExchangeResponse>.toExchangeList(iconResponses: List<IconResponse>): List<Exchange> {
    val iconMap = iconResponses.associateBy { it.exchangeId }

    return map { exchangeResponse ->
        val iconUrl = iconMap[exchangeResponse.exchangeId]?.url
        exchangeResponse.toExchange(iconUrl)
    }
}

private fun Double?.formatVolume(locale: Locale = Locale.getDefault()): String {
    val value = this.orZero()
    return when {
        value <= 0.0 -> "-"
        value >= TRILLION -> String.format(locale, "%.1fT", value / TRILLION)
        value >= BILLION -> String.format(locale, "%.1fB", value / BILLION)
        value >= MILLION -> String.format(locale, "%.1fM", value / MILLION)
        value >= THOUSAND -> String.format(locale, "%.1fK", value / THOUSAND)
        else -> this.toString()
    }
}
