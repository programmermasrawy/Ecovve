package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json

data class PromotionResponse(
    @Json(name = "data")
    val `data`: Data
) {
    data class Data(
        @Json(name = "buy_one_get_one_offer")
        val buyOneGetOneOffer: String,
        @Json(name = "created_at")
        val createdAt: String,
        @Json(name = "description")
        val description: String,
        @Json(name = "expire_date")
        val expireDate: String,
        @Json(name = "id")
        val id: Int,
        @Json(name = "item")
        val item: List<Item>,
        @Json(name = "name")
        val name: String,
        @Json(name = "percentage")
        val percentage: String,
        @Json(name = "type")
        val type: String,
        @Json(name = "updated_at")
        val updatedAt: String
    ) {
        data class Item(
            @Json(name = "brand")
            val brand: Brand,
            @Json(name = "brand_id")
            val brandId: Int,
            @Json(name = "category_id")
            val categoryId: Int,
            @Json(name = "created_at")
            val createdAt: String,
            @Json(name = "description")
            val description: String,
            @Json(name = "id")
            val id: Int,
            @Json(name = "image")
            val image: String,
            @Json(name = "ingredient")
            val ingredient: List<String>,
            @Json(name = "menu_id")
            val menuId: Int,
            @Json(name = "name")
            val name: String,
            @Json(name = "price")
            val price: String,
            @Json(name = "promotion_id")
            val promotionId: Int,
            @Json(name = "purchased_number")
            val purchasedNumber: Int,
            @Json(name = "updated_at")
            val updatedAt: String,
            @Json(name = "video")
            val video: String,
            @Json(name = "views_number")
            val viewsNumber: Int
        ) {
            data class Brand(
                @Json(name = "address")
                val address: String,
                @Json(name = "appears_in_search")
                val appearsInSearch: String,
                @Json(name = "brand_owner_id")
                val brandOwnerId: Int,
                @Json(name = "business_type")
                val businessType: String,
                @Json(name = "city_id")
                val cityId: Int,
                @Json(name = "commission_annual")
                val commissionAnnual: String,
                @Json(name = "commission_order_fixed")
                val commissionOrderFixed: String,
                @Json(name = "commission_order_percentage")
                val commissionOrderPercentage: String,
                @Json(name = "created_at")
                val createdAt: String,
                @Json(name = "description")
                val description: String,
                @Json(name = "extra_information")
                val extraInformation: String,
                @Json(name = "has_delivery")
                val hasDelivery: String,
                @Json(name = "id")
                val id: Int,
                @Json(name = "logo")
                val logo: String,
                @Json(name = "minimum_charge")
                val minimumCharge: String,
                @Json(name = "name")
                val name: String,
                @Json(name = "phone")
                val phone: String,
                @Json(name = "status")
                val status: String,
                @Json(name = "updated_at")
                val updatedAt: String,
                @Json(name = "website")
                val website: String
            )
        }
    }
}