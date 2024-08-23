package com.fappslab.mbchallenge.core.data.remote.network.exception.model

import com.fappslab.mbchallenge.core.data.remote.model.ErrorType

class HttpThrowable(
    val errorType: ErrorType,
    message: String? = null,
    throwable: Throwable = Throwable(),
) : Throwable(message, throwable)
