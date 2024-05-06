package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveOrderStore(
    @Json(name = "data")
    var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "status")
        var status: String?,
        @Json(name = "delivery")
        var delivery: String?,
        @Json(name = "notes")
        var notes: String?,
        @Json(name = "user_id")
        var userId: String?,
        @Json(name = "cart_id")
        var cartId: String?,
        @Json(name = "delivery_type")
        var deliveryType: String?,
        @Json(name = "number")
        var number: String?,
        @Json(name = "address_id")
        var address_id: String?,
        @Json(name = "total_qty")
        var totalQty: Int,
        @Json(name = "total_price_before_tax")
        var totalPriceBeforeTax: Int,
        @Json(name = "tax_val")
        var taxVal: String?,
        @Json(name = "total_price_after_tax")
        var totalPriceAfterTax: Int,
        @Json(name = "updated_at")
        var updatedAt: String?,
        @Json(name = "created_at")
        var createdAt: String?,
        @Json(name = "id")
        var id: Int,
        @Json(name = "assigned_user_id")
        var assignedUserId: Int,
        @Json(name = "payment_url")
        var paymentUrl: String?
    )
}