package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveNotification(
        @Json(name = "current_page")
        var currentPage: Int? = 0,
        @Json(name = "data")
        var `data`: List<Data>,
        @Json(name = "first_page_url")
        var firstPageUrl: String? = "",
        @Json(name = "from")
        var from: Int? = 0,
        @Json(name = "last_page")
        var lastPage: Int? = 0,
        @Json(name = "last_page_url")
        var lastPageUrl: String? = "",
        @Json(name = "next_page_url")
        var nextPageUrl: String? = "",
        @Json(name = "path")
        var path: String? = "",
        @Json(name = "per_page")
        var perPage: Int? = 0,
        @Json(name = "prev_page_url")
        var prevPageUrl: String? = String(),
        @Json(name = "to")
        var to: Int? = 0,
        @Json(name = "total")
        var total: Int? = 0
) {
    @JsonClass(generateAdapter = true)
    data class Data(
            @Json(name = "id")
            var id: Int? = 0,
            @Json(name = "name")
            var name: String? = "",
            @Json(name = "type")
            var type: Int? = 0,
            @Json(name = "content")
            var content: String? = "",
            @Json(name = "seen")
            var seen: String? = "",
            @Json(name = "send_to")
            var sendTo: List<String?>? = listOf(),
            @Json(name = "image")
            var image: String? = "",
            @Json(name = "created_at")
            var createdAt: String? = "",
            @Json(name = "updated_at")
            var updatedAt: String? = ""
    ) {
        @JsonClass(generateAdapter = true)
        data class NotificationType(
                @Json(name = "id")
                var id: Int? = 0,
                @Json(name = "title")
                var title: String? = "",
                @Json(name = "image")
                var image: String? = String(),
                @Json(name = "created_at")
                var createdAt: String? = "",
                @Json(name = "updated_at")
                var updatedAt: String? = ""
        )
    }
}