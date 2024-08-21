package com.fappslab.mbchallenge.core.domain.model

data class Exchange(
    val exchangeId: String,
    val name: String,
    val volume1dayUsd: Double,
    val website: String,
    val iconUrl: String,
)
