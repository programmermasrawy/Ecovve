package com.q8intouch.ecovve.data.model

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity
data class ShowReview(
    @Json(name = "data")
    var `data`: Data
)

