package com.q8intouch.ecovve.data.model

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity
data class Data(
    @Json(name = "id")
    var id: Int,
    @Json(name = "title")
    var title: String,
    @Json(name = "body")
    var body: String,
    @Json(name = "star")
    var star: String,
    @Json(name = "user_id")
    var userId: Int,
    @Json(name = "outlet_id")
    var outletId: Int,
    @Json(name = "created_at")
    var createdAt: String,
    @Json(name = "updated_at")
    var updatedAt: String,
    @Json(name = "user")
    var user: User,
    @Json(name = "outlet")
    var outlet: Outlet
)