package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveChatRoomUsers(
    @Json(name = "data")
    var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        var id: Int,
        @Json(name = "name")
        var name: String,
        @Json(name = "description")
        var description: String,
        @Json(name = "deleted_at")
        var deletedAt: String?,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "updated_at")
        var updatedAt: String,
        @Json(name = "users")
        var users: List<User>
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
            var socialMedia: List<String>,
            @Json(name = "verification_code")
            var verificationCode: String,
            @Json(name = "active")
            var active: Int,
            @Json(name = "email_verified_at")
            var emailVerifiedAt: Any?,
            @Json(name = "city_id")
            var cityId: Int,
            @Json(name = "all_time_habits")
            var allTimeHabits: List<Int>,
            @Json(name = "used_coupons")
            var usedCoupons: List<Int>,
            @Json(name = "created_at")
            var createdAt: String,
            @Json(name = "updated_at")
            var updatedAt: String,
            @Json(name = "deleted_at")
            var deletedAt: String?,
            @Json(name = "pivot")
            var pivot: Pivot
        ) {
            @JsonClass(generateAdapter = true)
            data class Pivot(
                @Json(name = "chat_room_id")
                var chatRoomId: Int,
                @Json(name = "user_id")
                var userId: Int,
                @Json(name = "created_at")
                var createdAt: String,
                @Json(name = "updated_at")
                var updatedAt: String
            )
        }
    }
}