package com.q8intouch.ecovve.network.model

import androidx.room.Ignore
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "status")
    var status: String?,
    @Json(name = "data")
    var `data`: Data,
    @Json(name = "accessToken")
    var accessToken: String?,
    @Json(name = "token_type")
    var tokenType: String?,
    @Json(name = "expires_at")
    var expiresAt: String?
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        var id: Int?,
        @Json(name = "name")
        var name: String?,
        @Json(name = "email")
        var email: String?,
        @Json(name = "phone")
        var phone: String?
    )
}