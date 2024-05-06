package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveSignup(
    @Json(name = "status")
    var status: String,
    @Json(name = "message")
    var message: String
) {
//    @JsonClass(generateAdapter = true)
//    data class Sms(
////        @Json(name = "headers")
////        var headers: Headers,
//        @Json(name = "original")
//        var original: Original,
//        @Json(name = "exception")
//        var exception: Any?
//    ) {
//
//        @JsonClass(generateAdapter = true)
//        data class Original(
//            @Json(name = "Message")
//            var message: String,
//            @Json(name = "Result")
//            var result: String,
//            @Json(name = "NetPoints")
//            var netPoints: String,
//            @Json(name = "messageId")
//            var messageId: String,
//            @Json(name = "RejectedNumbers")
//            var rejectedNumbers: List<Any>
//        )
//    }
}