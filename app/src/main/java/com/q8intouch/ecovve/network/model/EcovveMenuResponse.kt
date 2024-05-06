package com.q8intouch.ecovve.network.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveMenuResponse(
    @Json(name = "data")
    var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        var id: Int?,
        @Json(name = "name")
        var name: String?,
        @Json(name = "description")
        var description: String?,
        @Json(name = "image")
        var image: List<String>,
        @Json(name = "brand_id")
        var brandId: Int?,
        @Json(name = "created_at")
        var createdAt: String?,
        @Json(name = "updated_at")
        var updatedAt: String?,
        @Json(name = "items")
        var items: Items,
        @Json(name = "category")
        var category: List<Category>,
        @Json(name = "brand")
        var brand: Brand
    ) {
        @JsonClass(generateAdapter = true)
        data class Category(
            @Json(name = "id")
            var id: Int?,
            @Json(name = "name")
            var name: String?,
            @Json(name = "description")
            var description: String?,
            @Json(name = "image")
            var image: String?,
            @Json(name = "category_morph_type")
            var categoryMorphType: String?,
            @Json(name = "category_morph_id")
            var categoryMorphId: Int?,
            @Json(name = "created_at")
            var createdAt: String?,
            @Json(name = "updated_at")
            var updatedAt: String
        )

        @JsonClass(generateAdapter = true)
        data class Items(
            @Json(name = "current_page")
            var currentPage: Int?,
            @Json(name = "data")
            var data: List<Dataum>,
            @Json(name = "first_page_url")
            var firstPageUrl: String?,
            @Json(name = "from")
            var from: Int?,
            @Json(name = "last_page")
            var lastPage: Int?,
            @Json(name = "last_page_url")
            var lastPageUrl: String?,
            @Json(name = "next_page_url")
            var nextPageUrl: Any?,
            @Json(name = "path")
            var path: String?,
            @Json(name = "per_page")
            var perPage: Int?,
            @Json(name = "prev_page_url")
            var prevPageUrl: Any?,
            @Json(name = "to")
            var to: Int?,
            @Json(name = "total")
            var total: Int
        ) : Parcelable {
            constructor(parcel: Parcel) : this(
                parcel.readInt(),
                TODO("data"),
                parcel.readString(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readString(),
                TODO("nextPageUrl"),
                parcel.readString(),
                parcel.readInt(),
                TODO("prevPageUrl"),
                parcel.readInt(),
                parcel.readInt()
            ) {
            }

            override fun writeToParcel(p0: Parcel?, p1: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun describeContents(): Int {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            @JsonClass(generateAdapter = true)
            data class Dataum(
                @Json(name = "id")
                var id: Int?,
                @Json(name = "name")
                var name: String?,
                @Json(name = "price")
                var price: String?,
                @Json(name = "description")
                var description: String?,
                @Json(name = "ingredient")
                var ingredient: List<String>,
                @Json(name = "image")
                var image: String?,
                @Json(name = "video")
                var video: String?,
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
                @Json(name = "created_at")
                var createdAt: String?,
                @Json(name = "updated_at")
                var updatedAt: String
            )

            companion object CREATOR : Parcelable.Creator<Items> {
                override fun createFromParcel(parcel: Parcel): Items {
                    return Items(parcel)
                }

                override fun newArray(size: Int): Array<Items?> {
                    return arrayOfNulls(size)
                }
            }
        }

        @JsonClass(generateAdapter = true)
        data class Brand(
            @Json(name = "id")
            var id: Int?,
            @Json(name = "name")
            var name: String?,
            @Json(name = "description")
            var description: String?,
            @Json(name = "business_type")
            var businessType: String?,
            @Json(name = "commission_annual")
            var commissionAnnual: String?,
            @Json(name = "commission_order_fixed")
            var commissionOrderFixed: String?,
            @Json(name = "commission_order_percentage")
            var commissionOrderPercentage: String?,
            @Json(name = "logo")
            var logo: String?,
            @Json(name = "phone")
            var phone: String?,
            @Json(name = "address")
            var address: String?,
            @Json(name = "has_delivery")
            var hasDelivery: String?,
            @Json(name = "appears_in_search")
            var appearsInSearch: String?,
            @Json(name = "minimum_charge")
            var minimumCharge: String?,
            @Json(name = "website")
            var website: String?,
            @Json(name = "extra_information")
            var extraInformation: String?,
            @Json(name = "status")
            var status: String?,
            @Json(name = "city_id")
            var cityId: Int?,
            @Json(name = "brand_owner_id")
            var brandOwnerId: Int?,
            @Json(name = "created_at")
            var createdAt: String?,
            @Json(name = "updated_at")
            var updatedAt: String
        )
    }
}