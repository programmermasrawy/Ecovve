package com.q8intouch.ecovve.util.extension

import com.github.leonardoxh.livedatacalladapter.Resource
import com.squareup.moshi.Moshi
import com.q8intouch.ecovve.network.model.ErrorResponse
import retrofit2.HttpException


fun <T> Resource<T>.errorResponse(): ErrorResponse {
    val exception = this.error
    return if (exception is HttpException)
        exception.errorResponse()
    else
        ErrorResponse(exception!!.message!!)
}

fun HttpException.errorResponse(): ErrorResponse {
    return makeErrorResponseObject(this)
}

private fun makeErrorResponseObject(httpException: HttpException): ErrorResponse {
    val errorJson = httpException.response().errorBody()!!.source()
    val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter(ErrorResponse::class.java)
    var response = adapter.fromJson(errorJson)
    if (response == null) {
        response = ErrorResponse("Unknown Error")
    }
    response.responseCode = httpException.code()
    response.responseDescription = httpException.response().errorBody().toString()
    return response
}