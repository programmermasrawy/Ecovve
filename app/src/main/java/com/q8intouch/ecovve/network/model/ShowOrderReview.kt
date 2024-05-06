package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowOrderReview(
    @Json(name = "data")
    var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        var id: Int,
        @Json(name = "seal")
        var seal: Int,
        @Json(name = "delivery_time")
        var deliveryTime: Int,
        @Json(name = "price_to_value")
        var priceToValue: Int,
        @Json(name = "quality")
        var quality: Int,
        @Json(name = "delivery_rating")
        var deliveryRating: Int,
        @Json(name = "order_id")
        var orderId: Int,
        @Json(name = "review")
        var review: String,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "updated_at")
        var updatedAt: String,
        @Json(name = "order")
        var order: Order,
        @Json(name = "items")
        var items: List<Item>
    ) {
        @JsonClass(generateAdapter = true)
        data class Item(
            @Json(name = "id")
            var id: Int,
            @Json(name = "price")
            var price: String,
            @Json(name = "ingredient")
            var ingredient: List<String>,
            @Json(name = "image")
            var image: List<String>,
            @Json(name = "video")
            var video: String,
            @Json(name = "views_number")
            var viewsNumber: Int,
            @Json(name = "purchased_number")
            var purchasedNumber: Int,
            @Json(name = "brand_id")
            var brandId: Int,
            @Json(name = "category_id")
            var categoryId: Int,
            @Json(name = "menu_id")
            var menuId: Int,
            @Json(name = "promotion_id")
            var promotionId: Int,
            @Json(name = "reviews_rating")
            var reviewsRating: Double,
            @Json(name = "reviews_count")
            var reviewsCount: Int,
            @Json(name = "created_at")
            var createdAt: String,
            @Json(name = "updated_at")
            var updatedAt: String,
            @Json(name = "is_favorite")
            var isFavorite: Boolean,
            @Json(name = "name")
            var name: String,
            @Json(name = "description")
            var description: String,
            @Json(name = "pivot")
            var pivot: Pivot
        ) {
            @JsonClass(generateAdapter = true)
            data class Pivot(
                @Json(name = "order_review_id")
                var orderReviewId: Int,
                @Json(name = "item_id")
                var itemId: Int,
                @Json(name = "quality")
                var quality: Int
            )
        }

        @JsonClass(generateAdapter = true)
        data class Order(
            @Json(name = "id")
            var id: Int,
            @Json(name = "number")
            var number: String,
            @Json(name = "status_list")
            var statusList: List<Status>,
            @Json(name = "payment_url")
            var paymentUrl: Any?,
            @Json(name = "status")
            var status: String,
            @Json(name = "delivery")
            var delivery: String,
            @Json(name = "address_id")
            var addressId: Int,
            @Json(name = "pickuptime")
            var pickuptime: Any?,
            @Json(name = "delivery_type")
            var deliveryType: String,
            @Json(name = "total_qty")
            var totalQty: Int,
            @Json(name = "total_price_before_tax")
            var totalPriceBeforeTax: String,
            @Json(name = "tax_val")
            var taxVal: String,
            @Json(name = "total_price_after_tax")
            var totalPriceAfterTax: String,
            @Json(name = "coupon_code")
            var couponCode: String,
            @Json(name = "user_free_credit")
            var userFreeCredit: String,
            @Json(name = "gift_card_id")
            var giftCardId: Int,
            @Json(name = "notes")
            var notes: String,
            @Json(name = "user_id")
            var userId: Int,
            @Json(name = "assigned_user_id")
            var assignedUserId: Int,
            @Json(name = "cart_id")
            var cartId: Int,
            @Json(name = "paid")
            var paid: String,
            @Json(name = "payment_option")
            var paymentOption: String,
            @Json(name = "deliver_time")
            var deliverTime: String,
            @Json(name = "cancelation_time")
            var cancelationTime: Any?,
            @Json(name = "created_at")
            var createdAt: String,
            @Json(name = "updated_at")
            var updatedAt: String,
            @Json(name = "deleted_at")
            var deletedAt: Any?,
            @Json(name = "quality")
            var quality: Int,
            @Json(name = "order_review")
            var orderReview: List<OrderReview>
        ) {
            @JsonClass(generateAdapter = true)
            data class Status(
                @Json(name = "id")
                var id: Int,
                @Json(name = "status_name")
                var statusName: String,
                @Json(name = "state")
                var state: Int,
                @Json(name = "status_order")
                var statusOrder: Int
            )

            @JsonClass(generateAdapter = true)
            data class OrderReview(
                @Json(name = "id")
                var id: Int,
                @Json(name = "seal")
                var seal: Int,
                @Json(name = "delivery_time")
                var deliveryTime: Int,
                @Json(name = "price_to_value")
                var priceToValue: Int,
                @Json(name = "quality")
                var quality: Int,
                @Json(name = "delivery_rating")
                var deliveryRating: Int,
                @Json(name = "order_id")
                var orderId: Int,
                @Json(name = "review")
                var review: String,
                @Json(name = "created_at")
                var createdAt: String,
                @Json(name = "updated_at")
                var updatedAt: String
            )
        }
    }
}