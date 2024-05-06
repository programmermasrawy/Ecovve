package com.q8intouch.ecovve.network.model

data class EcovveAllBanners(
        var `data`: List<Data?>?,
        var links: Links?,
        var meta: Meta?
) {
    data class Links(
            var first: String?, // https://ecovve.com/en/api/public/banners/all?page=1
            var last: String?, // https://ecovve.com/en/api/public/banners/all?page=1
            var next: Any?, // null
            var prev: Any? // null
    )

    data class Data(
            var created_at: String?, // 2019-07-01 13:43:46
            var description: String?, // Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown
            var due_date: String?, // 15-7-2019
            var id: Int?, // 10
            var image: String?, // images/placeholder.png
            var priority: Int?, // 1
            var title: String?, // error
            var updated_at: String? // 2019-07-01 13:43:46
    )

    data class Meta(
            var current_page: Int?, // 1
            var from: Int?, // 1
            var last_page: Int?, // 1
            var path: String?, // https://ecovve.com/en/api/public/banners/all
            var per_page: Int?, // 10
            var to: Int?, // 10
            var total: Int? // 10
    )
}