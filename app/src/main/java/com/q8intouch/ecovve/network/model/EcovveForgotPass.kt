package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveForgotPass(
    @Json(name = "message")
    var message: String,
    @Json(name = "token")
    var token: String
)