package com.q8intouch.ecovve.network.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Ignore
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveUser(
        @Json(name = "data")
        var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
            @Json(name = "id")
            var id: Int?,
            @Json(name = "name")
            var name: String?,
            @Json(name = "email")
            var email: String?,
            @Json(name = "phone")
            var phone: String?,
            @Json(name = "avatar")
            var avatar: String?,
            @Json(name = "area")
            var area: String?,
            @Json(name = "gender")
            var gender: String?,
            @Json(name = "birthday")
            var birthday: String?,
            @Json(name = "points")
            var points: String?,
            @Json(name = "free_credit")
            var freeCredit: String?,
            @Json(name = "verification_code")
            var verificationCode: String?,
            @Json(name = "active")
            var active: Int?,
            @Json(name = "blocked_from_cash")
            var blockedFromCash: Int?,
            @Json(name = "email_active")
            var emailActive: Int?,
            @Json(name = "email_verified_at")
            var emailVerifiedAt: String?,
            @Json(name = "city_id")
            var cityId: Int?,
            @Ignore
//            @Json(name = "used_coupons")
//            var usedCoupons: List<Int>?,
            @Json(name = "sms_notifications")
            var smsNotifications: Int?,
            @Json(name = "news_letter_subscription")
            var newsLetterSubscription: Int?,
            @Json(name = "created_at")
            var createdAt: String?,
            @Json(name = "updated_at")
            var updatedAt: String?,
            @Json(name = "deleted_at")
            var deletedAt: String?,
            @Json(name = "notifications")
            var notifications: Notifications?,
            @Ignore
//            @Json(name = "friends")
//            var friends: Friends?,
            @Json(name = "reviews")
            var reviews: Reviews?,
            @Json(name = "sent_requests")
            var sentRequests: SentRequests?,
            @Json(name = "received_requests")
            var receivedRequests: ReceivedRequests?,
            @Json(name = "received_gift_cards")
            var receivedGiftCards: ReceivedGiftCards?,
            @Json(name = "sent_gift_cards")
            var sentGiftCards: SentGiftCards?,
//            @Ignore
//            @Json(name = "assigned_orders")
//            var assignedOrders: AssignedOrders?,
//            @Ignore
//            @Json(name = "orders")
//            var orders: Orders?,
            @Json(name = "city")
            var city: City?,
            @Json(name = "user_setting")
            var userSetting: UserSetting?,
            @Json(name = "addresses")
            var addresses: List<Addresse>?
    ) {
        @JsonClass(generateAdapter = true)
        data class ReceivedGiftCards(
                @Json(name = "current_page")
                var currentPage: Int?,
                @Json(name = "data")
                var `data`: List<DataRecieved>,
                @Json(name = "first_page_url")
                var firstPageUrl: String?,
                @Json(name = "from")
                var from: Int?,
                @Json(name = "last_page")
                var lastPage: Int?,
                @Json(name = "last_page_url")
                var lastPageUrl: String?,
                @Json(name = "next_page_url")
                var nextPageUrl: String?,
                @Json(name = "path")
                var path: String?,
                @Json(name = "per_page")
                var perPage: Int?,
                @Json(name = "prev_page_url")
                var prevPageUrl: String?,
                @Json(name = "to")
                var to: Int?,
                @Json(name = "total")
                var total: Int?
        ) {
            @JsonClass(generateAdapter = true)
            data class DataRecieved(
                    @Json(name = "id")
                    var id: Int?,
                    @Json(name = "name")
                    var name: String?,
                    @Json(name = "description")
                    var description: String?,
                    @Json(name = "amount")
                    var amount: String?,
                    @Json(name = "status")
                    var status: String?,
                    @Json(name = "expire_date")
                    var expireDate: String?,
                    @Json(name = "giver_id")
                    var giverId: Int?,
                    @Json(name = "taker_id")
                    var takerId: Int?,
                    @Json(name = "created_at")
                    var createdAt: String?,
                    @Json(name = "updated_at")
                    var updatedAt: String?
            )
        }

        @JsonClass(generateAdapter = true)
        data class Notifications(
                @Json(name = "current_page")
                var currentPage: Int?,
                @Json(name = "data")
                var `data`: List<DataNotifications>,
                @Json(name = "first_page_url")
                var firstPageUrl: String?,
                @Json(name = "from")
                var from: Int?,
                @Json(name = "last_page")
                var lastPage: Int?,
                @Json(name = "last_page_url")
                var lastPageUrl: String?,
                @Json(name = "next_page_url")
                var nextPageUrl: String?,
                @Json(name = "path")
                var path: String?,
                @Json(name = "per_page")
                var perPage: Int?,
                @Json(name = "prev_page_url")
                var prevPageUrl: String?,
                @Json(name = "to")
                var to: Int?,
                @Json(name = "total")
                var total: Int?
        ) {
            @JsonClass(generateAdapter = true)
            data class DataNotifications(
                    @Json(name = "id")
                    var id: Int?,
                    @Json(name = "name")
                    var name: String?,
                    @Json(name = "type")
                    var type: Int?,
                    @Json(name = "content")
                    var content: String?,
                    @Json(name = "seen")
                    var seen: String?,
//                    @Json(name = "send_to")
//                    var sendTo: List<String>,
                    @Json(name = "image")
                    var image: String?,
                    @Json(name = "created_at")
                    var createdAt: String?,
                    @Json(name = "updated_at")
                    var updatedAt: String?
//                    ,
//                    @Json(name = "notification_type")
//                    var notificationType: NotificationType
            ) {
                @JsonClass(generateAdapter = true)
                data class NotificationType(
                        @Json(name = "id")
                        var id: Int?,
                        @Json(name = "title")
                        var title: String?,
                        @Json(name = "image")
                        var image: String?,
                        @Json(name = "created_at")
                        var createdAt: String?,
                        @Json(name = "updated_at")
                        var updatedAt: String?
                )
            }
        }

        @JsonClass(generateAdapter = true)
        data class SentRequests(
                @Json(name = "current_page")
                var currentPage: Int?,
                @Json(name = "data")
                var `data`: List<DataSentRequests>,
                @Json(name = "first_page_url")
                var firstPageUrl: String?,
                @Json(name = "from")
                var from: Int?,
                @Json(name = "last_page")
                var lastPage: Int?,
                @Json(name = "last_page_url")
                var lastPageUrl: String?,
                @Json(name = "next_page_url")
                var nextPageUrl: String?,
                @Json(name = "path")
                var path: String?,
                @Json(name = "per_page")
                var perPage: Int?,
                @Json(name = "prev_page_url")
                var prevPageUrl: String?,
                @Json(name = "to")
                var to: Int?,
                @Json(name = "total")
                var total: Int?
        ) {
            @JsonClass(generateAdapter = true)
            data class DataSentRequests(
                    @Json(name = "id")
                    var id: Int?,
                    @Json(name = "status")
                    var status: String?,
                    @Json(name = "sender_id")
                    var senderId: Int?,
                    @Json(name = "receiver_id")
                    var receiverId: Int?,
                    @Json(name = "created_at")
                    var createdAt: String?,
                    @Json(name = "updated_at")
                    var updatedAt: String?
            )
        }

        @JsonClass(generateAdapter = true)
        data class SentGiftCards(
                @Json(name = "current_page")
                var currentPage: Int?,
                @Json(name = "data")
                var `data`: List<DataSentGiftCards>,
                @Json(name = "first_page_url")
                var firstPageUrl: String?,
                @Json(name = "from")
                var from: Int?,
                @Json(name = "last_page")
                var lastPage: Int?,
                @Json(name = "last_page_url")
                var lastPageUrl: String?,
                @Json(name = "next_page_url")
                var nextPageUrl: String?,
                @Json(name = "path")
                var path: String?,
                @Json(name = "per_page")
                var perPage: Int?,
                @Json(name = "prev_page_url")
                var prevPageUrl: String?,
                @Json(name = "to")
                var to: Int?,
                @Json(name = "total")
                var total: Int?
        ) {
            @JsonClass(generateAdapter = true)
            data class DataSentGiftCards(
                    @Json(name = "id")
                    var id: Int?,
                    @Json(name = "name")
                    var name: String?,
                    @Json(name = "description")
                    var description: String?,
                    @Json(name = "amount")
                    var amount: String?,
                    @Json(name = "status")
                    var status: String?,
                    @Json(name = "expire_date")
                    var expireDate: String?,
                    @Json(name = "giver_id")
                    var giverId: Int?,
                    @Json(name = "taker_id")
                    var takerId: Int?,
                    @Json(name = "created_at")
                    var createdAt: String?,
                    @Json(name = "updated_at")
                    var updatedAt: String?
            )
        }

        @JsonClass(generateAdapter = true)
        data class Orders(
                @Json(name = "current_page")
                var currentPage: Int?,
                @Json(name = "data")
                var `data`: List<DataOrders>,
                @Json(name = "first_page_url")
                var firstPageUrl: String?,
                @Json(name = "from")
                var from: Int?,
                @Json(name = "last_page")
                var lastPage: Int?,
                @Json(name = "last_page_url")
                var lastPageUrl: String?,
                @Json(name = "next_page_url")
                var nextPageUrl: String?,
                @Json(name = "path")
                var path: String?,
                @Json(name = "per_page")
                var perPage: Int?,
                @Json(name = "prev_page_url")
                var prevPageUrl: String?,
                @Json(name = "to")
                var to: Int?,
                @Json(name = "total")
                var total: Int?
        ) {
            @JsonClass(generateAdapter = true)
            data class DataOrders(
                    @Json(name = "id")
                    var id: Int?,
                    @Json(name = "number")
                    var number: String?,
                    @Json(name = "status_list")
                    var statusList: List<Status>,
                    @Json(name = "payment_url")
                    var paymentUrl: String?,
                    @Json(name = "status")
                    var status: String?,
                    @Json(name = "delivery")
                    var delivery: String?,
                    @Json(name = "address_id")
                    var addressId: String?,
                    @Json(name = "pickuptime")
                    var pickuptime: String?,
                    @Json(name = "delivery_type")
                    var deliveryType: String?,
                    @Json(name = "total_qty")
                    var totalQty: Int?,
                    @Json(name = "total_price_before_tax")
                    var totalPriceBeforeTax: String?,
                    @Json(name = "tax_val")
                    var taxVal: String?,
                    @Json(name = "total_price_after_tax")
                    var totalPriceAfterTax: String?,
                    @Json(name = "coupon_code")
                    var couponCode: String?,
                    @Json(name = "user_free_credit")
                    var userFreeCredit: String?,
                    @Json(name = "gift_card_id")
                    var giftCardId: String?,
                    @Json(name = "notes")
                    var notes: String?,
                    @Json(name = "user_id")
                    var userId: Int?,
                    @Json(name = "assigned_user_id")
                    var assignedUserId: String?,
                    @Json(name = "cart_id")
                    var cartId: Int?,
                    @Json(name = "paid")
                    var paid: String?,
                    @Json(name = "payment_option")
                    var paymentOption: String?,
                    @Json(name = "deliver_time")
                    var deliverTime: String?,
                    @Json(name = "cancelation_time")
                    var cancelationTime: String?,
                    @Json(name = "created_at")
                    var createdAt: String?,
                    @Json(name = "updated_at")
                    var updatedAt: String??,
                    @Json(name = "deleted_at")
                    var deletedAt: String?,
                    @Json(name = "quality")
                    var quality: String?
            ) {
                @JsonClass(generateAdapter = true)
                data class Status(
                        @Json(name = "id")
                        var id: Int?,
                        @Json(name = "status_name")
                        var statusName: String?,
                        @Json(name = "state")
                        var state: Int?,
                        @Json(name = "status_order")
                        var statusOrder: Int
                )
            }
        }

        @JsonClass(generateAdapter = true)
        data class City(
                @Json(name = "id")
                var id: Int?,
                @Json(name = "name_ar")
                var nameAr: String?,
                @Json(name = "name_en")
                var nameEn: String?,
                @Json(name = "name_fr")
                var nameFr: String?,
                @Json(name = "country_id")
                var countryId: Int?,
                @Json(name = "created_at")
                var createdAt: String?,
                @Json(name = "updated_at")
                var updatedAt: String??
        )

        @JsonClass(generateAdapter = true)
        data class Friends(
                @Json(name = "current_page")
                var currentPage: Int?,
                @Json(name = "data")
                var `data`: List<DataFriends>,
                @Json(name = "first_page_url")
                var firstPageUrl: String?,
                @Json(name = "from")
                var from: Int?,
                @Json(name = "last_page")
                var lastPage: Int?,
                @Json(name = "last_page_url")
                var lastPageUrl: String?,
                @Json(name = "next_page_url")
                var nextPageUrl: String?,
                @Json(name = "path")
                var path: String?,
                @Json(name = "per_page")
                var perPage: Int?,
                @Json(name = "prev_page_url")
                var prevPageUrl: String?,
                @Json(name = "to")
                var to: Int?,
                @Json(name = "total")
                var total: Int?
        ) {
            @JsonClass(generateAdapter = true)
            data class DataFriends(
                    @Json(name = "id")
                    var id: Int?,
                    @Json(name = "name")
                    var name: String?,
                    @Json(name = "email")
                    var email: String?,
                    @Json(name = "phone")
                    var phone: String?,
                    @Json(name = "avatar")
                    var avatar: String?,
                    @Json(name = "area")
                    var area: String?,
                    @Json(name = "gender")
                    var gender: String?,
                    @Json(name = "birthday")
                    var birthday: String?,
                    @Json(name = "points")
                    var points: Int?,
                    @Json(name = "free_credit")
                    var freeCredit: String?,
                    @Json(name = "social_media")
                    var socialMedia: List<String>,
                    @Json(name = "verification_code")
                    var verificationCode: String?,
                    @Json(name = "active")
                    var active: Int?,
                    @Json(name = "blocked_from_cash")
                    var blockedFromCash: Int?,
                    @Json(name = "email_active")
                    var emailActive: Int?,
                    @Json(name = "email_verified_at")
                    var emailVerifiedAt: String?,
                    @Json(name = "city_id")
                    var cityId: Int?,
//                    @Json(name = "used_coupons")
//                    var usedCoupons: List<Int>,
                    @Json(name = "sms_notifications")
                    var smsNotifications: Int?,
                    @Json(name = "news_letter_subscription")
                    var newsLetterSubscription: Int?,
                    @Json(name = "created_at")
                    var createdAt: String?,
                    @Json(name = "updated_at")
                    var updatedAt: String??,
                    @Json(name = "deleted_at")
                    var deletedAt: String?
            )
        }

        @JsonClass(generateAdapter = true)
        data class UserSetting(
                @Json(name = "id")
                var id: Int?,
                @Json(name = "notification")
                var notification: String?,
                @Json(name = "report_type")
                var reportType: String?,
                @Json(name = "language")
                var language: String?,
                @Json(name = "user_id")
                var userId: Int?,
                @Json(name = "created_at")
                var createdAt: String?,
                @Json(name = "updated_at")
                var updatedAt: String?
        )

        @JsonClass(generateAdapter = true)
        data class ReceivedRequests(
                @Json(name = "current_page")
                var currentPage: Int?,
                @Json(name = "data")
                var `data`: List<DataReceivedRequests>,
                @Json(name = "first_page_url")
                var firstPageUrl: String?,
                @Json(name = "from")
                var from: Int?,
                @Json(name = "last_page")
                var lastPage: Int?,
                @Json(name = "last_page_url")
                var lastPageUrl: String?,
                @Json(name = "next_page_url")
                var nextPageUrl: String?,
                @Json(name = "path")
                var path: String?,
                @Json(name = "per_page")
                var perPage: Int?,
                @Json(name = "prev_page_url")
                var prevPageUrl: String?,
                @Json(name = "to")
                var to: Int?,
                @Json(name = "total")
                var total: Int?
        ) {
            @JsonClass(generateAdapter = true)
            data class DataReceivedRequests(
                    @Json(name = "id")
                    var id: Int?,
                    @Json(name = "status")
                    var status: String?,
                    @Json(name = "sender_id")
                    var senderId: Int?,
                    @Json(name = "receiver_id")
                    var receiverId: Int?,
                    @Json(name = "created_at")
                    var createdAt: String?,
                    @Json(name = "updated_at")
                    var updatedAt: String?
            )
        }


        @JsonClass(generateAdapter = true)
        data class Reviews(
                @Json(name = "current_page")
                var currentPage: Int?,
                @Json(name = "data")
                var `data`: List<ReviewITem>,
                @Json(name = "first_page_url")
                var firstPageUrl: String?,
                @Json(name = "from")
                var from: String?,
                @Json(name = "last_page")
                var lastPage: Int?,
                @Json(name = "last_page_url")
                var lastPageUrl: String?,
                @Json(name = "next_page_url")
                var nextPageUrl: String?,
                @Json(name = "path")
                var path: String?,
                @Json(name = "per_page")
                var perPage: Int?,
                @Json(name = "prev_page_url")
                var prevPageUrl: String?,
                @Json(name = "to")
                var to: String?,
                @Json(name = "total")
                var total: Int?
        ){
            @JsonClass(generateAdapter = true)
            data class ReviewITem(
                    @Json(name = "id")
                    var id: Int?,
                    @Json(name = "title")
                    var title: String?,
                    @Json(name = "body")
                    var body: String?,
                    @Json(name = "star")
                    var star: String?,
                    @Json(name = "user_id")
                    var user_id: Int?,
                    @Json(name = "outlet_id")
                    var outlet_id: String?,
                    @Json(name = "created_at")
                    var createdAt: String?,
                    @Json(name = "updated_at")
                    var updatedAt: String?
            ) : Parcelable {
                constructor(parcel: Parcel) : this(
                        parcel.readValue(Int::class.java.classLoader) as? Int,
                        parcel.readString(),
                        parcel.readString(),
                        parcel.readString(),
                        parcel.readValue(Int::class.java.classLoader) as? Int,
                        parcel.readString(),
                        parcel.readString(),
                        parcel.readString()) {
                }

                override fun writeToParcel(parcel: Parcel, flags: Int) {
                    parcel.writeValue(id)
                    parcel.writeString(title)
                    parcel.writeString(body)
                    parcel.writeString(star)
                    parcel.writeValue(user_id)
                    parcel.writeString(outlet_id)
                    parcel.writeString(createdAt)
                    parcel.writeString(updatedAt)
                }

                override fun describeContents(): Int {
                    return 0
                }

                companion object CREATOR : Parcelable.Creator<ReviewITem> {
                    override fun createFromParcel(parcel: Parcel): ReviewITem {
                        return ReviewITem(parcel)
                    }

                    override fun newArray(size: Int): Array<ReviewITem?> {
                        return arrayOfNulls(size)
                    }
                }
            }
        }

        @JsonClass(generateAdapter = true)
        data class AssignedOrders(
                @Json(name = "current_page")
                var currentPage: Int?,
                @Json(name = "data")
                var `data`: List<DataAssignedOrders>,
                @Json(name = "first_page_url")
                var firstPageUrl: String?,
                @Json(name = "from")
                var from: Int?,
                @Json(name = "last_page")
                var lastPage: Int?,
                @Json(name = "last_page_url")
                var lastPageUrl: String?,
                @Json(name = "next_page_url")
                var nextPageUrl: String?,
                @Json(name = "path")
                var path: String?,
                @Json(name = "per_page")
                var perPage: Int?,
                @Json(name = "prev_page_url")
                var prevPageUrl: String?,
                @Json(name = "to")
                var to: Int?,
                @Json(name = "total")
                var total: Int?
        ) {
            @JsonClass(generateAdapter = true)
            data class DataAssignedOrders(
                    @Json(name = "id")
                    var id: Int?,
                    @Json(name = "number")
                    var number: String?,
                    @Json(name = "status_list")
                    var statusList: List<Status>,
                    @Json(name = "payment_url")
                    var paymentUrl: String?,
                    @Json(name = "status")
                    var status: String?,
                    @Json(name = "delivery")
                    var delivery: String?,
                    @Json(name = "address_id")
                    var addressId: Int?,
                    @Json(name = "pickuptime")
                    var pickuptime: String?,
                    @Json(name = "delivery_type")
                    var deliveryType: String?,
                    @Json(name = "total_qty")
                    var totalQty: Int?,
                    @Json(name = "total_price_before_tax")
                    var totalPriceBeforeTax: String?,
                    @Json(name = "tax_val")
                    var taxVal: String?,
                    @Json(name = "total_price_after_tax")
                    var totalPriceAfterTax: String?,
                    @Json(name = "coupon_code")
                    var couponCode: String?,
                    @Json(name = "user_free_credit")
                    var userFreeCredit: String?,
                    @Json(name = "gift_card_id")
                    var giftCardId: Int?,
                    @Json(name = "notes")
                    var notes: String?,
                    @Json(name = "user_id")
                    var userId: Int?,
                    @Json(name = "assigned_user_id")
                    var assignedUserId: Int?,
                    @Json(name = "cart_id")
                    var cartId: Int?,
                    @Json(name = "paid")
                    var paid: String?,
                    @Json(name = "payment_option")
                    var paymentOption: String?,
                    @Json(name = "deliver_time")
                    var deliverTime: String?,
                    @Json(name = "cancelation_time")
                    var cancelationTime: String?,
                    @Json(name = "created_at")
                    var createdAt: String?,
                    @Json(name = "updated_at")
                    var updatedAt: String?,
                    @Json(name = "deleted_at")
                    var deletedAt: String?,
                    @Json(name = "quality")
                    var quality: Int?,
                    @Json(name = "order_review")
                    var orderReview: List<OrderReview>
            ) {
                @JsonClass(generateAdapter = true)
                data class Status(
                        @Json(name = "id")
                        var id: Int?,
                        @Json(name = "status_name")
                        var statusName: String?,
                        @Json(name = "state")
                        var state: Int?,
                        @Json(name = "status_order")
                        var statusOrder: Int
                )

                @JsonClass(generateAdapter = true)
                data class OrderReview(
                        @Json(name = "id")
                        var id: Int?,
                        @Json(name = "seal")
                        var seal: Int?,
                        @Json(name = "delivery_time")
                        var deliveryTime: Int?,
                        @Json(name = "price_to_value")
                        var priceToValue: Int?,
                        @Json(name = "quality")
                        var quality: Int?,
                        @Json(name = "delivery_rating")
                        var deliveryRating: Int?,
                        @Json(name = "order_id")
                        var orderId: Int?,
                        @Json(name = "review")
                        var review: String?,
                        @Json(name = "created_at")
                        var createdAt: String?,
                        @Json(name = "updated_at")
                        var updatedAt: String?
                )
            }
        }

        @JsonClass(generateAdapter = true)
        data class Addresse(
                @Json(name = "id")
                var id: Int?,
                @Json(name = "user_id")
                var userId: Int?,
                @Json(name = "name")
                var name: String?,
                @Json(name = "phone")
                var phone: String?,
                @Json(name = "area_id")
                var areaId: Int?,
                @Json(name = "street")
                var street: String?,
                @Json(name = "block")
                var block: String?,
                @Json(name = "parcel")
                var parcel: String?,
                @Json(name = "building")
                var building: String?,
                @Json(name = "floor")
                var floor: String?,
                @Json(name = "additional")
                var additional: String?,
                @Json(name = "lat")
                var lat: Double?,
                @Json(name = "lng")
                var lng: Double?,
                @Json(name = "created_at")
                var createdAt: String?,
                @Json(name = "updated_at")
                var updatedAt: String?
        )
    }
}