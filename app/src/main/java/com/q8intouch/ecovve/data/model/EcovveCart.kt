package com.q8intouch.ecovve.data.model

import androidx.lifecycle.MutableLiveData

class ShopCart(
    var id: Int,
    var name: String,
    var logo: String,
    var items: MutableLiveData<MutableMap<CartItem,Int>>
){
    override fun equals(other: Any?): Boolean {
        if (other is ShopCart)
            return this.id == other.id
        return false
    }

    override fun hashCode(): Int {
        return id
    }
}

data class CartItem(
    var id:Int,
    var shopId:Int,
    var name: String,
    var image: String,
    var price: Float = 0f
    ,var extra : ArrayList<String>
) {
    override fun equals(other: Any?): Boolean {
        if (other is CartItem)
            return this.id == other.id
        return false
    }

    override fun hashCode(): Int {
        return id
    }
}