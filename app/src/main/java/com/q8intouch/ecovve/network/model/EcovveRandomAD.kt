package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveRandomAD(
        @Json(name = "data")
        var `data`: Data = Data()
) {
    @JsonClass(generateAdapter = true)
    data class Data(
            @Json(name = "id")
            var id: Int = 0,
            @Json(name = "video_url")
            var videoUrl: String = "",
            @Json(name = "image")
            var image: String = "",
            @Json(name = "status")
            var status: String = "",
            @Json(name = "outlet_id")
            var outletId: Int = 0,
            @Json(name = "area_id")
            var areaId: Int = 0,
            @Json(name = "duration")
            var duration: Int = 0,
            @Json(name = "created_at")
            var createdAt: String = "",
            @Json(name = "updated_at")
            var updatedAt: String = "",
            @Json(name = "deleted_at")
            var deletedAt: Any? = Any(),
            @Json(name = "title")
            var title: String = "",
            @Json(name = "content")
            var content: String = ""
    )
}