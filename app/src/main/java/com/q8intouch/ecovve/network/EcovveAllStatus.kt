package com.q8intouch.ecovve.network

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@SuppressLint("ParcelCreator")
@JsonClass(generateAdapter = true)
data class EcovveAllStatus(
    @Json(name = "data")
    var `data`: List<Data>
) :Parcelable {
    override fun writeToParcel(p0: Parcel?, p1: Int) {

    }

    override fun describeContents(): Int {
return 0   }

    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        var id: Int,
        @Json(name = "status_name")
        var statusName: String,
        @Json(name = "state")
        var state: Int,
        @Json(name = "status_order")
        var statusOrder: Int
    ) :Parcelable {
        override fun writeToParcel(p0: Parcel?, p1: Int) {

        }

        override fun describeContents(): Int {
            return  0
        }
    }
}