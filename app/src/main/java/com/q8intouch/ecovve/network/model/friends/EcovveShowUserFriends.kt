package com.q8intouch.ecovve.network.model.friends

import com.q8intouch.ecovve.network.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveShowUserFriends(
        @Json(name = "data")
        var `data`: List<User>
)