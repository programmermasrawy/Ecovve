package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json

data class GoogleResponse(
        @Json(name = "access_token")
        val access_token: String,
        @Json(name = "expires_in")
        val expires_in: String,
        @Json(name = "scope")
        val scope: String
        ,@Json(name = "token_type")
        val token_type: String
        ,@Json(name = "id_token")
        val id_token: String
)
