package com.fappslab.mbchallenge.core.data.local.model.extension

import com.fappslab.mbchallenge.core.data.local.model.ExchangeEntity
import com.fappslab.mbchallenge.core.domain.model.Exchange

internal fun ExchangeEntity.toExchange(): Exchange {
    return Exchange(
        exchangeId = exchangeId,
        name = name,
        volume1hrsUsd = volume1hrsUsd,
        volume1dayUsd = volume1dayUsd,
        volume1mthUsd = volume1mthUsd,
        website = website,
        iconUrl = iconUrl
    )
}

internal fun Exchange.toExchangeEntity(): ExchangeEntity {
    return ExchangeEntity(
        exchangeId = exchangeId,
        name = name,
        volume1hrsUsd = volume1hrsUsd,
        volume1dayUsd = volume1dayUsd,
        volume1mthUsd = volume1mthUsd,
        website = website,
        iconUrl = iconUrl
    )
}

internal fun List<ExchangeEntity>.toExchangeList(): List<Exchange> {
    return map { it.toExchange() }
}

internal fun List<Exchange>.toExchangeListEntity(): List<ExchangeEntity> {
    return map { it.toExchangeEntity() }
}
