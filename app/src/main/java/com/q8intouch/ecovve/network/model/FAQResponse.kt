package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json

data class FAQResponse(
    @Json(name = "data")
    val `data`: Data
) {
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