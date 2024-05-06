package com.q8intouch.ecovve.network.model
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class CountryResponse(
    @Json(name = "data")
    var country: Country
)

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "id")
    var id: Int,
    @Json(name = "code")
    var code: String,
    @Json(name = "name_ar")
    var nameAr: String,
    @Json(name = "name_en")
    var nameEn: String,
    @Json(name = "name_fr")
    var nameFr: String,
    @Json(name = "created_at")
    var createdAt: Any?,
    @Json(name = "updated_at")
    var updatedAt: Any?,
    @Json(name = "city")
    var city: List<City>
)

@JsonClass(generateAdapter = true)
data class City(
    @Json(name = "id")
    var id: Int
    ,
//    @Json(name = "name_ar")
//    var nameAr: String,
    @Json(name = "name")
    var name: String,
//    @Json(name = "name_fr")
//    var nameFr: String,
    @Json(name = "country_id")
    var countryId: Int,
    @Json(name = "created_at")
    var createdAt: Any?,
    @Json(name = "updated_at")
    var updatedAt: Any?
)


