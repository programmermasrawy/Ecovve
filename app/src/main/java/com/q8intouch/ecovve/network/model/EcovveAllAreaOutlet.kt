package com.q8intouch.ecovve.network.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@SuppressLint("ParcelCreator")
@JsonClass(generateAdapter = true)
data class EcovveAllAreaOutlet(
    @Json(name = "data")
    var `data`: List<Data>,
    @Json(name = "links")
    var links: Links,
    @Json(name = "meta")
    var meta: Meta
) : Parcelable {


    override fun writeToParcel(p0: Parcel?, p1: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    @JsonClass(generateAdapter = true)
    data class Meta(
        @Json(name = "current_page")
        var currentPage: Int?,
        @Json(name = "from")
        var from: Int?,
        @Json(name = "last_page")
        var lastPage: Int?,
        @Json(name = "path")
        var path: String?,
        @Json(name = "per_page")
        var perPage: Int?,
        @Json(name = "to")
        var to: Int?,
        @Json(name = "total")
        var total: Int
    )

    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        var id: Int?,
        @Json(name = "outlet_id")
        var outletId: Int?,
        @Json(name = "area_id")
        var areaId: Int?,
        @Json(name = "delivery_fee")
        var deliveryFee: String?,
        @Json(name = "delivery_time")
        var deliveryTime: Int?,
        @Json(name = "delivery_availability")
        var deliveryAvailability: String?,
        @Json(name = "created_at")
        var createdAt: String?,
        @Json(name = "updated_at")
        var updatedAt: String?,
//        @Json(name = "area")
//        var area: Area,
        @Json(name = "outlet")
        var outlet: Outlet
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
//            TODO("area"),
            TODO("outlet")
        ) {
        }

        @JsonClass(generateAdapter = true)
        data class Outlet(
            @Json(name = "id")
            var id: Int?,
            @Json(name = "name")
            var name: String?,
            @Json(name = "address")
            var address: String?,
            @Json(name = "longitude")
            var longitude: Double,
            @Json(name = "latitude")
            var latitude: Double,
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
            @Json(name = "opening")
            var opening: String?,
            @Json(name = "closing")
            var closing: String?,
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
            var reviewsCount: Int?,
            @Json(name = "created_at")
            var createdAt: String?,
            @Json(name = "updated_at")
            var updatedAt: String
        )

        @JsonClass(generateAdapter = true)
        data class Area(
            @Json(name = "id")
            var id: Int?,
            @Json(name = "name_ar")
            var nameAr: String?,
            @Json(name = "name_en")
            var nameEn: String?,
            @Json(name = "name_fr")
            var nameFr: String?,
            @Json(name = "city_id")
            var cityId: Int?,
            @Json(name = "created_at")
            var createdAt: String?,
            @Json(name = "updated_at")
            var updatedAt: String?
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id!!)
            parcel.writeInt(outletId!!)
            parcel.writeInt(areaId!!)
            parcel.writeString(deliveryFee)
            parcel.writeInt(deliveryTime!!)
            parcel.writeString(deliveryAvailability)
            parcel.writeString(createdAt)
            parcel.writeString(updatedAt)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Data> {
            override fun createFromParcel(parcel: Parcel): Data {
                return Data(parcel)
            }

            override fun newArray(size: Int): Array<Data?> {
                return arrayOfNulls(size)
            }
        }
    }

    @JsonClass(generateAdapter = true)
    data class Links(
        @Json(name = "first")
        var first: String?,
        @Json(name = "last")
        var last: String?,
        @Json(name = "prev")
        var prev: Any?,
        @Json(name = "next")
        var next: String
    )

}