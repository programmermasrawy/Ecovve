package com.q8intouch.ecovve.ui.wishlist

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class WishListViewModel @Inject constructor(
    private val userDataRepo: UserDataRepo, private val cartRepo: CartRepo
    ) : ViewModel() {
    fun favorites() : LiveData<Resource<EcovveUserFavorites>> = userDataRepo.userFavorites().map { it }
    var cart = cartRepo.cart
    fun fetchUserData(id : String): LiveData<Resource<EcovveUser>> {
        return userDataRepo.userInfo(id).map {
            it
        }
    }

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


}
