package com.fappslab.mbchallenge.core.domain.model

data class Exchange(
    val exchangeId: String,
    val name: String,
    val volume1hrsUsd: String,
    val volume1dayUsd: String,
    val volume1mthUsd: String,
    val website: String,
    val iconUrl: String,
)
