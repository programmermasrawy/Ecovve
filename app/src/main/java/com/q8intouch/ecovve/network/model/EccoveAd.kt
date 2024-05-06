package com.q8intouch.ecovve.network.model
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class AdResponse(
    @Json(name = "data")
    var ad: Ad
)

@JsonClass(generateAdapter = true)
data class Ad(
    @Json(name = "id")
    var id: Int,
    @Json(name = "title")
    var title: String,
    @Json(name = "content")
    var content: String,
    @Json(name = "video_url")
    var videoUrl: String,
    @Json(name = "image")
    var image: String,
    @Json(name = "status")
    var status: String,
    @Json(name = "position")
    var position: String,
    @Json(name = "created_at")
    var createdAt: String,
    @Json(name = "updated_at")
    var updatedAt: String,
    @Json(name = "title_translate")
    var titleTranslate: Any?,
    @Json(name = "content_translate")
    var contentTranslate: Any?,
    @Json(name = "popup")
    var popup: List<Popup>,
    @Json(name = "translations")
    var translations: List<Any>
)

@JsonClass(generateAdapter = true)
data class Popup(
    @Json(name = "id")
    var id: Int,
    @Json(name = "title")
    var title: String,
    @Json(name = "content")
    var content: String,
    @Json(name = "status")
    var status: String,
    @Json(name = "ad_id")
    var adId: Int,
    @Json(name = "created_at")
    var createdAt: String,
    @Json(name = "updated_at")
    var updatedAt: String
)