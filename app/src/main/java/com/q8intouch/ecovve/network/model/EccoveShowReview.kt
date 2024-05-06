package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EccoveShowReview(
    @Json(name = "data")
    var `data`: Data
)


@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "id")
    var id: Int,
    @Json(name = "title")
    var title: String,
    @Json(name = "body")
    var body: String,
    @Json(name = "star")
    var star: String,
    @Json(name = "user_id")
    var userId: Int,
    @Json(name = "outlet_id")
    var outletId: Int,
    @Json(name = "created_at")
    var createdAt: String,
    @Json(name = "updated_at")
    var updatedAt: String,
    @Json(name = "user")
    var user: User,
    @Json(name = "outlet")
    var outlet: Outlet1
)

@JsonClass(generateAdapter = true)
data class Outlet1(
    @Json(name = "id")
    var id: Int,
    @Json(name = "name")
    var name: String,
    @Json(name = "address")
    var address: String,
    @Json(name = "longitude")
    var longitude: Double,
    @Json(name = "latitude")
    var latitude: Double,
    @Json(name = "phone")
    var phone: String,
    @Json(name = "status")
    var status: String,
    @Json(name = "has_delivery")
    var hasDelivery: String,
    @Json(name = "tax")
    var tax: String,
    @Json(name = "capacity")
    var capacity: Int,
    @Json(name = "opening")
    var opening: String,
    @Json(name = "closing")
    var closing: String,
    @Json(name = "location")
    var location: String,
    @Json(name = "city_id")
    var cityId: Int,
    @Json(name = "brand_id")
    var brandId: Int,
    @Json(name = "current_orders_number")
    var currentOrdersNumber: Int,
    @Json(name = "area_id")
    var areaId: Int,
    @Json(name = "created_at")
    var createdAt: String,
    @Json(name = "updated_at")
    var updatedAt: String
)