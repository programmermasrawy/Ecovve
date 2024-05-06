package com.q8intouch.ecovve.ui.offers

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveOfferItems
import com.q8intouch.ecovve.network.model.EcovveShowExtra
import com.q8intouch.ecovve.network.model.EcovveShowItem
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class OfferItemsViewModel @Inject constructor(private val userDataRepo: UserDataRepo
                                              ,private val cartRepo: CartRepo
) : ViewModel() {

    val cart = cartRepo.cart
    fun showItem(id: String): LiveData<Resource<EcovveShowItem>> {
        return  userDataRepo.showItem(id).map {
            if (it.isSuccess) {

            }
            return@map it
        }
    }
    fun showExtra(id: String): LiveData<Resource<EcovveShowExtra>> {
        return  userDataRepo.showExtra(id).map {
            if (it.isSuccess) {

            }
            return@map it
        }
    }

    fun addItem(shopId:Int, cartItem: CartItem, quantity:Int,shoplogo:String , name:String,activity:Activity,extra : ArrayList<String>){
        cartRepo.addItem(shopId, cartItem, quantity, shoplogo, name,extra,activity)
    }

    fun showofferItems(id:String,page:Int) : LiveData<Resource<EcovveOfferItems>> {
        return userDataRepo.showOfferitems(id,page).map {
            it
        }
    }
}
