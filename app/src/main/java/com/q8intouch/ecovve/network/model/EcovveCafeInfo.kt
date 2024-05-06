package com.q8intouch.ecovve.network.model

import android.os.Parcel
import android.os.Parcelable

data class EcovveCafeInfo(
        var `data`: Data?
) {
    data class Data(
            var address: String?, // 8164 Kallie RapidDameonbury, OR 41822
            var area: Area?,
            var area_id: Int?, // 11
            var avg_delivery_fee: String?, // 23.00
            var avg_delivery_time: Int?, // 42
            var brand: Brand?,
            var brand_id: Int?, // 48
            var capacity: Int?, // 8937
            var category: List<Category?>?,
            var city_id: Int?, // 6
//            var closing: String?, // 04:00:00
            var created_at: String?, // 2019-04-30 22:59:57
            var current_orders_number: Int?, // 0
            var deleted_at: String?, // null
            var delivery_area: List<DeliveryArea?>?,
            var employees: List<Employee?>?,
            var has_delivery: String?, // no
            var id: Int?, // 1
            var is_open: Boolean?, // false
            var latitude: Double?, // -76.7020160000000004174580681137740612030029296875
            var location: String?, // Raynorborough
            var longitude: Double?, // 44.60980399999999690408003516495227813720703125
            var name: String?, // eum
            var working_days: List<workingDays>?,
            var outlet_owner_id: Int?, // 21
            var phone: String?, // 1-454-877-0431
            var reviews_count: Int?, // 40
            var reviews_rating: Double?, // 3.00
            var review: List<Review?>?, // 3.00
            var status: String?, // banned
            var tax: String?, // 7.00
            var updated_at: String? // 2019-04-30 22:59:57
    ) : Parcelable {


        constructor(parcel: Parcel) : this(
                parcel.readString(),
                TODO("area"),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                TODO("brand"),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Int::class.java.classLoader) as? Int,
                TODO("category"),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                TODO("delivery_area"),
                TODO("employees"),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
                parcel.readValue(Double::class.java.classLoader) as? Double,
                parcel.readString(),
                parcel.readValue(Double::class.java.classLoader) as? Double,
                parcel.readString(),
                TODO("working_days"),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Double::class.java.classLoader) as? Double,
                TODO("review"),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()) {
        }

        data class Review(var id: Int?,
                          var title: String?, // 25
                          var body: String?,
                          var star: String?,
                          var updated_at: String?
        ) {
        }

        data class workingDays(var id: Int?,
                               var start_time: String?,
                               var end_time: String?,
                               var day_id: String?,
                               var days: Day?
        ) {
            class Day (
                var id: Int?,
                var day_name: String?
            )
        }

        data class Area(
                var city: City?,
                var city_id: Int?, // 3889
                var created_at: String?, // null
                var id: Int?, // 11
                var name_ar: String?, // الدوحة
                var name_en: String?, // الدوحة
                var updated_at: String? // null
        ) {
            data class City(
                    var country_id: Int?, // 112
                    var created_at: String?, // null
                    var id: Int?, // 3889
                    var name_ar: String?, // العاصمة
                    var name_en: String?, // Capital
                    var name_fr: String?, // Capital
                    var updated_at: String? // null
            )
        }

        data class Brand(
                var address: String?, // 7619 Crona RouteRueckerfurt, ID 60743
                var appears_in_search: String?, // yes
                var brand_owner_id: Int?, // 15
                var business_type: String?, // quod
                var city_id: Int?, // 2
                var commission_annual: String?, // 20000
                var commission_order_fixed: String?, // 100
                var commission_order_percentage: String?, // 5
                var cover: String?, // images/placeholder.png
                var created_at: String?, // 2019-04-30 22:59:56
                var deleted_at: String?, // null
                var description: String?, // Aut occaecati deleniti ut ex adipisci doloribus in.
                var extra_information: String?, // Occaecati dolores nobis magnam ut et reiciendis eius est.
                var has_delivery: String?, // yes
                var id: Int?, // 48
                var logo: String?, // images/placeholder.png
                var menu: List<Menu?>?,
                var minimum_charge: String?, // 7.00
                var name: String?, // rerum
                var phone: String?, // 787.612.9120
                var reviews_count: Int?, // 42
                var reviews_rating: Double?, // 5.00
                var status: String?, // activated
                var supports_cc: Int?, // 1
                var supports_knet: Int?, // 0
                var updated_at: String?, // 2019-04-30 22:59:56
                var website: String? // www.rerum.com
        ) {
            data class Menu(
                    var brand_id: Int?, // 48
                    var created_at: String?, // 2019-04-30 22:59:56
                    var description: String?, // Nyasia Batz II
                    var id: Int?, // 99
                    var image: List<String?>?,
                    var name: String?, // Kelsie Rempel
                    var updated_at: String? // 2019-04-30 22:59:56
            )
        }

        data class Category(
                var created_at: String?, // 2019-04-30 22:59:56
                var description: String?, // Dr. Maureen Huels Jr.
                var id: Int?, // 26
                var image: String?, // images/placeholder.png
                var name: String?, // Rebeca Baumbach
                var pivot: Pivot?,
                var updated_at: String? // 2019-04-30 22:59:56
        ) {
            data class Pivot(
                    var category_id: Int?, // 26
                    var outlet_id: Int? // 1
            )
        }

        data class Employee(
                var created_at: String?, // 2019-04-30 22:59:56
                var employable_id: Int?, // 1
                var employable_type: String?, // App\Models\Outlet
                var id: Int?, // 3
                var updated_at: String?, // 2019-04-30 22:59:56
                var user_id: Int? // 7
        )

        data class DeliveryArea(
                var city_id: Int?, // 3889
                var created_at: String?, // null
                var id: Int?, // 25
                var name_ar: String?, // الشويخ
                var name_en: String?, // الشويخ
                var pivot: Pivot?,
                var updated_at: String? // null
        ) {
            data class Pivot(
                    var area_id: Int?, // 25
                    var delivery_availability: String?, // on
                    var delivery_fee: String?, // 6.00
                    var delivery_time: Int?, // 41
                    var outlet_id: Int? // 1
            )
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(address)
            parcel.writeValue(area_id)
            parcel.writeString(avg_delivery_fee)
            parcel.writeValue(avg_delivery_time)
            parcel.writeValue(brand_id)
            parcel.writeValue(capacity)
            parcel.writeValue(city_id)
            parcel.writeString(created_at)
            parcel.writeValue(current_orders_number)
            parcel.writeString(deleted_at)
            parcel.writeString(has_delivery)
            parcel.writeValue(id)
            parcel.writeValue(is_open)
            parcel.writeValue(latitude)
            parcel.writeString(location)
            parcel.writeValue(longitude)
            parcel.writeString(name)
            parcel.writeValue(outlet_owner_id)
            parcel.writeString(phone)
            parcel.writeValue(reviews_count)
            parcel.writeValue(reviews_rating)
            parcel.writeString(status)
            parcel.writeString(tax)
            parcel.writeString(updated_at)
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