package com.q8intouch.ecovve.network.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcovveCafeMenu(
    @Json(name = "data")
    var `data`: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        var id: Int,
        @Json(name = "name")
        var name: String,
        @Json(name = "address")
        var address: String,
        @Json(name = "longitude")
        var longitude: Double,
        @Json(name = "latitude")
        var latitude: Double,
        @Json(name = "phone")
        var phone: String,
        @Json(name = "status")
        var status: String,
        @Json(name = "has_delivery")
        var hasDelivery: String,
        @Json(name = "tax")
        var tax: String,
        @Json(name = "capacity")
        var capacity: Int,
        @Json(name = "opening")
        var opening: String,
        @Json(name = "closing")
        var closing: String,
        @Json(name = "location")
        var location: String,
        @Json(name = "city_id")
        var cityId: Int,
        @Json(name = "brand_id")
        var brandId: Int,
        @Json(name = "current_orders_number")
        var currentOrdersNumber: Int,
        @Json(name = "area_id")
        var areaId: Int,
        @Json(name = "created_at")
        var createdAt: String,
        @Json(name = "updated_at")
        var updatedAt: String,
        @Json(name = "category")
        var category: List<Category>,
        @Json(name = "employee")
        var employee: List<Any>,
        @Json(name = "area")
        var area: Area,
        @Json(name = "review")
        var review: List<Review>,
        @Json(name = "brand")
        var brand: Brand,
        @Json(name = "suborders")
        var suborders: List<Suborder>
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            TODO("category"),
            TODO("employee"),
            TODO("area"),
            TODO("review"),
            TODO("brand"),
            TODO("suborders")
        ) {
        }

        override fun writeToParcel(p0: Parcel?, p1: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun describeContents(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        @JsonClass(generateAdapter = true)
        data class Review(
            @Json(name = "id")
            var id: Int,
            @Json(name = "title")
            var title: String,
            @Json(name = "body")
            var body: String,
            @Json(name = "star")
            var star: String,
            @Json(name = "user_id")
            var userId: Int,
            @Json(name = "outlet_id")
            var outletId: Int,
            @Json(name = "created_at")
            var createdAt: String,
            @Json(name = "updated_at")
            var updatedAt: String
        )

        @JsonClass(generateAdapter = true)
        data class Brand(
            @Json(name = "id")
            var id: Int,
            @Json(name = "name")
            var name: String,
            @Json(name = "description")
            var description: String,
            @Json(name = "business_type")
            var businessType: String,
            @Json(name = "commission_annual")
            var commissionAnnual: String,
            @Json(name = "commission_order_fixed")
            var commissionOrderFixed: String,
            @Json(name = "commission_order_percentage")
            var commissionOrderPercentage: String,
            @Json(name = "logo")
            var logo: String,
            @Json(name = "phone")
            var phone: String,
            @Json(name = "address")
            var address: String,
            @Json(name = "has_delivery")
            var hasDelivery: String,
            @Json(name = "appears_in_search")
            var appearsInSearch: String,
            @Json(name = "minimum_charge")
            var minimumCharge: String,
            @Json(name = "website")
            var website: String,
            @Json(name = "extra_information")
            var extraInformation: String,
            @Json(name = "status")
            var status: String,
            @Json(name = "city_id")
            var cityId: Int,
            @Json(name = "brand_owner_id")
            var brandOwnerId: Int,
            @Json(name = "created_at")
            var createdAt: String,
            @Json(name = "updated_at")
            var updatedAt: String,
            @Json(name = "menu")
            var menu: List<Menu>
        ) {
            @JsonClass(generateAdapter = true)
            data class Menu(
                @Json(name = "id")
                var id: Int,
                @Json(name = "name")
                var name: String,
                @Json(name = "description")
                var description: String,
                @Json(name = "image")
                var image: List<String>,
                @Json(name = "brand_id")
                var brandId: Int,
                @Json(name = "created_at")
                var createdAt: String,
                @Json(name = "updated_at")
                var updatedAt: String
            )
        }

        @JsonClass(generateAdapter = true)
        data class Area(
            @Json(name = "id")
            var id: Int,
            @Json(name = "name_ar")
            var nameAr: String,
            @Json(name = "name_en")
            var nameEn: String,
            @Json(name = "name_fr")
            var nameFr: String,
            @Json(name = "city_id")
            var cityId: Int,
            @Json(name = "created_at")
            var createdAt: String,
            @Json(name = "updated_at")
            var updatedAt: String,
            @Json(name = "city")
            var city: City
        ) {
            @JsonClass(generateAdapter = true)
            data class City(
                @Json(name = "id")
                var id: Int,
                @Json(name = "name_ar")
                var nameAr: String,
                @Json(name = "name_en")
                var nameEn: String,
                @Json(name = "name_fr")
                var nameFr: String,
                @Json(name = "country_id")
                var countryId: Int,
                @Json(name = "created_at")
                var createdAt: Any?,
                @Json(name = "updated_at")
                var updatedAt: Any?
            )
        }

        @JsonClass(generateAdapter = true)
        data class Category(
            @Json(name = "id")
            var id: Int,
            @Json(name = "name")
            var name: String,
            @Json(name = "description")
            var description: String,
            @Json(name = "image")
            var image: String,
            @Json(name = "category_morph_type")
            var categoryMorphType: String,
            @Json(name = "category_morph_id")
            var categoryMorphId: Int,
            @Json(name = "created_at")
            var createdAt: String,
            @Json(name = "updated_at")
            var updatedAt: String
        )

        @JsonClass(generateAdapter = true)
        data class Suborder(
            @Json(name = "id")
            var id: Int,
            @Json(name = "number")
            var number: String,
            @Json(name = "item")
            var item: List<Item>,
            @Json(name = "status")
            var status: String,
            @Json(name = "delivery")
            var delivery: String,
            @Json(name = "total_qty")
            var totalQty: Int,
            @Json(name = "total_price_before_tax")
            var totalPriceBeforeTax: String,
            @Json(name = "tax_val")
            var taxVal: String,
            @Json(name = "total_price_after_tax")
            var totalPriceAfterTax: String,
            @Json(name = "order_id")
            var orderId: Int,
            @Json(name = "outlet_id")
            var outletId: Int,
            @Json(name = "cafe_notes")
            var cafeNotes: List<Any>,
            @Json(name = "delivery_man_id")
            var deliveryManId: Any?,
            @Json(name = "created_at")
            var createdAt: String,
            @Json(name = "updated_at")
            var updatedAt: String
        ) {
            @JsonClass(generateAdapter = true)
            data class Item(
                @Json(name = "id")
                var id: String,
                @Json(name = "qty")
                var qty: String,
                @Json(name = "price_after_promotion")
                var priceAfterPromotion: String,
                @Json(name = "buy_one_get_one_offer")
                var buyOneGetOneOffer: String,
                @Json(name = "outlet_id")
                var outletId: Int
            )
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