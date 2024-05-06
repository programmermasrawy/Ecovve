package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class EcovveReviewAll(
    @Json(name = "data")
    var `data`: List<Datareview>,
    @Json(name = "links")
    var links: Links,
    @Json(name = "meta")
    var meta: Meta
)

@JsonClass(generateAdapter = true)
data class Datareview(
    @Json(name = "id")
    var id: Int,
    @Json(name = "title")
    var title: String,
    @Json(name = "body")
    var body: String,
    @Json(name = "star")
    var star: String,
    @Json(name = "user_id")
    var userId: Int,
    @Json(name = "outlet_id")
    var outletId: Int,
    @Json(name = "created_at")
    var createdAt: String,
    @Json(name = "updated_at")
    var updatedAt: String
)
