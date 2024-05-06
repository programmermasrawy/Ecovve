package com.q8intouch.ecovve.network.model
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class OutletsInAreaResponse(
    @Json(name = "data")
    var outletsInArea: List<OutletsInArea> = listOf(),
    @Json(name = "links")
    var links: Links = Links(),
    @Json(name = "meta")
    var meta: Meta = Meta()
)

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "first")
    var first: String = "",
    @Json(name = "last")
    var last: String = "",
    @Json(name = "prev")
    var prev: String = "",
    @Json(name = "next")
    var next: Any? = Any()
)

@JsonClass(generateAdapter = true)
data class Meta(
    @Json(name = "current_page")
    var currentPage: Int = 0,
    @Json(name = "from")
    var from: Int = 0,
    @Json(name = "last_page")
    var lastPage: Int = 0,
    @Json(name = "path")
    var path: String = "",
    @Json(name = "per_page")
    var perPage: Int = 0,
    @Json(name = "to")
    var to: Int = 0,
    @Json(name = "total")
    var total: Int = 0
)

@JsonClass(generateAdapter = true)
data class OutletsInArea(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "outlet_id")
    var outletId: Int = 0,
    @Json(name = "area_id")
    var areaId: Int = 0,
    @Json(name = "delivery_fee")
    var deliveryFee: String = "",
    @Json(name = "delivery_time")
    var deliveryTime: String = "",
    @Json(name = "delivery_availability")
    var deliveryAvailability: String = "",
    @Json(name = "created_at")
    var createdAt: String = "",
    @Json(name = "updated_at")
    var updatedAt: String = "",
    @Json(name = "area")
    var area: Area = Area(),
    @Json(name = "outlet")
    var outlet: Outlet = Outlet()
)

@JsonClass(generateAdapter = true)
data class Outlet(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "name")
    var name: String = "",
    @Json(name = "address")
    var address: String = "",
    @Json(name = "longitude")
    var longitude: Double = 0.0,
    @Json(name = "latitude")
    var latitude: Double = 0.0,
    @Json(name = "phone")
    var phone: String = "",
    @Json(name = "status")
    var status: String = "",
    @Json(name = "has_delivery")
    var hasDelivery: String = "",
    @Json(name = "tax")
    var tax: String = "",
    @Json(name = "capacity")
    var capacity: Int = 0,
    @Json(name = "opening")
    var opening: String = "",
    @Json(name = "closing")
    var closing: String = "",
    @Json(name = "location")
    var location: String = "",
    @Json(name = "city_id")
    var cityId: Int = 0,
    @Json(name = "brand_id")
    var brandId: Int = 0,
    @Json(name = "current_orders_number")
    var currentOrdersNumber: Int = 0,
    @Json(name = "created_at")
    var createdAt: String = "",
    @Json(name = "updated_at")
    var updatedAt: String = ""
)

@JsonClass(generateAdapter = true)
data class Area(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "name_ar")
    var nameAr: String = "",
    @Json(name = "name_en")
    var nameEn: String = "",
    @Json(name = "name_fr")
    var nameFr: String = "",
    @Json(name = "city_id")
    var cityId: Int = 0,
    @Json(name = "created_at")
    var createdAt: String = "",
    @Json(name = "updated_at")
    var updatedAt: String = ""
)