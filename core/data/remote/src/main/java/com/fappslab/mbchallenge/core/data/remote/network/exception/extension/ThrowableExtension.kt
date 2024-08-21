package com.fappslab.mbchallenge.core.data.remote.network.exception.extension

import com.fappslab.mbchallenge.core.data.remote.model.ErrorResponse
import com.fappslab.mbchallenge.core.data.remote.network.exception.model.HttpThrowable
import com.fappslab.mbchallenge.core.data.remote.network.exception.model.InternetThrowable
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

// Only for unit tests
private const val UNEXPECTED_ERROR_MESSAGE = "Unexpected error, please try again."

private fun HttpException.getCause(errorBody: String?): Throwable =
    getErrorResponse(errorBody)?.let {
        HttpThrowable(
            title = it.title,
            status = it.status,
            detail = it.detail,
            message = message,
            throwable = this
        )
    } ?: HttpThrowable(
        message = UNEXPECTED_ERROR_MESSAGE,
        throwable = this
    )

private fun getErrorResponse(errorBody: String?): ErrorResponse? =
    Gson().fromJson(errorBody, ErrorResponse::class.java)

private fun HttpException.parseError(): Throwable =
    getCause(errorBody = response()?.errorBody()?.string())

internal fun Throwable.parseHttpError(): Throwable =
    when (this) {
        is HttpException -> parseError()
        is UnknownHostException,
        is SocketTimeoutException,
        is IOException -> InternetThrowable()

        else -> this
    }
