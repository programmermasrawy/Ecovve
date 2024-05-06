package com.q8intouch.ecovve.ui.cart

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveShowItem
import com.q8intouch.ecovve.util.CartUtils
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class CartViewModel @Inject constructor( private val cartRepo: CartRepo
                                        ,private val userDataRepo: UserDataRepo) :  ViewModel() {

    val cart = cartRepo.cart
    val total = cart.map { cart -> CartUtils.calculateCartTotal(cart).toString() }
    var title = R.string.cart

    fun showItem(id: String): LiveData<Resource<EcovveShowItem>> {
        return  userDataRepo.showItem(id).map {
            if (it.isSuccess) {

            }
            return@map it
        }
    }
    fun addItem(shopId:Int, cartItem: CartItem, quantity:Int,shoplogo:String , name:String,activity:Activity,extra : ArrayList<String>){
        cartRepo.addItem(shopId, cartItem, quantity, shoplogo, name,extra,activity)
    }


//    fun removeItem(cartItem: CartItem){
//        cartRepo.removeItem(cartItem)
//    }

}
