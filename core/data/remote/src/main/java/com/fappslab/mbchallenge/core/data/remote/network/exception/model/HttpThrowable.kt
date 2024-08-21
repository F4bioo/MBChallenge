package com.fappslab.mbchallenge.core.data.remote.network.exception.model

class HttpThrowable(
    val title: String? = null,
    val status: Int? = null,
    val detail: String? = null,
    message: String?,
    throwable: Throwable
) : Throwable(message, throwable)
