package com.q8intouch.ecovve.network.model

data class EcovveALLOUTLETS(
        var `data`: List<Data?>?,
        var links: Links?,
        var meta: Meta?
) {
    data class Data(
            var address: String?, // 46510 Etha SpringsNorth Libbiefort, NV 54925-4618
            var area_id: Int?, // 22
            var avg_delivery_fee: String?, // 36.00
            var avg_delivery_time: Int?, // 2
            var brand_id: Int?, // 26
            var capacity: Int?, // 9764
            var city_id: Int?, // 6
            var closing: String?, // 05:00:00
            var created_at: String?, // 2019-05-01 19:33:21
            var current_orders_number: Int?, // 0
            var deleted_at: Any?, // null
            var has_delivery: String?, // no
            var id: Int?, // 10
            var is_open: Boolean?, // false
            var latitude: Double?, // -59.19108899999999806595951667986810207366943359375
            var location: String?, // West Garfieldport
            var longitude: Double?, // 130.01713000000000874933903105556964874267578125
            var name: String?, // quas
            var opening: String?, // 13:00:00
            var outlet_owner_id: Int?, // 5
            var phone: String?, // (676) 750-9093 x1252
            var reviews_count: Int?, // 54
            var reviews_rating: String?, // 2.06
            var status: String?, // banned
            var tax: String?, // 13.00
            var updated_at: String? // 2019-05-01 19:33:21
    )

    data class Links(
            var first: String?, // http://ecovve.com/ar/api/public/outlet/all?page=1
            var last: String?, // http://ecovve.com/ar/api/public/outlet/all?page=5
            var next: String?, // http://ecovve.com/ar/api/public/outlet/all?page=2
            var prev: Any? // null
    )

    data class Meta(
            var current_page: Int?, // 1
            var from: Int?, // 1
            var last_page: Int?, // 5
            var path: String?, // http://ecovve.com/ar/api/public/outlet/all
            var per_page: Int?, // 10
            var to: Int?, // 10
            var total: Int? // 50
    )
}