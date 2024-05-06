package com.q8intouch.ecovve.network.model.friends

import com.q8intouch.ecovve.network.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveSentRequests(
        @Json(name = "data")
        var `data`: List<Data>
) {
    @JsonClass(generateAdapter = true)
    data class Data(
            @Json(name = "id")
            var id: Int,
            @Json(name = "status")
            var status: String,
            @Json(name = "sender_id")
            var senderId: Int,
            @Json(name = "receiver_id")
            var receiverId: Int,
            @Json(name = "created_at")
            var createdAt: String,
            @Json(name = "updated_at")
            var updatedAt: String,
            @Json(name = "sender")
            var sender: User,
            @Json(name = "receiver")
            var `receiver`: User
    )
}