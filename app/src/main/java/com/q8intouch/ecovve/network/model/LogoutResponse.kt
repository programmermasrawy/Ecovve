package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LogoutResponse(
    @Json(name = "status")
    var status: String,
    @Json(name = "message")
    var message: String
)