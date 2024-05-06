package com.q8intouch.ecovve.network.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiftCardsListResponse(
    @Json(name = "data")
    var data: List<DataEntity>
) {
    @JsonClass(generateAdapter = true)
    data class DataEntity(
        @Json(name = "id")
        var id: String?,
        @Json(name = "name")
        var name: String?,
        @Json(name = "description")
        var description: String?,
        @Json(name = "amount")
        var amount: String?,
        @Json(name = "status")
        var status: String?,
        @Json(name = "expire_date")
        var expireDate: String?,
        @Json(name = "giver_id")
        var giverId: Int?,
        @Json(name = "taker_id")
        var takerId: Int?,
        @Json(name = "created_at")
        var createdAt: String?,
        @Json(name = "updated_at")
        var updatedAt: String?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(name)
            parcel.writeString(description)
            parcel.writeString(amount)
            parcel.writeString(status)
            parcel.writeString(expireDate)
            parcel.writeInt(giverId!!)
            parcel.writeInt(takerId!!)
            parcel.writeString(createdAt)
            parcel.writeString(updatedAt)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<DataEntity> {
            override fun createFromParcel(parcel: Parcel): DataEntity {
                return DataEntity(parcel)
            }

            override fun newArray(size: Int): Array<DataEntity?> {
                return arrayOfNulls(size)
            }
        }
    }
}
