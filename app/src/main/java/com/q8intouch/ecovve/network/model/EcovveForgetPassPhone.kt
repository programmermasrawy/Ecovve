package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveForgetPassPhone(
        @Json(name = "result")
        var result: Result = Result()
) {
    @JsonClass(generateAdapter = true)
    data class Result(
            @Json(name = "Message")
            var message: String = "",
            @Json(name = "Result")
            var result: String = "",
            @Json(name = "NetPoints")
            var netPoints: String = "",
            @Json(name = "messageId")
            var messageId: String = "",
            @Json(name = "RejectedNumbers")
            var rejectedNumbers: List<Any> = listOf()
    )
}