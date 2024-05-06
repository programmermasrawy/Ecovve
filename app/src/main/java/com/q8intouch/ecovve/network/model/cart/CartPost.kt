package com.q8intouch.ecovve.network.model.cart

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class CartPost(
        var item: List<Item>,
        var notes: String
    ) {
        data class Item(
            var id: String,
            var qty: String,
            var price_after_promotion: String,
            var buy_one_get_one_offer: String,
            var outlet_id: String
            ,var extra : ArrayList<String>
        )
    }