package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveSearchFriend(
        @Json(name = "result")
        var result: List<Result>
) {
    @JsonClass(generateAdapter = true)
    data class Result(
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
            @Json(name = "blocked_from_cash")
            var blockedFromCash: Int,
            @Json(name = "email_active")
            var emailActive: Int,
            @Json(name = "email_verified_at")
            var emailVerifiedAt: Any?,
            @Json(name = "city_id")
            var cityId: Int,
            @Json(name = "all_time_habits")
            var allTimeHabits: List<Int>,
            @Json(name = "used_coupons")
            var usedCoupons: List<Int>,
            @Json(name = "sms_notifications")
            var smsNotifications: Int,
            @Json(name = "news_letter_subscription")
            var newsLetterSubscription: Int,
            @Json(name = "last_active")
            var lastActive: String,
            @Json(name = "created_at")
            var createdAt: String,
            @Json(name = "updated_at")
            var updatedAt: String,
            @Json(name = "deleted_at")
            var deletedAt: Any?
    )
}