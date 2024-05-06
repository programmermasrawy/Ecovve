package com.q8intouch.ecovve.data.model

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity
data class Outlet(
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