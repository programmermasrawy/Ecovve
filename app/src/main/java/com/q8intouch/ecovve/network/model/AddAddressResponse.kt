package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddAddressResponse(
    @Json(name = "message")
    var message: String,
    @Json(name = "data")
    var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "name")
        var name: String,
        @Json(name = "phone")
        var phone: String,
        @Json(name = "area_id")
        var area_id: String,
        @Json(name = "block")
        var block: String,
        @Json(name = "parcel")
        var parcel: String,
        @Json(name = "building")
        var building: String,
        @Json(name = "floor")
        var floor: String,
        @Json(name = "additional")
        var additional: String,
        @Json(name = "street")
        var street: String,
        @Json(name = "lat")
        var lat: String,
        @Json(name = "lng")
        var lng: String,
        @Json(name = "user_id")
        var userId: Int,
        @Json(name = "updated_at")
        var updatedAt: String,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "id")
        var id: Int
    )
}