package com.fappslab.mbchallenge.features.exchanges.domain.model

internal data class Exchange(
    val exchangeId: String,
    val name: String,
    val volume1hrsUsd: String,
    val volume1dayUsd: String,
    val volume1mthUsd: String,
    val website: String,
    val iconUrl: String,
)
