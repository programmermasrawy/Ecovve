package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiftCardResponse(
    @Json(name = "data")
    var data: DataEntity
) {
    @JsonClass(generateAdapter = true)
    data class DataEntity(
        @Json(name = "id")
        var id: String,
        @Json(name = "name")
        var name: String,
        @Json(name = "description")
        var description: String,
        @Json(name = "amount")
        var amount: String,
        @Json(name = "status")
        var status: String,
        @Json(name = "expire_date")
        var expireDate: String,
        @Json(name = "giver_id")
        var giverId: Int,
        @Json(name = "taker_id")
        var takerId: Int,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "updated_at")
        var updatedAt: String
    )
}
