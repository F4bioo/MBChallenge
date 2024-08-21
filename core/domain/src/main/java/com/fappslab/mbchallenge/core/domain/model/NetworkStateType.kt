package com.fappslab.mbchallenge.core.domain.model

enum class NetworkStateType(
    val isOnline: Boolean
) {
    Available(isOnline = true),
    Unavailable(isOnline = false),
    Lost(isOnline = false)
}
