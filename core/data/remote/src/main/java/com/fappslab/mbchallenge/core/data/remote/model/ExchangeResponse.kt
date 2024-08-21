package com.fappslab.mbchallenge.core.data.remote.model


import com.google.gson.annotations.SerializedName

internal data class ExchangeResponse(
    @SerializedName("exchange_id") val exchangeId: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("volume_1day_usd") val volume1dayUsd: Double?,
    @SerializedName("website") val website: String?
)
