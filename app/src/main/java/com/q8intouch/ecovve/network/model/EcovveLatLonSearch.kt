package com.q8intouch.ecovve.network.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveLatLonSearch(
        @Json(name = "data")
        var `data`: Data
) : Parcelable {
    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @JsonClass(generateAdapter = true)
    data class Data(
            @Json(name = "outlets")
            var outlets: Outlets,
            @Json(name = "categories")
            var categories: Categories
    ) {
        @JsonClass(generateAdapter = true)
        data class Outlets(
                @Json(name = "current_page")
                var currentPage: Int?,
                @Json(name = "data")
                var data: List<DataOutlet>,
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
            data class DataOutlet(
                    @Json(name = "id")
                    var id: Int?,
                    @Json(name = "name")
                    var name: String?,
                    @Json(name = "address")
                    var address: String?,
                    @Json(name = "longitude")
                    var longitude: Double?,
                    @Json(name = "latitude")
                    var latitude: Double?,
                    @Json(name = "phone")
                    var phone: String?,
                    @Json(name = "status")
                    var status: String?,
                    @Json(name = "has_delivery")
                    var hasDelivery: String?,
                    @Json(name = "tax")
                    var tax: String?,
                    @Json(name = "capacity")
                    var capacity: Int?,
                    @Json(name = "is_open")
                    var is_open: Boolean?,
                    @Json(name = "location")
                    var location: String?,
                    @Json(name = "city_id")
                    var cityId: Int?,
                    @Json(name = "brand_id")
                    var brandId: Int?,
                    @Json(name = "current_orders_number")
                    var currentOrdersNumber: Int?,
                    @Json(name = "area_id")
                    var areaId: Int?,
                    @Json(name = "avg_delivery_time")
                    var avgDeliveryTime: Int?,
                    @Json(name = "avg_delivery_fee")
                    var avgDeliveryFee: String?,
                    @Json(name = "reviews_rating")
                    var reviewsRating: Double?,
                    @Json(name = "reviews_count")
                    var reviewsCount: Double?,
                    @Json(name = "created_at")
                    var createdAt: String?,
                    @Json(name = "updated_at")
                    var updatedAt: String?,
                    @Json(name = "minimum_charge")
                    var minimumCharge: String?,
                    @Json(name = "supports_cc")
                    var supportsCc: Int?,
                    @Json(name = "supports_knet")
                    var supportsKnet: Int?,
                    @Json(name = "brand")
                    var brand: Brand
            ) {
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
                        @Json(name = "cover")
                        var cover: String?,
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
                        @Json(name = "supports_knet")
                        var supportsKnet: Int?,
                        @Json(name = "supports_cc")
                        var supportsCc: Int?,
                        @Json(name = "created_at")
                        var createdAt: String?,
                        @Json(name = "updated_at")
                        var updatedAt: String?
                )
            }
        }

        @SuppressLint("ParcelCreator")
        @JsonClass(generateAdapter = true)
        data class Categories(
                @Json(name = "current_page")
                var currentPage: Int?,
                @Json(name = "data")
                var data: List<DataCategory>,
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
        ) : Parcelable {
            constructor(parcel: Parcel) : this(
                    parcel.readValue(Int::class.java.classLoader) as? Int,
                    parcel.createTypedArrayList(DataCategory.CREATOR),
                    parcel.readString(),
                    parcel.readValue(Int::class.java.classLoader) as? Int,
                    parcel.readValue(Int::class.java.classLoader) as? Int,
                    parcel.readString(),
                    parcel.readString(),
                    parcel.readString(),
                    parcel.readValue(Int::class.java.classLoader) as? Int,
                    parcel.readString(),
                    parcel.readValue(Int::class.java.classLoader) as? Int,
                    parcel.readValue(Int::class.java.classLoader) as? Int) {
            }

            override fun writeToParcel(p0: Parcel?, p1: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun describeContents(): Int {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            @JsonClass(generateAdapter = true)
            data class DataCategory(
                    @Json(name = "id")
                    var id: Int?,
                    @Json(name = "name")
                    var name: String?
            ) : Parcelable {
                constructor(parcel: Parcel) : this(
                        parcel.readValue(Int::class.java.classLoader) as? Int,
                        parcel.readString()) {
                }

                override fun writeToParcel(p0: Parcel?, p1: Int) {
                    p0!!.writeValue(id)
                    p0!!.writeString(name)
                }

                override fun describeContents(): Int {
                    return 0                }

                companion object CREATOR : Parcelable.Creator<DataCategory> {
                    override fun createFromParcel(parcel: Parcel): DataCategory {
                        return DataCategory(parcel)
                    }

                    override fun newArray(size: Int): Array<DataCategory?> {
                        return arrayOfNulls(size)
                    }
                }
            }

            companion object CREATOR : Parcelable.Creator<Categories> {
                override fun createFromParcel(parcel: Parcel): Categories {
                    return Categories(parcel)
                }

                override fun newArray(size: Int): Array<Categories?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}