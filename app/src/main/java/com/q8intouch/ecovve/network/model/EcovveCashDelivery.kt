package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveCashDelivery(
        @Json(name = "data")
        var `data`: Data = Data()
) {
    @JsonClass(generateAdapter = true)
    data class Data(
            @Json(name = "user_id")
            var userId: String = "",
            @Json(name = "outlet_id")
            var outletId: String = "",
            @Json(name = "delivery_type")
            var deliveryType: String = "",
            @Json(name = "notes")
            var notes: String = "",
            @Json(name = "address_id")
            var addressId: String = "",
            @Json(name = "status_list")
            var statusList: List<Status> = listOf(),
            @Json(name = "status")
            var status: String = "",
            @Json(name = "delivery")
            var delivery: String = "",
            @Json(name = "track_id")
            var trackId: String = "",
            @Json(name = "initial_price")
            var initialPrice: Int = 0,
            @Json(name = "final_price")
            var finalPrice: Int = 0,
            @Json(name = "updated_at")
            var updatedAt: String = "",
            @Json(name = "created_at")
            var createdAt: String = "",
            @Json(name = "id")
            var id: Int = 0,
            @Json(name = "quality")
            var quality: Int = 0,
            @Json(name = "order_review")
            var orderReview: List<OrderReview> = listOf()
    ) {
        @JsonClass(generateAdapter = true)
        data class Status(
                @Json(name = "id")
                var id: Int = 0,
                @Json(name = "status_name")
                var statusName: String = "",
                @Json(name = "state")
                var state: Int = 0,
                @Json(name = "status_order")
                var statusOrder: Int = 0
        )

        @JsonClass(generateAdapter = true)
        data class OrderReview(
                @Json(name = "id")
                var id: Int = 0,
                @Json(name = "seal")
                var seal: Int = 0,
                @Json(name = "delivery_time")
                var deliveryTime: Int = 0,
                @Json(name = "price_to_value")
                var priceToValue: Int = 0,
                @Json(name = "quality")
                var quality: Int = 0,
                @Json(name = "delivery_man")
                var deliveryMan: Int = 0,
                @Json(name = "order_id")
                var orderId: Int = 0,
                @Json(name = "review")
                var review: String = "",
                @Json(name = "created_at")
                var createdAt: String = "",
                @Json(name = "updated_at")
                var updatedAt: String = ""
        )
    }
}