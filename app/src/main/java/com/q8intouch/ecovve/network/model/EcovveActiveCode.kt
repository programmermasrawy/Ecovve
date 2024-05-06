package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveActiveCode(
        @Json(name = "status")
        var status: String?,
        @Json(name = "message")
        var message: Message?
) {
    @JsonClass(generateAdapter = true)
    data class Message(
            @Json(name = "id")
            var id: Int?,
            @Json(name = "name")
            var name: String?,
            @Json(name = "email")
            var email: String?,
            @Json(name = "phone")
            var phone: String?,
            @Json(name = "avatar")
            var avatar: String?,
            @Json(name = "gender")
            var gender: String?,
            @Json(name = "birthday")
            var birthday: String?
    )
}