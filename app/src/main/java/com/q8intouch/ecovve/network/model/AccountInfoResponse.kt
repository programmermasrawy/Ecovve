package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountInfoResponse(
    @Json(name = "data")
    var data: DataEntity
) {
    @JsonClass(generateAdapter = true)
    data class DataEntity(
        @Json(name = "updated_at")
        var updatedAt: String,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "used_coupons")
        var usedCoupons: List<String>,
        @Json(name = "all_time_habits")
        var allTimeHabits: List<String>,
        @Json(name = "city_id")
        var cityId: Int,
        @Json(name = "active")
        var active: Int,
        @Json(name = "verification_code")
        var verificationCode: String,
        @Json(name = "social_media")
        var socialMedia: List<String>,
        @Json(name = "free_credit")
        var freeCredit: String,
        @Json(name = "points")
        var points: Int,
        @Json(name = "birthday")
        var birthday: String,
        @Json(name = "gender")
        var gender: String,
        @Json(name = "area")
        var area: String,
        @Json(name = "avatar")
        var avatar: String,
        @Json(name = "phone")
        var phone: String,
        @Json(name = "email")
        var email: String,
        @Json(name = "name")
        var name: String,
        @Json(name = "id")
        var id: Int
    )

}
