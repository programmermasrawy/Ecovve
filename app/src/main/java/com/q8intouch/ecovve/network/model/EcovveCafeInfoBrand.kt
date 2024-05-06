package com.q8intouch.ecovve.network.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveCafeInfoBrand(
        @Json(name = "data")
        var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
            @Json(name = "id")
            var id: Int = 0,
            @Json(name = "menus")
            var menus: Menus = Menus(),
            var items : Items
    ) {
        @JsonClass(generateAdapter = true)
        data class Menus(
                @Json(name = "current_page")
                var currentPage: Int = 0,
                @Json(name = "data")
                var `data`: List<DataMenus> = listOf()
        ) : Parcelable {
            constructor(parcel: Parcel) : this(
                    parcel.readInt(),
                    TODO("data")) {
            }

            override fun writeToParcel(p0: Parcel?, p1: Int) {
               p0!!.writeInt(currentPage)
            }

            override fun describeContents(): Int {
return 0            }

            @JsonClass(generateAdapter = true)
            data class DataMenus(
                    @Json(name = "id")
                    var id: Int? = 0,
                    @Json(name = "brand_id")
                    var brandId: Int? = 0,
                    @Json(name = "created_at")
                    var createdAt: String? = "",
                    @Json(name = "updated_at")
                    var updatedAt: String? = "",
                    @Json(name = "name")
                    var name: String? = "",
                    @Json(name = "description")
                    var description: String? = ""
            )

            companion object CREATOR : Parcelable.Creator<Menus> {
                override fun createFromParcel(parcel: Parcel): Menus {
                    return Menus(parcel)
                }

                override fun newArray(size: Int): Array<Menus?> {
                    return arrayOfNulls(size)
                }
            }
        }

        @JsonClass(generateAdapter = true)
        data class Items(
                @Json(name = "current_page")
                var currentPage: Int?,
                @Json(name = "data")
                var data: List<DataItems>,
                @Json(name = "first_page_url")
                var firstPageUrl: String?,
                @Json(name = "from")
                var from: Int?,
                @Json(name = "last_page")
                var lastPage: Int?,
                @Json(name = "last_page_url")
                var lastPageUrl: String?,
                @Json(name = "next_page_url")
                var nextPageUrl: String?,
                @Json(name = "path")
                var path: String?,
                @Json(name = "per_page")
                var perPage: Int?,
                @Json(name = "prev_page_url")
                var prevPageUrl: String?,
                @Json(name = "to")
                var to: Int?,
                @Json(name = "total")
                var total: Int?
        ) {
            @JsonClass(generateAdapter = true)
            data class DataItems(
                    @Json(name = "id")
                    var id: Int?,
                    @Json(name = "price")
                    var price: Float?,
                    @Json(name = "image")
                    var image: List<String>,
                    @Json(name = "views_number")
                    var viewsNumber: Int?,
                    @Json(name = "purchased_number")
                    var purchasedNumber: Int?,
                    @Json(name = "brand_id")
                    var brandId: Int?,
                    @Json(name = "category_id")
                    var categoryId: Int?,
                    @Json(name = "menu_id")
                    var menuId: Int?,
                    @Json(name = "promotion_id")
                    var promotionId: Int?,
                    @Json(name = "reviews_rating")
                    var reviewsRating: Double?,
                    @Json(name = "reviews_count")
                    var reviewsCount: Int?,
                    @Json(name = "created_at")
                    var createdAt: String?,
                    @Json(name = "updated_at")
                    var updatedAt: String?,
                    @Json(name = "is_favorite")
                    var isFavorite: Boolean,
                    @Json(name = "name")
                    var name: String?,
                    @Json(name = "description")
                    var description: String?
            )
        }
    }
}