package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class EcovveUserChatRooms(
        @Json(name = "data")
        var `data`: List<Data>
) {
    @JsonClass(generateAdapter = true)
    data class Data(
            @Json(name = "id")
            var id: Int,
            @Json(name = "name")
            var name: String?,
            @Json(name = "description")
            var description: String?,
            @Json(name = "image")
            var image: String?,
            @Json(name = "creator_id")
            var creatorId: Int?,
            @Json(name = "deleted_at")
            var deletedAt: String?,
            @Json(name = "created_at")
            var createdAt: String?,
            @Json(name = "updated_at")
            var updatedAt: String?,
            @Json(name = "last_message")
            var lastMessage: LastMessage?,
            @Json(name = "pivot")
            var pivot: Pivot
    ) {
        @JsonClass(generateAdapter = true)
        data class Pivot(
                @Json(name = "user_id")
                var userId: Int?,
                @Json(name = "chat_room_id")
                var chatRoomId: Int?,
                @Json(name = "created_at")
                var createdAt: String?,
                @Json(name = "updated_at")
                var updatedAt: String?
        )
        @JsonClass(generateAdapter = true)
        data class LastMessage(
                @Json(name = "message")
                var message: String?
        )
    }
}