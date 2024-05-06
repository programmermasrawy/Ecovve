package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveOfferInfo(
    @Json(name = "data")
    var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        var id: Int,
        @Json(name = "name")
        var name: String,
        @Json(name = "description")
        var description: String,
        @Json(name = "type")
        var type: String,
        @Json(name = "buy_one_get_one_offer")
        var buyOneGetOneOffer: String,
        @Json(name = "percentage")
        var percentage: String,
        @Json(name = "expire_date")
        var expireDate: String,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "updated_at")
        var updatedAt: String,
        @Json(name = "item")
        var item: List<Any>
    )
}