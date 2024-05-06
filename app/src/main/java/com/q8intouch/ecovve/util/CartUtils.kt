package com.q8intouch.ecovve.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.model.ShopCart

object CartUtils{
    fun calculateShopCartItemTotal(cartItem: LiveData<CartItem>, quantity: LiveData<Int>): LiveData<Float> {
        return MutableLiveData<Float>().apply { value = calculateShopCartItemTotal(cartItem.value!!, quantity.value!!) }
    }

    fun calculateShopCartItemTotal(cartItem: CartItem, quantity: Int): Float {
        return cartItem.price * quantity
    }

    fun calculateShopCartTotal(shopCartItems: MutableLiveData<MutableMap<CartItem,Int>>): MutableLiveData<Float> {
        return MutableLiveData<Float>().apply { value = calculateShopCartTotal( shopCartItems.value!!) }
    }

    fun calculateShopCartTotal(shopCartItems: MutableMap<CartItem,Int>): Float {
        var total = 0f
        shopCartItems.toList().forEach { pair: Pair<CartItem, Int> ->
            total += calculateShopCartItemTotal(pair.first, pair.second)
        }
        return total
    }

    fun calculateShopCartTotalQuantity(shopCartItems: MutableMap<CartItem,Int>): Int {
        var total = 0
        shopCartItems.toList().forEach { pair: Pair<CartItem, Int> ->
            total += pair.second
        }
        return total
    }

    fun calculateCartTotal(shopsCarts: MutableLiveData<MutableMap<Int, ShopCart>>): MutableLiveData<Float> {
        return MutableLiveData<Float>().apply { value = calculateCartTotal( shopsCarts.value!!) }
    }

    fun calculateCartTotal(shopsCarts: MutableMap<Int, ShopCart>): Float {
        var total = 0f
        shopsCarts.forEach { shopsCart ->
            total += calculateShopCartTotal(shopsCart.value.items.value!!)
        }
        return total
    }
}
