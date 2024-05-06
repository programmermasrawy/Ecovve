package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json

data class FAQListResponse(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "links")
    val links: Links,
    @Json(name = "meta")
    val meta: Meta
) {
    data class Links(
        @Json(name = "first")
        val first: String,
        @Json(name = "last")
        val last: String,
        @Json(name = "next")
        val next: String,
        @Json(name = "prev")
        val prev: Any
    )

    data class Meta(
        @Json(name = "current_page")
        val currentPage: Int,
        @Json(name = "from")
        val from: Int,
        @Json(name = "last_page")
        val lastPage: Int,
        @Json(name = "path")
        val path: String,
        @Json(name = "per_page")
        val perPage: Int,
        @Json(name = "to")
        val to: Int,
        @Json(name = "total")
        val total: Int
    )

    data class Data(
        @Json(name = "answer")
        val answer: String,
        @Json(name = "created_at")
        val createdAt: String,
        @Json(name = "id")
        val id: Int,
        @Json(name = "priority")
        val priority: String,
        @Json(name = "question")
        val question: String,
        @Json(name = "updated_at")
        val updatedAt: String
    )
}