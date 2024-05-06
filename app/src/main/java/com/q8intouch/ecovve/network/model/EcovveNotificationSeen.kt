package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveNotificationSeen(
    @Json(name = "data")
    var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        var id: Int,
        @Json(name = "name")
        var name: String,
        @Json(name = "type")
        var type: String,
        @Json(name = "content")
        var content: String,
        @Json(name = "seen")
        var seen: String,
        @Json(name = "send_to")
        var sendTo: List<String>,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "updated_at")
        var updatedAt: String
    )
}