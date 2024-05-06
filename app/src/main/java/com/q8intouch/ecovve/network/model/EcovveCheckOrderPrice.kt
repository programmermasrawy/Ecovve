package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveCheckOrderPrice(
        @Json(name = "data")
        var `data`: Data = Data()
) {
    @JsonClass(generateAdapter = true)
    data class Data(
            @Json(name = "costs")
            var costs: Costs = Costs(),
            @Json(name = "discounts")
            var discounts: Discounts = Discounts(),
            @Json(name = "total")
            var total: String? = ""
    ) {
        @JsonClass(generateAdapter = true)
        data class Costs(
                @Json(name = "item_costs")
                var itemCosts: Double? = 0.0,
                @Json(name = "delivery_cost")
                var deliveryCost: Int? = 0
        )

        @JsonClass(generateAdapter = true)
        data class Discounts(
                @Json(name = "free_credit_discount")
                var freeCreditDiscount: String? = ""
        )
    }
}