package com.q8intouch.ecovve.ui.home

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.apis.UserAPI
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val userDataRepo: UserDataRepo,
    private val cartRepo: CartRepo,
    private val userAPI: UserAPI
) : ViewModel() {
    var cart = cartRepo.cart
    fun fetchUserData(id : String): LiveData<Resource<EcovveUser>> {
       return userDataRepo.userInfo(id).map {
           it
       }
    }
    fun prevOrders(id : String) : LiveData<Resource<EcovveUserPrevOrders>>{
        return userDataRepo.prevOrders(id).map {
            it
        }
    }
    fun getAllBanners(): LiveData<Resource<EcovveAllBanners>> {
        return  userDataRepo.getAllBanners().map {
            it
        }
    }
    fun userOrders(id: String,delivery__:String): LiveData<Resource<EcovveUserOrders>> {
        return userDataRepo.userOrders(id,delivery__).map {
            it
        }
    }

    fun userHabits(id: String): LiveData<Resource<EcovveAllHabits>> {
        return userDataRepo.userHabits(id).map {
            it
        }
    }

    fun getMostSold(): LiveData<Resource<EcovveGetMostSold>> {
        return userDataRepo.getMostSold().map {
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

    fun showExtra(id: String): LiveData<Resource<EcovveShowExtra>> {
        return  userDataRepo.showExtra(id).map {
            if (it.isSuccess) {

            }
            return@map it
        }
    }


    fun allPromotions() : LiveData<Resource<EcovveAllPromotion>>{
        return userDataRepo.allPromotions(1).map {
            it
        }
    }

    fun allAreaArea(): LiveData<Resource<EcovveCitiesWithAreas>> {
        return  userDataRepo.allAreaWithCities().map {
            it
        }
    }

    fun allArea() : LiveData<Resource<EcovveAllAreaOutlet>>{
        return userDataRepo.allarea().map {
            it
        }
    }

    fun allCategories() : LiveData<Resource<EcovveAllCategoryResponse>>{
        return userDataRepo.allCategories().map {
            it
        }
    }

    fun addItem(
            shopId: Int,
            cartItem: CartItem,
            quantity: Int,
            shoplogo: String,
            name: String,
            activity: Activity
            ,extra : ArrayList<String>
    ){
        val sharedPreference: Shared = Shared(activity!!)
        sharedPreference.setList("cart", cartRepo.cart.value!!.toList())
        cartRepo.addItem(shopId, cartItem, quantity, shoplogo, name,extra ,activity)
    }


    fun searchBrand(search:String) : LiveData<Resource<EcovveBrandSearch>>{
        return userDataRepo.searchBrand(search).map {
            it
        }
    }
    fun cirtBrand(search:String) : LiveData<Resource<EcovveCityBrand>>{
        return userDataRepo.cityBrand(search).map {
            it
        }
    }
}
