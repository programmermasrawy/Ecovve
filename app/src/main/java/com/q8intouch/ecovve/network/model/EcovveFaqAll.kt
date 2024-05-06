package com.q8intouch.ecovve.network.model

data class EcovveFaqAll(
        var `data`: List<Data?>?,
        var links: Links?,
        var meta: Meta?
) {
    data class Links(
            var first: String?, // http://ecovve.com/ar/api/public/faq/all?page=1
            var last: String?, // http://ecovve.com/ar/api/public/faq/all?page=5
            var next: String?, // http://ecovve.com/ar/api/public/faq/all?page=2
            var prev: Any? // null
    )

    data class Meta(
            var current_page: Int?, // 1
            var from: Int?, // 1
            var last_page: Int?, // 5
            var path: String?, // http://ecovve.com/ar/api/public/faq/all
            var per_page: Int?, // 10
            var to: Int?, // 10
            var total: Int? // 50
    )

    data class Data(
            var answer: String?, // Ad doloribus dolorem ratione impedit ut aut ratione quia possimus ratione illum quo omnis aut incidunt distinctio libero sapiente ut provident molestiae saepe aspernatur numquam reiciendis quia sit hic ex explicabo dignissimos sed sint non ex est vero rem sapiente maiores qui nam nesciunt facere nostrum blanditiis.
            var created_at: String?, // 2019-04-06 12:05:53
            var id: Int?, // 10
            var priority: String?, // 4
            var question: String?, // In sit aut rerum ut sint natus optio.
            var updated_at: String? // 2019-04-06 12:05:53
    )
}