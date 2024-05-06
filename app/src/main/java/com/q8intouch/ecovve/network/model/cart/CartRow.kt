package com.q8intouch.ecovve.network.model.cart

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartRow(
    @Json(name = "item")
    var item: List<Item>,
    @Json(name = "notes")
    var notes: String,
    @Json(name = "updated_at")
    var updated_at: String,
    @Json(name = "created_at")
    var created_at: String,
    @Json(name = "id")
    var id: String
)

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