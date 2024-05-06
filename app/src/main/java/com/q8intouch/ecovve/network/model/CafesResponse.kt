package com.q8intouch.ecovve.network.model

import com.squareup.moshi.Json

data class CafesResponse(
    @Json(name = "data")
    val `data`: List<Data>
) {
    data class Data(
        @Json(name = "address")
        val address: String,
        @Json(name = "area_id")
        val areaId: Int,
        @Json(name = "brand")
        val brand: Brand,
        @Json(name = "brand_id")
        val brandId: Int,
        @Json(name = "capacity")
        val capacity: Int,
        @Json(name = "city_id")
        val cityId: Int,
        @Json(name = "closing")
        val closing: String,
        @Json(name = "created_at")
        val createdAt: String,
        @Json(name = "current_orders_number")
        val currentOrdersNumber: Int,
        @Json(name = "has_delivery")
        val hasDelivery: String,
        @Json(name = "id")
        val id: Int,
        @Json(name = "latitude")
        val latitude: Double,
        @Json(name = "location")
        val location: String,
        @Json(name = "longitude")
        val longitude: Double,
        @Json(name = "name")
        val name: String,
        @Json(name = "opening")
        val opening: String,
        @Json(name = "phone")
        val phone: String,
        @Json(name = "status")
        val status: String,
        @Json(name = "tax")
        val tax: String,
        @Json(name = "updated_at")
        val updatedAt: String
    ) {
        data class Brand(
            @Json(name = "address")
            val address: String,
            @Json(name = "appears_in_search")
            val appearsInSearch: String,
            @Json(name = "brand_owner_id")
            val brandOwnerId: Int,
            @Json(name = "business_type")
            val businessType: String,
            @Json(name = "city_id")
            val cityId: Int,
            @Json(name = "commission_annual")
            val commissionAnnual: String,
            @Json(name = "commission_order_fixed")
            val commissionOrderFixed: String,
            @Json(name = "commission_order_percentage")
            val commissionOrderPercentage: String,
            @Json(name = "created_at")
            val createdAt: String,
            @Json(name = "description")
            val description: String,
            @Json(name = "extra_information")
            val extraInformation: String,
            @Json(name = "has_delivery")
            val hasDelivery: String,
            @Json(name = "id")
            val id: Int,
            @Json(name = "logo")
            val logo: String,
            @Json(name = "minimum_charge")
            val minimumCharge: String,
            @Json(name = "name")
            val name: String,
            @Json(name = "phone")
            val phone: String,
            @Json(name = "status")
            val status: String,
            @Json(name = "updated_at")
            val updatedAt: String,
            @Json(name = "website")
            val website: String
        )
    }
}