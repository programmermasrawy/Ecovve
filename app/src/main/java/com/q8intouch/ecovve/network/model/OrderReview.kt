package com.q8intouch.ecovve.network.model

data class OrderReview(
        val created_at: String,
        val delivery_man: Int,
        val delivery_time: Int,
        val id: Int,
        val order_id: Int,
        val price_to_value: Int,
        val quality: Int,
        val review: String,
        val seal: Int,
        val updated_at: String
)