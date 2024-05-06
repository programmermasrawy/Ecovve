package com.q8intouch.ecovve.network.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class EcovveUserOrders(
        var current_page: Int?, // 1
        var `data`: List<Data>,
        var first_page_url: String?, // https://ecovve.com/ar/api/public/user/user_orders/1?page=1
        var from: Int?, // 1
        var last_page: Int?, // 3
        var last_page_url: String?, // https://ecovve.com/ar/api/public/user/user_orders/1?page=3
        var next_page_url: String?, // https://ecovve.com/ar/api/public/user/user_orders/1?page=2
        var path: String?, // https://ecovve.com/ar/api/public/user/user_orders/1
        var per_page: Int?, // 10
        var prev_page_url: String?, // null
        var to: Int?, // 10
        var total: Int? // 28
) {
    data class Data(
            var address_id: String?, // null
            var assigned_user_id: String?, // null
            var cancellation_time: String?, // null
            var coupon_id: String?, // null
            var created_at: String?, // 2019-04-07 18:25:20
            var deleted_at: String?, // null
            var deliver_time: String?, // null
            var delivery: String?, // not_delivered
            var delivery_type: String?, // pickup
            var final_price: String?, // 182.00
            var gift_card_id: Int?, // 32
            var id: Int?, // 19
            var initial_price: String?, // 182.00
            var notes: String?, // notes
            var order_review: List<OrderReview?>?,
            var outlet: Outlet?,
            var outlet_id: Int?, // 1
            var paid: String?, // no
            var payment_option: String?, // cash
            var payment_url: String?, // https://www.knetpaytest.com.kw:443/CGW302/hppaction?formAction=com.aciworldwide.commerce.gateway.payment.action.HostedPaymentPageAction&?PaymentID=3682685261990970
            var pickup_time: String?, // 2019-04-07 00:00:00
            var quality: Int?, // 2
            var status: String?, // payment_pending
            var status_list: List<Status?>?,
            var track_id: String?, // 1-5caa24704b4c9
            var updated_at: String?, // 2019-04-07 18:25:21
            var user_free_credit: String?, // 0
            var user_id: Int? ,
            var items : List<DataItems>,
            var delivery_man : User
    ) : Parcelable {
        @JsonClass(generateAdapter = true)
        data class DataItems(
                @Json(name = "id")
                var id: Int?,
                @Json(name = "price")
                var price: Float?,
                @Json(name = "price_after_promotion")
                var price_after_promotion: Float?,
                @Json(name = "image")
                var image: List<String>,
                @Json(name = "views_number")
                var viewsNumber: Int?,
                @Json(name = "purchased_number")
                var purchasedNumber: Int?,
                @Json(name = "brand_id")
                var brandId: Int?,
                @Json(name = "category_id")
                var categoryId: Int?,
                @Json(name = "menu_id")
                var menuId: Int?,
                @Json(name = "promotion_id")
                var promotionId: Int?,
                @Json(name = "reviews_rating")
                var reviewsRating: Double?,
                @Json(name = "reviews_count")
                var reviewsCount: Int?,
                @Json(name = "created_at")
                var createdAt: String?,
                @Json(name = "updated_at")
                var updatedAt: String?,
                @Json(name = "is_favorite")
                var isFavorite: Boolean,
                @Json(name = "name")
                var name: String?,
                @Json(name = "description")
                var description: String?
        )

        data class Status(
                var id: Int?, // 1
                var state: Int?, // 0
                var status_name: String?, // Dr. Conner Lakin
                var status_order: Int? // 5
        )

        data class OrderReview(
                var created_at: String?, // 2019-04-06 12:07:31
                var delivery_man: Int?, // 1
                var delivery_time: Int?, // 2
                var id: Int?, // 32
                var order_id: Int?, // 19
                var price_to_value: Int?, // 3
                var quality: Int?, // 4
                var review: String?, // Possimus molestiae vel nisi sunt aut aut unde optio.
                var seal: Int?, // 1
                var updated_at: String? // 2019-04-06 12:07:31
        )

        data class Outlet(
                var address: String?, // 39413 Koch StreamDarrylport, AL 02387
                var area_id: Int?, // 22
                var avg_delivery_fee: String?, // 14.00
                var avg_delivery_time: Int?, // 3
                var brand: Brand?,
                var brand_id: Int?, // 8
                var capacity: Int?, // 5028
                var city_id: Int?, // 17
                var closing: String?, // 02:00:00
                var created_at: String?, // 2019-04-06 12:06:12
                var current_orders_number: Int?, // 0
                var deleted_at: String?, // null
                var has_delivery: String?, // yes
                var id: Int?, // 1
                var is_open: Boolean?, // false
                var latitude: Double?, // 62.176907999999997400664142332971096038818359375
                var location: String?, // East Myrlmouth
                var longitude: Double?, // 79.7219989999999967267285683192312717437744140625
                var name: String?, // consectetur
                var opening: String?, // 14:00:00
                var outlet_owner_id: Int?, // 44
                var phone: String?, // 1-913-253-3007
                var reviews_count: Int?, // 64
                var reviews_rating: String?, // 3.00
                var status: String?, // banned
                var tax: String?, // 17.00
                var updated_at: String? // 2019-04-06 12:06:12
        ) {
            data class Brand(
                    var address: String?, // 716 Shawna GroveAnaborough, PA 94990-9586
                    var appears_in_search: String?, // no
                    var brand_owner_id: Int?, // 48
                    var business_type: String?, // alias
                    var city_id: Int?, // 2
                    var commission_annual: String?, // 20000
                    var commission_order_fixed: String?, // 100
                    var commission_order_percentage: String?, // 5
                    var cover: String?, // images/placeholder.png
                    var created_at: String?, // 2019-04-06 12:06:08
                    var deleted_at: String?, // null
                    var description: String?, // Nemo eligendi totam nisi aut maiores.
                    var extra_information: String?, // Corrupti veniam adipisci voluptatem rem maxime.
                    var has_delivery: String?, // no
                    var id: Int?, // 8
                    var logo: String?, // images/placeholder.png
                    var minimum_charge: String?, // 9.00
                    var name: String?, // consequatur
                    var phone: String?, // 268-593-9262 x050
                    var reviews_count: Int?, // 39
                    var reviews_rating: String?, // 5.00
                    var status: String?, // banned
                    var supports_cc: Int?, // 0
                    var supports_knet: Int?, // 0
                    var updated_at: String?, // 2019-04-06 12:06:08
                    var website: String? // www.consequatur.com
            )
        }

        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readString(),
                TODO("order_review"),
                TODO("outlet"),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                TODO("status_list"),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                TODO("items") ,
                TODO("deliver_man")) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(address_id)
            parcel.writeString(assigned_user_id)
            parcel.writeString(cancellation_time)
            parcel.writeString(coupon_id)
            parcel.writeString(created_at)
            parcel.writeString(deleted_at)
            parcel.writeString(deliver_time)
            parcel.writeString(delivery)
            parcel.writeString(delivery_type)
            parcel.writeString(final_price)
            parcel.writeValue(gift_card_id)
            parcel.writeValue(id)
            parcel.writeString(initial_price)
            parcel.writeString(notes)
            parcel.writeValue(outlet_id)
            parcel.writeString(paid)
            parcel.writeString(payment_option)
            parcel.writeString(payment_url)
            parcel.writeString(pickup_time)
            parcel.writeValue(quality)
            parcel.writeString(status)
            parcel.writeString(track_id)
            parcel.writeString(updated_at)
            parcel.writeString(user_free_credit)
            parcel.writeValue(user_id)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Data> {
            override fun createFromParcel(parcel: Parcel): Data {
                return Data(parcel)
            }

            override fun newArray(size: Int): Array<Data?> {
                return arrayOfNulls(size)
            }
        }


    }
}