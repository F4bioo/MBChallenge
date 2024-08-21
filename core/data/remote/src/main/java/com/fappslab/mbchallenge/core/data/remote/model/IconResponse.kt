package com.fappslab.mbchallenge.core.data.remote.model


import com.google.gson.annotations.SerializedName

internal data class IconResponse(
    @SerializedName("exchange_id") val exchangeId: String?,
    @SerializedName("url") val url: String?
)
