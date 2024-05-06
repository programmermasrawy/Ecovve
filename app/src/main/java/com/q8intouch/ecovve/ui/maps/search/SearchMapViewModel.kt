package com.q8intouch.ecovve.ui.maps.search

import androidx.lifecycle.ViewModel;
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.apis.UserAPI
import javax.inject.Inject

class SearchMapViewModel @Inject constructor(
    private val userDataRepo: UserDataRepo,
    private val cartRepo: CartRepo,
    private val userAPI: UserAPI
) : ViewModel() {

//    fun searchLatLon(lat:String?, lon:String?, boundries:String): LiveData<Resource<EcovveLatLonSearch>> {
//        return  userDataRepo.searchLatLon(lat,lon,"").map {
//            it
//        }
//    }
}
