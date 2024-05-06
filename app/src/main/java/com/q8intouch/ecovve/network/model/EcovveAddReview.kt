package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveAddReview(
    @Json(name = "data")
    var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "title")
        var title: String,
        @Json(name = "body")
        var body: String,
        @Json(name = "star")
        var star: String,
        @Json(name = "user_id")
        var userId: String,
        @Json(name = "outlet_id")
        var outletId: String,
        @Json(name = "updated_at")
        var updatedAt: String,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "id")
        var id: Int
    )
}