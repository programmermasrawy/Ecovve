package com.q8intouch.ecovve.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveSendOrderReview(
    @Json(name = "data")
    var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "seal")
        var seal: String,
        @Json(name = "delivery_time")
        var deliveryTime: String,
        @Json(name = "quality")
        var quality: String,
        @Json(name = "review")
        var review: String,
        @Json(name = "order_id")
        var orderId: String,
        @Json(name = "price_to_value")
        var priceToValue: String,
        @Json(name = "updated_at")
        var updatedAt: String,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "id")
        var id: Int
    )
}