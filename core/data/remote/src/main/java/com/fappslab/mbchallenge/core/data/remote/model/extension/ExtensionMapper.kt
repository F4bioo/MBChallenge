package com.fappslab.mbchallenge.core.data.remote.model.extension

import com.fappslab.mbchallenge.core.data.remote.model.ExchangeResponse
import com.fappslab.mbchallenge.core.data.remote.model.IconResponse
import com.fappslab.mbchallenge.core.domain.model.Exchange
import com.fappslab.mbchallenge.libraries.arch.extension.orZero

internal fun ExchangeResponse.toExchange(iconUrl: String?): Exchange {
    return Exchange(
        exchangeId = requireNotNull(exchangeId) { "Exchange ID cannot be null" },
        name = name ?: exchangeId,
        volume1dayUsd = volume1dayUsd.orZero(),
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
