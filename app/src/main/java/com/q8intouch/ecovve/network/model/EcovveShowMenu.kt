package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveShowMenu(
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
        @Json(name = "image")
        var image: List<Any>,
        @Json(name = "brand_id")
        var brandId: Int,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "updated_at")
        var updatedAt: String,
        @Json(name = "category")
        var category: List<Any>,
        @Json(name = "brand")
        var brand: Brand,
        @Json(name = "item")
        var item: List<Item>
    ) {
        @JsonClass(generateAdapter = true)
        data class Brand(
            @Json(name = "id")
            var id: Int,
            @Json(name = "name")
            var name: String,
            @Json(name = "description")
            var description: String,
            @Json(name = "business_type")
            var businessType: String,
            @Json(name = "commission_annual")
            var commissionAnnual: String,
            @Json(name = "commission_order_fixed")
            var commissionOrderFixed: String,
            @Json(name = "commission_order_percentage")
            var commissionOrderPercentage: String,
            @Json(name = "logo")
            var logo: String,
            @Json(name = "phone")
            var phone: String,
            @Json(name = "address")
            var address: String,
            @Json(name = "has_delivery")
            var hasDelivery: String,
            @Json(name = "appears_in_search")
            var appearsInSearch: String,
            @Json(name = "minimum_charge")
            var minimumCharge: String,
            @Json(name = "website")
            var website: String,
            @Json(name = "extra_information")
            var extraInformation: String,
            @Json(name = "status")
            var status: String,
            @Json(name = "city_id")
            var cityId: Int,
            @Json(name = "brand_owner_id")
            var brandOwnerId: Int,
            @Json(name = "created_at")
            var createdAt: String,
            @Json(name = "updated_at")
            var updatedAt: String
        )

        @JsonClass(generateAdapter = true)
        data class Item(
            @Json(name = "id")
            var id: Int,
            @Json(name = "name")
            var name: String,
            @Json(name = "price")
            var price: String,
            @Json(name = "description")
            var description: String,
            @Json(name = "ingredient")
            var ingredient: List<Any>,
            @Json(name = "image")
            var image: String,
            @Json(name = "video")
            var video: String,
            @Json(name = "views_number")
            var viewsNumber: Int,
            @Json(name = "purchased_number")
            var purchasedNumber: Int,
            @Json(name = "brand_id")
            var brandId: Int,
            @Json(name = "category_id")
            var categoryId: Int,
            @Json(name = "menu_id")
            var menuId: Int,
            @Json(name = "promotion_id")
            var promotionId: Int,
            @Json(name = "created_at")
            var createdAt: String,
            @Json(name = "updated_at")
            var updatedAt: String
        )
    }
}