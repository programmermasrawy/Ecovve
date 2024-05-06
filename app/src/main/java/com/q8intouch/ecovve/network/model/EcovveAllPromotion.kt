package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveAllPromotion(
    @Json(name = "data")
    var `data`: List<Data>,
    @Json(name = "links")
    var links: Links,
    @Json(name = "meta")
    var meta: Meta
) {
    @JsonClass(generateAdapter = true)
    data class Meta(
        @Json(name = "current_page")
        var currentPage: Int?,
        @Json(name = "from")
        var from: Int?,
        @Json(name = "last_page")
        var lastPage: Int?,
        @Json(name = "path")
        var path: String?,
        @Json(name = "per_page")
        var perPage: Int?,
        @Json(name = "to")
        var to: Int?,
        @Json(name = "total")
        var total: Int?
    )

    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        var id: Int?,
        @Json(name = "name")
        var name: String?,
        @Json(name = "description")
        var description: String?,
        @Json(name = "type")
        var type: String?,
        @Json(name = "buy_one_get_one_offer")
        var buyOneGetOneOffer: String?,
        @Json(name = "value")
        var value: String?,
        @Json(name = "expire_date")
        var expireDate: String?,
        @Json(name = "image")
        var image: String?,
        @Json(name = "created_at")
        var createdAt: String?,
        @Json(name = "updated_at")
        var updatedAt: String?,
        @Json(name = "item")
        var item: List<Item>
    ) {
        @JsonClass(generateAdapter = true)
        data class Item(
            @Json(name = "id")
            var id: Int?,
            @Json(name = "price")
            var price: Float?,
            @Json(name = "image")
            var image: List<String?>,
            @Json(name = "views_number")
            var viewsNumber: Int?,
            @Json(name = "purchased_number")
            var purchasedNumber: Int?,
            @Json(name = "brand_id")
            var brandId: Int?,
            @Json(name = "category_id")
            var categoryId: Int?,
            @Json(name = "menu_id")
            var menuId: Int?,
            @Json(name = "promotion_id")
            var promotionId: Int?,
            @Json(name = "reviews_rating")
            var reviewsRating: Double?,
            @Json(name = "reviews_count")
            var reviewsCount: Int?,
            @Json(name = "created_at")
            var createdAt: String?,
            @Json(name = "updated_at")
            var updatedAt: String?,
            @Json(name = "is_favorite")
            var isFavorite: Boolean,
            @Json(name = "name")
            var name: String?,
            @Json(name = "description")
            var description: String?,
            @Json(name = "brand")
            var brand: Brand
        ) {
            @JsonClass(generateAdapter = true)
            data class Brand(
                @Json(name = "id")
                var id: Int?,
                @Json(name = "business_type")
                var businessType: String?,
                @Json(name = "commission_annual")
                var commissionAnnual: String?,
                @Json(name = "commission_order_fixed")
                var commissionOrderFixed: String?,
                @Json(name = "commission_order_percentage")
                var commissionOrderPercentage: String?,
                @Json(name = "logo")
                var logo: String?,
                @Json(name = "cover")
                var cover: String?,
                @Json(name = "phone")
                var phone: String?,
                @Json(name = "address")
                var address: String?,
                @Json(name = "has_delivery")
                var hasDelivery: String?,
                @Json(name = "appears_in_search")
                var appearsInSearch: String?,
                @Json(name = "minimum_charge")
                var minimumCharge: String?,
                @Json(name = "website")
                var website: String?,
                @Json(name = "extra_information")
                var extraInformation: String?,
                @Json(name = "status")
                var status: String?,
                @Json(name = "city_id")
                var cityId: Int?,
                @Json(name = "brand_owner_id")
                var brandOwnerId: Int?,
                @Json(name = "supports_knet")
                var supportsKnet: Int?,
                @Json(name = "supports_cc")
                var supportsCc: Int?,
                @Json(name = "reviews_rating")
                var reviewsRating: Double?,
                @Json(name = "reviews_count")
                var reviewsCount: Int?,
                @Json(name = "created_at")
                var createdAt: String?,
                @Json(name = "updated_at")
                var updatedAt: String?,
                @Json(name = "deleted_at")
                var deletedAt: String?,
                @Json(name = "name")
                var name: String?,
                @Json(name = "description")
                var description: String
            )
        }
    }

    @JsonClass(generateAdapter = true)
    data class Links(
        @Json(name = "first")
        var first: String?,
        @Json(name = "last")
        var last: String?,
        @Json(name = "prev")
        var prev: String?,
        @Json(name = "next")
        var next: String?
    )
}