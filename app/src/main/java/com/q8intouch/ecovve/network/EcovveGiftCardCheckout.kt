package com.q8intouch.ecovve.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveGiftCardCheckout(
        @Json(name = "data")
        var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
            @Json(name = "name")
            var name: String?,
            @Json(name = "description")
            var description: String?,
            @Json(name = "amount")
            var amount: String?,
            @Json(name = "expire_date")
            var expireDate: String?,
            @Json(name = "status")
            var status: String?,
            @Json(name = "giver_id")
            var giverId: Int?,
            @Json(name = "taker_id")
            var takerId: String?,
            @Json(name = "updated_at")
            var updatedAt: String?,
            @Json(name = "created_at")
            var createdAt: String?,
            @Json(name = "id")
            var id: Int?,
            @Json(name = "payment_url")
            var paymentUrl: String?
    )
}