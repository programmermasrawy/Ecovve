package com.q8intouch.ecovve.network.model

data class EcovveShowItem(
        var `data`: Data?
) {
    data class Data(
            var brand: Brand?,
            var brand_id: Int?, // 1
            var created_at: String?, // 2019-04-21 18:34:37
            var description: String?, // Ut accusantium recusandae aut id ut.
            var extras: List<Extra?>?,
            var id: Int?, // 2
            var image: List<String?>?,
            var ingredient: List<Ingredient?>?,
            var is_favorite: Boolean?, // false
            var menu: Menu?,
            var menu_id: Int?, // 51
            var name: String?, 
            var price: String?, // 136.00
            var price_after_promotion: String?, // 126.4800000000000039790393202565610408782958984375
            var promotion: Promotion?,
            var promotion_id: Int?, // 4
            var purchased_number: Int?, // 0
            var reviews_count: Int?, // 63
            var reviews_rating: String?, // 1.00
            var updated_at: String?, // 2019-04-21 18:34:37
            var video: String?, // https://youtu.be/Bey4XXJAqS8
            var views_number: Int? // 0
    ) {
        data class Ingredient(
                var value: String? // et
        )

        data class Promotion(
                var buy_one_get_one_offer: String?, // off
                var created_at: String?, // 2019-04-21 18:34:38
                var description: String?, // Alias architecto aut quibusdam unde laboriosam impedit.
                var expire_date: String?, // 2019-11-04
                var id: Int?, // 4
                var image: String?, // images/placeholder.png
                var kind: String?, // featured
                var name: String?, // inventore
                var type: String?, // percentage
                var updated_at: String?, // 2019-04-21 18:34:38
                var value: String? // 7.00
        )

        data class Translation(
                var description: String?, // Ut accusantium recusandae aut id ut.
                var id: Int?, // 2
                var item_id: Int?, // 2
                var locale: String?, // en
                var name: String? // dolorem
        )

        data class Menu(
                var brand_id: Int?, // 39
                var created_at: String?, // 2019-04-21 18:34:36
                var description: String?, // Rene Anderson
                var id: Int?, // 51
                var image: List<String?>?,
                var name: String?, // Blake Baumbach
                var translations: List<Translation?>?,
                var updated_at: String? // 2019-04-21 18:34:36
        ) {
            data class Translation(
                    var description: String?, // Rene Anderson
                    var id: Int?, // 51
                    var locale: String?, // en
                    var menu_id: Int?, // 51
                    var name: String? // Blake Baumbach
            )
        }

        data class Brand(
                var address: String?, // 1107 Luettgen EstateNitzschetown, HI 37995-1267
                var appears_in_search: String?, // yes
                var brand_owner_id: Int?, // 36
                var business_type: String?, // minus
                var city_id: Int?, // 43
                var commission_annual: String?, // 20000
                var commission_order_fixed: String?, // 100
                var commission_order_percentage: String?, // 5
                var cover: String?, // images/placeholder.png
                var created_at: String?, // 2019-04-21 18:34:36
                var deleted_at: Any?, // null
                var description: String?, // Doloremque voluptatum beatae dolorem laboriosam adipisci et eum nam dolorum.
                var extra_information: String?, // Ut quibusdam dolores sit molestiae delectus.
                var has_delivery: String?, // yes
                var id: Int?, // 1
                var logo: String?, // images/placeholder.png
                var minimum_charge: String?, // 0.00
                var name: String?, // pariatur
                var phone: String?, // (241) 860-5130 x0601
                var reviews_count: Int?, // 57
                var reviews_rating: String?, // 2.00
                var status: String?, // banned
                var supports_cc: Int?, // 0
                var supports_knet: Int?, // 0
                var translations: List<Translation?>?,
                var updated_at: String?, // 2019-04-21 18:34:36
                var website: String? // www.pariatur.com
        ) {
            data class Translation(
                    var brand_id: Int?, // 1
                    var deleted_at: Any?, // null
                    var description: String?, // Doloremque voluptatum beatae dolorem laboriosam adipisci et eum nam dolorum.
                    var id: Int?, // 1
                    var locale: String?, // en
                    var name: String? // pariatur
            )
        }

        data class Extra(
                var created_at: String?, // 2019-04-21 18:34:37
                var description: String?, // Perferendis harum adipisci quia quis totam.
                var id: Int?, // 12
                var is_required: Int?, // 1
                var item_id: Int?, // 2
                var name: String?, // officia
                var updated_at: String? // 2019-04-21 18:34:37
        ) {
            data class Translation(
                    var description: String?, // Perferendis harum adipisci quia quis totam.
                    var extra_id: Int?, // 12
                    var id: Int?, // 12
                    var locale: String?, // en
                    var name: String? // officia
            )
        }
    }
}