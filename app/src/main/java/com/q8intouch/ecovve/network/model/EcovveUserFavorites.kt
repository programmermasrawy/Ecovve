package com.q8intouch.ecovve.network.model

import androidx.room.Ignore
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveUserFavorites(
    @Json(name = "data")
    var `data`: List<Data>
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        var id:Int?,
        @Json(name = "name")
        var name: String?,
        @Json(name = "email")
        var email: String?,
        @Json(name = "phone")
        var phone: String?,
        @Json(name = "avatar")
        var avatar: String?,
        @Json(name = "area")
        var area: String?,
        @Json(name = "gender")
        var gender: String?,
        @Json(name = "birthday")
        var birthday: String?,
        @Json(name = "points")
        var points:Int?,
        @Json(name = "free_credit")
        var freeCredit: String?,
        @Json(name = "verification_code")
        var verificationCode: String?,
        @Json(name = "active")
        var active:Int?,
        @Json(name = "blocked_from_cash")
        var blockedFromCash:Int?,
        @Json(name = "email_active")
        var emailActive:Int?,
        @Json(name = "email_verified_at")
        var emailVerifiedAt: String?,
        @Json(name = "city_id")
        var cityId:Int?,
        @Json(name = "favorites")
        var favorites: List<Favorite>?
    ) {
        @JsonClass(generateAdapter = true)
        data class Favorite(
            @Json(name = "id")
            var id:Int?,
            @Json(name = "price")
            var price: Float?,
            @Json(name = "image")
            var image: List<String>,
            @Json(name = "video")
            var video: String?,
            @Json(name = "views_number")
            var viewsNumber:Int?,
            @Json(name = "purchased_number")
            var purchasedNumber:Int?,
            @Json(name = "brand_id")
            var brandId:Int?,
            @Json(name = "category_id")
            var categoryId:Int?,
            @Json(name = "menu_id")
            var menuId:Int?,
            @Json(name = "promotion_id")
            var promotionId:Int?,
            @Json(name = "reviews_rating")
            var reviewsRating: Double?,
            @Json(name = "reviews_count")
            var reviewsCount:Int?,
            @Json(name = "created_at")
            var createdAt: String?,
            @Json(name = "updated_at")
            var updatedAt: String?,
            @Json(name = "is_favorite")
            var isFavorite: Boolean,
            @Json(name = "name")
            var name: String?,
            @Json(name = "description")
            var description: String?,
            @Json(name = "brand")
            var brand: Brand?
        ) {
            @JsonClass(generateAdapter = true)
            data class Brand(
                @Json(name = "id")
                var id:Int?,
                @Json(name = "logo")
                var logo: String?,
                @Json(name = "address")
                var address: String?,
                @Json(name = "name")
                var name: String?,
                @Json(name = "description")
                var description: String?
            )


        }


    }
}