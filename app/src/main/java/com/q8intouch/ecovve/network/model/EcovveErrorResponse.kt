package com.q8intouch.ecovve.network.model
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "message")
    var message: String = "",
    var responseCode:Int = 0,
    var responseDescription: String =""
)