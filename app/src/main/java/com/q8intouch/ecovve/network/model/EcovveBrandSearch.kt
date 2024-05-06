package com.q8intouch.ecovve.network.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveBrandSearch(
    @Json(name = "result")
    var result: List<Result>
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "id")
        var id: Int,
        @Json(name = "name")
        var name: String,
        @Json(name = "description")
        var description: String,
        @Json(name = "business_type")
        var businessType: String,
        @Json(name = "commission_annual")
        var commissionAnnual: String,
        @Json(name = "commission_order_fixed")
        var commissionOrderFixed: String,
        @Json(name = "commission_order_percentage")
        var commissionOrderPercentage: String,
        @Json(name = "logo")
        var logo: String,
        @Json(name = "phone")
        var phone: String,
        @Json(name = "address")
        var address: String,
        @Json(name = "has_delivery")
        var hasDelivery: String,
        @Json(name = "appears_in_search")
        var appearsInSearch: String,
        @Json(name = "minimum_charge")
        var minimumCharge: String,
        @Json(name = "website")
        var website: String,
        @Json(name = "extra_information")
        var extraInformation: String,
        @Json(name = "status")
        var status: String,
        @Json(name = "city_id")
        var cityId: Int,
        @Json(name = "brand_owner_id")
        var brandOwnerId: Int,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "updated_at")
        var updatedAt: String,
        @Json(name = "outlets")
        var outlets: List<Outlet>
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            TODO("outlets")
        ) {
        }

        override fun writeToParcel(p0: Parcel?, p1: Int) {

        }

        override fun describeContents(): Int {
return 0
        }

        @JsonClass(generateAdapter = true)
        data class Outlet(
            @Json(name = "id")
            var id: Int,
            @Json(name = "name")
            var name: String,
            @Json(name = "address")
            var address: String,
            @Json(name = "longitude")
            var longitude: Double,
            @Json(name = "latitude")
            var latitude: Double,
            @Json(name = "phone")
            var phone: String,
            @Json(name = "status")
            var status: String,
            @Json(name = "has_delivery")
            var hasDelivery: String,
            @Json(name = "tax")
            var tax: String,
            @Json(name = "capacity")
            var capacity: Int,
            @Json(name = "opening")
            var opening: String,
            @Json(name = "closing")
            var closing: String,
            @Json(name = "location")
            var location: String,
            @Json(name = "city_id")
            var cityId: Int,
            @Json(name = "brand_id")
            var brandId: Int,
            @Json(name = "current_orders_number")
            var currentOrdersNumber: Int,
            @Json(name = "area_id")
            var areaId: Int,
            @Json(name = "created_at")
            var createdAt: String,
            @Json(name = "updated_at")
            var updatedAt: String
        )

        companion object CREATOR : Parcelable.Creator<Result> {
            override fun createFromParcel(parcel: Parcel): Result {
                return Result(parcel)
            }

            override fun newArray(size: Int): Array<Result?> {
                return arrayOfNulls(size)
            }
        }
    }
}