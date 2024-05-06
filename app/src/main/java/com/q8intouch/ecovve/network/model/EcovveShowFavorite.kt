package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveShowFavorite(
    @Json(name = "favourite")
    var favourite: Favourite,
    @Json(name = "items")
    var items: String
) {
    @JsonClass(generateAdapter = true)
    data class Favourite(
        @Json(name = "id")
        var id: Int,
        @Json(name = "name")
        var name: String,
        @Json(name = "item")
        var item: List<Any>,
        @Json(name = "description")
        var description: String,
        @Json(name = "user_id")
        var userId: Int,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "updated_at")
        var updatedAt: String,
        @Json(name = "user")
        var user: User
    ) {
        @JsonClass(generateAdapter = true)
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
            @Json(name = "gender")
            var gender: String,
            @Json(name = "birthday")
            var birthday: String,
            @Json(name = "points")
            var points: Int,
            @Json(name = "free_credit")
            var freeCredit: String,
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
    }
}