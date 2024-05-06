package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveOutletArea(
        @Json(name = "data")
        var `data`: Data = Data()
) {
    @JsonClass(generateAdapter = true)
    data class Data(
            @Json(name = "id")
            var id: Int?= 0,
            @Json(name = "address")
            var address: String? = "",
            @Json(name = "longitude")
            var longitude: Double = 0.0,
            @Json(name = "latitude")
            var latitude: Double = 0.0,
            @Json(name = "phone")
            var phone: String? = "",
            @Json(name = "status")
            var status: String? = "",
            @Json(name = "has_delivery")
            var hasDelivery: String? = "",
            @Json(name = "tax")
            var tax: String? = "",
            @Json(name = "capacity")
            var capacity: Int?= 0,
            @Json(name = "opening")
            var opening: String? = "",
            @Json(name = "closing")
            var closing: String? = "",
            @Json(name = "location")
            var location: String? = "",
            @Json(name = "outlet_owner_id")
            var outletOwnerId: Int?= 0,
            @Json(name = "city_id")
            var cityId: Int?= 0,
            @Json(name = "brand_id")
            var brandId: Int?= 0,
            @Json(name = "current_orders_number")
            var currentOrdersNumber: Int?= 0,
            @Json(name = "area_id")
            var areaId: Int?= 0,
            @Json(name = "avg_delivery_time")
            var avgDeliveryTime: Int?= 0,
            @Json(name = "avg_delivery_fee")
            var avgDeliveryFee: String? = "",
            @Json(name = "reviews_rating")
            var reviewsRating: Double? = 0.0,
            @Json(name = "reviews_count")
            var reviewsCount: Int?= 0,
            @Json(name = "deleted_at")
            var deletedAt: Any? = Any(),
            @Json(name = "created_at")
            var createdAt: String? = "",
            @Json(name = "updated_at")
            var updatedAt: String? = "",
            @Json(name = "is_open")
            var isOpen: Boolean = false,
            @Json(name = "name")
            var name: String? = "",
            @Json(name = "area")
            var area: Area = Area(),
            @Json(name = "delivery_area")
            var deliveryArea: List<DeliveryArea> = listOf(),
            @Json(name = "brand")
            var brand: Brand = Brand()
    ) {
        @JsonClass(generateAdapter = true)
        data class DeliveryArea(
                @Json(name = "id")
                var id: Int?= 0,
                @Json(name = "name_ar")
                var nameAr: String? = "",
                @Json(name = "name_en")
                var nameEn: String? = "",
                @Json(name = "city_id")
                var cityId: Int?= 0,
                @Json(name = "created_at")
                var createdAt: String? = "",
                @Json(name = "updated_at")
                var updatedAt: String? = ""
        ) {

        }


        @JsonClass(generateAdapter = true)
        data class Area(
                @Json(name = "id")
                var id: Int?= 0,
                @Json(name = "name_ar")
                var nameAr: String? = "",
                @Json(name = "name_en")
                var nameEn: String? = "",
                @Json(name = "city_id")
                var cityId: Int?= 0,
                @Json(name = "created_at")
                var createdAt: String? = "",
                @Json(name = "updated_at")
                var updatedAt: String? = "",
                @Json(name = "city")
                var city: City = City()
        ) {
            @JsonClass(generateAdapter = true)
            data class City(
                    @Json(name = "id")
                    var id: Int?= 0,
                    @Json(name = "name_ar")
                    var nameAr: String? = "",
                    @Json(name = "name_en")
                    var nameEn: String? = "",
                    @Json(name = "name_fr")
                    var nameFr: String? = "",
                    @Json(name = "country_id")
                    var countryId: Int?= 0
            )
        }


        @JsonClass(generateAdapter = true)
        data class Brand(
                @Json(name = "id")
                var id: Int?= 0,
                @Json(name = "business_type")
                var businessType: String? = "",
                @Json(name = "commission_annual")
                var commissionAnnual: String? = "",
                @Json(name = "commission_order_fixed")
                var commissionOrderFixed: String? = "",
                @Json(name = "commission_order_percentage")
                var commissionOrderPercentage: String? = "",
                @Json(name = "logo")
                var logo: String? = "",
                @Json(name = "cover")
                var cover: String? = "",
                @Json(name = "phone")
                var phone: String? = "",
                @Json(name = "address")
                var address: String? = "",
                @Json(name = "has_delivery")
                var hasDelivery: String? = "",
                @Json(name = "appears_in_search")
                var appearsInSearch: String? = "",
                @Json(name = "minimum_charge")
                var minimumCharge: String? = "",
                @Json(name = "website")
                var website: String? = "",
                @Json(name = "extra_information")
                var extraInformation: String? = "",
                @Json(name = "status")
                var status: String? = "",
                @Json(name = "city_id")
                var cityId: Int?= 0,
                @Json(name = "brand_owner_id")
                var brandOwnerId: Int?= 0,
                @Json(name = "supports_knet")
                var supportsKnet: Int?= 0,
                @Json(name = "supports_cc")
                var supportsCc: Int?= 0,
                @Json(name = "reviews_rating")
                var reviewsRating: Double? = 0.0,
                @Json(name = "reviews_count")
                var reviewsCount: Int?= 0
        )
    }
}