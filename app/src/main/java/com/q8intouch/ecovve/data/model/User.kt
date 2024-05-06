package com.q8intouch.ecovve.data.model

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity
data class User(
    @Json(name = "id")
    var id: Int,
    @Json(name = "name")
    var name: String,
    @Json(name = "email")
    var email: String,
    @Json(name = "phone")
    var phone: String,
    @Json(name = "avatar")
    var avatar: String,
    @Json(name = "area")
    var area: String,
    @Json(name = "address")
    var address: List<Any>,
    @Json(name = "gender")
    var gender: String,
    @Json(name = "birthday")
    var birthday: String,
    @Json(name = "free_credit")
    var freeCredit: Int,
    @Json(name = "social_media")
    var socialMedia: List<Any>,
    @Json(name = "verification_code")
    var verificationCode: String,
    @Json(name = "active")
    var active: Int,
    @Json(name = "city_id")
    var cityId: Int,
    @Json(name = "all_time_habits")
    var allTimeHabits: List<Any>,
    @Json(name = "used_coupons")
    var usedCoupons: List<Any>,
    @Json(name = "created_at")
    var createdAt: String,
    @Json(name = "updated_at")
    var updatedAt: String,
    @Json(name = "deleted_at")
    var deletedAt: Any?
)