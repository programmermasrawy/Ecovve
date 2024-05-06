package com.q8intouch.ecovve.network.model

data class EcovveCitiesWithAreas(
        var `data`: List<Data?>?,
        var links: Links?,
        var meta: Meta?
) {
    data class Links(
            var first: String?, // http://ecovve.com/ar/api/public/city/cities_with_areas?page=1
            var last: String?, // http://ecovve.com/ar/api/public/city/cities_with_areas?page=34
            var next: String?, // http://ecovve.com/ar/api/public/city/cities_with_areas?page=2
            var prev: String? // null
    )

    data class Data(
            var area: List<Area?>?,
            var country_id: Int?, // 10
            var created_at: String?, // null
            var id: Int?, // 108
            var name_ar: String?, // لا بامبا
            var name_en: String?, // La Pampa
            var name_fr: String?, // La Pampa
            var updated_at: String? // null
    ){
        data class Area(
                var id: Int?, // 10
                var name_ar: String?, // لا بامبا
                var name_en: String?, // La Pampa
                var city_id: String?, // La Pampa
                var updated_at: String?, // null
                var created_at: String? // null
        )
    }

    data class Meta(
            var current_page: Int?, // 1
            var from: Int?, // 1
            var last_page: Int?, // 34
            var path: String?, // http://ecovve.com/ar/api/public/city/cities_with_areas
            var per_page: String?, // 100
            var to: Int?, // 100
            var total: Int? // 3382
    )
}