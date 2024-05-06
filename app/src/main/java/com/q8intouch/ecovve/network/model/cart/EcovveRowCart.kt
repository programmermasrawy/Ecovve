package com.q8intouch.ecovve.network.model.cart

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveRowCart(
    @Json(name = "data")
    var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "item")
        var item: List<Item>,
        @Json(name = "notes")
        var notes: String,
        @Json(name = "updated_at")
        var updatedAt: String,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "id")
        var id: Int
    ) {
        @JsonClass(generateAdapter = true)
        data class Item(
            @Json(name = "id")
            var id: String,
            @Json(name = "qty")
            var qty: String,
            @Json(name = "price_after_promotion")
            var priceAfterPromotion: String,
            @Json(name = "buy_one_get_one_offer")
            var buyOneGetOneOffer: String,
            @Json(name = "outlet_id")
            var outletId: String
        )
    }
}