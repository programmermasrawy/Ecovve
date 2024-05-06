package com.q8intouch.ecovve.data.repo

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.data.AppDatabase
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.model.ShopCart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepo @Inject constructor (val appDatabase: AppDatabase) {

    public  var cart = MutableLiveData<MutableMap<Int, ShopCart>>().apply {  value = mutableMapOf()}

    fun addItem(
            shopId: Int,
            cartItem: CartItem,
            quantity: Int,
            shoplogo: String,
            name: String,
            extra : ArrayList<String>,
            context: Activity
    ){
        cart.run {
            value = value?.also {
                it[shopId]?.items?.run { value = value?.apply { put(cartItem,quantity)} }
                    ?: it.put(shopId,createShopCartWithAnewItem(shopId,cartItem,quantity,shoplogo,name,extra))
            }
        }
        cartBadge(context)
    }
    @SuppressLint("SetTextI18n")
    fun cartBadge(activity: Activity) {
        var num = 0
        if (cart.value !=null && cart.value!!.toList().isNotEmpty()) {
            cart.value!!.toList()[0].second.items.value!!.toList().forEach { item ->
                num += item.second
            }
        }

        if (num==0){
            val image =  activity.findViewById<TextView>(R.id.imageBadge)
            image.visibility = View.GONE
        }
        else {

            val image = activity.findViewById<TextView>(R.id.imageBadge)
            if (image != null) {
                image.visibility = View.VISIBLE
                image.text = "" + num
            }
        }
    }
    fun removeItem(cartItem: CartItem, parentActivity: AppCompatActivity){
        cart.run {
            value = value?.also {
                it[cartItem.shopId]?.items?.run {
                    value = value?.apply {
                        remove(cartItem)
                        if (size == 0 ) {
                            it.remove(cartItem.shopId)

                        }
                    }
                }
            }
        }
        cartBadge(parentActivity)
    }

    private fun createShopCart(shopId: Int, image: String, name: String, extra: ArrayList<String>) : ShopCart {
        return ShopCart(shopId, name,
            image,
            MutableLiveData<MutableMap<CartItem, Int>>().apply {
                value = mutableMapOf()
            })
    }
    private fun createShopCartWithAnewItem(
            shopId: Int,
            cartItem: CartItem,
            quantity: Int,
            image: String,
            name: String,
            extra: ArrayList<String>
    ) : ShopCart {
        return createShopCart(shopId,image,name,extra).apply { items.value!![cartItem] = quantity }
    }
}

