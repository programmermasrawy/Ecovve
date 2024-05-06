package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveOrderStatus(
    @Json(name = "data")
    var `data`: Data
) {
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
    )
}