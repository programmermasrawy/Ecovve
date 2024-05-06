package com.q8intouch.ecovve.network.model

data class EcovveCancelOrder(
        var `data`: Data?
) {
    data class Data(
            var address_id: Int?, // 11
            var assigned_user_id: String?, // null
            var cancellation_time: String?, // 2019-04-11 00:00:00
            var coupon_id: String?, // null
            var created_at: String?, // 2019-04-10 22:19:24
            var deleted_at: String?, // null
            var deliver_time: String?, // null
            var delivery: String?, // not_delivered
            var delivery_type: String?, // delivery
            var final_price: String?, // 47.00
            var gift_card_id: String?, // null
            var id: Int?, // 38
            var initial_price: String?, // 47.00
            var notes: String?, // hello man
            var order_review: List<OrderReview?>?,
            var outlet_id: Int?, // 1
            var paid: String?, // no
            var payment_option: String?, // cash
            var payment_url: String?, // null
            var pickup_time: String?, // null
            var quality: Int?, // 2
            var status: String?, // cancelled
            var status_list: List<Status?>?,
            var track_id: String?, // 1-5cae4fcc3dca8
            var updated_at: String?, // 2019-04-11 00:27:56
            var user_free_credit: String?, // 0
            var user_id: Int? // 1
    ) {
        data class Status(
                var id: Int?, // 1
                var state: Int?, // 0
                var status_name: String?, // Dr. Conner Lakin
                var status_order: Int? // 5
        )

        data class OrderReview(
                var created_at: String?, // 2019-04-06 12:07:26
                var delivery_man: Int?, // 1
                var delivery_time: Int?, // 4
                var id: Int?, // 11
                var order_id: Int?, // 38
                var price_to_value: Int?, // 1
                var quality: Int?, // 2
                var review: String?, // Aspernatur laborum voluptates in inventore et nihil voluptas adipisci sequi consequatur.
                var seal: Int?, // 2
                var updated_at: String? // 2019-04-06 12:07:26
        )
    }
}