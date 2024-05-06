package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json

data class OrderResponse(
    @Json(name = "data")
    val `data`: Data
) {
    data class Data(
        @Json(name = "address")
        val address: Any,
        @Json(name = "cart")
        val cart: Cart,
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
        @Json(name = "user")
        val user: User,
        @Json(name = "user_free_credit")
        val userFreeCredit: String,
        @Json(name = "user_id")
        val userId: Int
    ) {
        data class User(
            @Json(name = "active")
            val active: Int,
            @Json(name = "all_time_habits")
            val allTimeHabits: List<Int>,
            @Json(name = "area")
            val area: String,
            @Json(name = "avatar")
            val avatar: String,
            @Json(name = "birthday")
            val birthday: String,
            @Json(name = "city_id")
            val cityId: Int,
            @Json(name = "created_at")
            val createdAt: String,
            @Json(name = "deleted_at")
            val deletedAt: Any,
            @Json(name = "email")
            val email: String,
            @Json(name = "email_verified_at")
            val emailVerifiedAt: Any,
            @Json(name = "free_credit")
            val freeCredit: String,
            @Json(name = "gender")
            val gender: String,
            @Json(name = "id")
            val id: Int,
            @Json(name = "name")
            val name: String,
            @Json(name = "phone")
            val phone: String,
            @Json(name = "points")
            val points: Int,
            @Json(name = "social_media")
            val socialMedia: List<String>,
            @Json(name = "updated_at")
            val updatedAt: String,
            @Json(name = "used_coupons")
            val usedCoupons: List<Int>,
            @Json(name = "verification_code")
            val verificationCode: String
        )

        data class Cart(
            @Json(name = "created_at")
            val createdAt: String,
            @Json(name = "id")
            val id: Int,
            @Json(name = "item")
            val item: List<Item>,
            @Json(name = "notes")
            val notes: String,
            @Json(name = "updated_at")
            val updatedAt: String
        ) {
            data class Item(
                @Json(name = "buy_one_get_one_offer")
                val buyOneGetOneOffer: String,
                @Json(name = "id")
                val id: String,
                @Json(name = "outlet_id")
                val outletId: String,
                @Json(name = "price_after_promotion")
                val priceAfterPromotion: String,
                @Json(name = "qty")
                val qty: String
            )
        }
    }
}