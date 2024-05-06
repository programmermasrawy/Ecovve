package com.q8intouch.ecovve.network.model

data class EcovveShowExtra(
        var `data`: Data?
) {
    data class Data(
            var choices: List<Choice?>?,
            var created_at: String?, // 2019-04-21 18:34:37
            var description: String?, // Dolore et alias eos a quam eius.
            var id: Int?, // 1
            var is_required: Int?, // 1
            var item_id: Int?, // 50
            var items: Items?,
            var name: String?, // eum
            var updated_at: String? // 2019-04-21 18:34:37
    ) {
        data class Choice(
                var created_at: String?, // 2019-04-21 18:34:37
                var extra_id: Int?, // 1
                var id: Int?, // 40
                var name: String?, // Anika Veum
                var price: String?, // 167.00
                var updated_at: String? // 2019-04-21 18:34:37
        )

        data class Items(
                var brand_id: Int?, // 1
                var created_at: String?, // 2019-04-21 18:34:37
                var description: String?, // Aut magni ipsa quis.
                var id: Int?, // 50
                var image: List<String?>?,
                var ingredient: List<Ingredient?>?,
                var is_favorite: Boolean?, // false
                var menu_id: Int?, // 51
                var name: String?, // tempora
                var price: String?, // 81.00
                var price_after_promotion: String?, // 71
                var promotion_id: Int?, // 47
                var purchased_number: Int?, // 0
                var reviews_count: Int?, // 44
                var reviews_rating: String?, // 3.00
                var updated_at: String?, // 2019-04-21 18:34:37
                var video: String?, // https://youtu.be/Bey4XXJAqS8
                var views_number: Int? // 0
        ) {
            data class Ingredient(
                    var value: String? // minima
            )
        }
    }
}