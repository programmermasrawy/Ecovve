package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json

data class OrdersResponse(
    @Json(name = "current_page")
    val currentPage: Int,
    @Json(name = "data")
    val data: List<Data>
) {
    data class Data(
        @Json(name = "address")
        val address: String,
        @Json(name = "cart_id")
        val cartId: Int,
        @Json(name = "coupon_code")
        val couponCode: String,
        @Json(name = "created_at")
        val createdAt: String,
        @Json(name = "deliver_time")
        val deliverTime: String,
        @Json(name = "delivery")
        val delivery: String,
        @Json(name = "gift_card_id")
        val giftCardId: Int,
        @Json(name = "id")
        val id: Int,
        @Json(name = "notes")
        val notes: String,
        @Json(name = "number")
        val number: String,
        @Json(name = "paid")
        val paid: String,
        @Json(name = "payment_option")
        val paymentOption: String,
        @Json(name = "status")
        val status: String,
        @Json(name = "suborder")
        val suborder: List<Any>,
        @Json(name = "tax_val")
        val taxVal: String,
        @Json(name = "total_price_after_tax")
        val totalPriceAfterTax: String,
        @Json(name = "total_price_before_tax")
        val totalPriceBeforeTax: String,
        @Json(name = "total_qty")
        val totalQty: Int,
        @Json(name = "updated_at")
        val updatedAt: String,
        @Json(name = "user_free_credit")
        val userFreeCredit: String,
        @Json(name = "user_id")
        val userId: Int
    )
}