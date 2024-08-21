package com.fappslab.mbchallenge.core.data.remote.model


import com.google.gson.annotations.SerializedName

internal data class ErrorResponse(
    @SerializedName("title") val title: String?,
    @SerializedName("status") val status: Int?,
    @SerializedName("detail") val detail: String?,
)
