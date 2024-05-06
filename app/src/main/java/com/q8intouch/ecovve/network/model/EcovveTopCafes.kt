package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveTopCafes(
        @Json(name = "data")
        var `data`: List<Data> = listOf()
) {
    @JsonClass(generateAdapter = true)
    data class Data(
            @Json(name = "id")
            var id: Int?= 0,
            @Json(name = "business_type")
            var businessType: String?= "",
            @Json(name = "commission_annual")
            var commissionAnnual: String?= "",
            @Json(name = "commission_order_fixed")
            var commissionOrderFixed: String?= "",
            @Json(name = "commission_order_percentage")
            var commissionOrderPercentage: String?= "",
            @Json(name = "logo")
            var logo: String?= "",
            @Json(name = "cover")
            var cover: String?= "",
            @Json(name = "phone")
            var phone: String?= "",
            @Json(name = "address")
            var address: String?= "",
            @Json(name = "has_delivery")
            var hasDelivery: String?= "",
            @Json(name = "appears_in_search")
            var appearsInSearch: String?= "",
            @Json(name = "minimum_charge")
            var minimumCharge: String?= "",
            @Json(name = "website")
            var website: String?= "",
            @Json(name = "extra_information")
            var extraInformation: String?= "",
            @Json(name = "status")
            var status: String?= "",
            @Json(name = "city_id")
            var cityId: Int?= 0,
            @Json(name = "brand_owner_id")
            var brandOwnerId: Int?= 0,
            @Json(name = "supports_knet")
            var supportsKnet: Int?= 0,
            @Json(name = "supports_cc")
            var supportsCc: Int?= 0,
            @Json(name = "reviews_rating")
            var reviewsRating: Double?= 0.0,
            @Json(name = "reviews_count")
            var reviewsCount: Int?= 0,
            @Json(name = "created_at")
            var createdAt: String?= "",
            @Json(name = "updated_at")
            var updatedAt: String?= "",
            @Json(name = "deleted_at")
            var deletedAt: String? = "",
            @Json(name = "name")
            var name: String?= "",
            @Json(name = "description")
            var description: String?= "",
            var outlets : List<Outlets>?
    )
    {
            data class Outlets(
                    var id: Int?, // 10
                    var name: String?
            )
    }
}