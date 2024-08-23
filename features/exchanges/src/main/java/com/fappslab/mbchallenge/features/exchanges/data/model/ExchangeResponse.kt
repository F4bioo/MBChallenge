package com.fappslab.mbchallenge.features.exchanges.data.model


import com.google.gson.annotations.SerializedName

internal data class ExchangeResponse(
    @SerializedName("exchange_id") val exchangeId: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("volume_1hrs_usd") val volume1hrsUsd: Double?,
    @SerializedName("volume_1day_usd") val volume1dayUsd: Double?,
    @SerializedName("volume_1mth_usd") val volume1mthUsd: Double?,
    @SerializedName("website") val website: String?
)
