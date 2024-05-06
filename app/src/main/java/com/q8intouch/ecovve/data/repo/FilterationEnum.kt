//package com.q8intouch.ecovve.data.repo
//
//enum class FilterationEnum constructor(
//    val color: String,
//    val price: String,
//    val position: Int
//) {
//    SHOES("red", "$", 2),
//    CAR("blue", "$$$$$", 1),
//    BOAT("green", "$$$$$$$$$$$$$", 3)
//}
//
//data class Order(val ProductItem: FilterationEnum? = null)
//
//val orders = listOf(Order(ProductItem.SHOES), Order(ProductItem.CAR), Order(ProductItem.BOAT))
//val sortedOrders = orders.sortedBy { it.ProductItem?.position }