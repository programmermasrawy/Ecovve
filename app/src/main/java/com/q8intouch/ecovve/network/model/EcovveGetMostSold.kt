package com.q8intouch.ecovve.network.model

data class EcovveGetMostSold(
        var `data`: List<Data?>?
) {
    data class Data(
            var brand_id: Int?, // 1
            var created_at: String?, // 2019-05-16 02:38:32
            var description: String?, // Aut commodi vero dolores eaque vitae.
            var id: Int?, // 40
            var image: List<String?>?,
            var ingredient: List<Ingredient?>?,
            var is_favorite: Boolean?, // false
            var menu_id: Int?, // 1
            var name: String?, // repudiandae
            var price: Float?, // 61.00
            var price_after_promotion: Double?, // 57.340000000000003410605131648480892181396484375
            var promotion_id: Int?, // 6
            var purchased_number: Int?, // 3
            var reviews_count: Int?, // 15
            var reviews_rating: String?, // 4.00
            var updated_at: String?, // 2019-05-16 02:38:32
            var video: String?, // https://youtu.be/Bey4XXJAqS8
            var views_number: Int? // 7
    ) {
        data class Ingredient(
                var value: String? // et
        )
    }
}