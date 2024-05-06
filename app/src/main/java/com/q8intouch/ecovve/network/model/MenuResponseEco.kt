package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MenuResponseEco(
    @Json(name = "data")
    var `data`: List<Data>,
    @Json(name = "links")
    var links: Links,
    @Json(name = "meta")
    var meta: Meta
) {
    @JsonClass(generateAdapter = true)
    data class Meta(
        @Json(name = "current_page")
        var currentPage: Int,
        @Json(name = "from")
        var from: Int,
        @Json(name = "last_page")
        var lastPage: Int,
        @Json(name = "path")
        var path: String,
        @Json(name = "per_page")
        var perPage: Int,
        @Json(name = "to")
        var to: Int,
        @Json(name = "total")
        var total: Int
    )

    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        var id: Int,
        @Json(name = "name")
        var name: String,
        @Json(name = "description")
        var description: String,
        @Json(name = "image")
        var image: List<Any>,
        @Json(name = "brand_id")
        var brandId: Int,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "updated_at")
        var updatedAt: String
    )

    @JsonClass(generateAdapter = true)
    data class Links(
        @Json(name = "first")
        var first: String,
        @Json(name = "last")
        var last: String,
        @Json(name = "prev")
        var prev: Any?,
        @Json(name = "next")
        var next: String
    )
}