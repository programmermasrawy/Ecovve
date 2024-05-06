package com.q8intouch.ecovve.ui.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.apis.AuthAPI
import com.q8intouch.ecovve.network.model.EcovveLatLonSearch
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val userDataRepo: UserDataRepo,
                                          private val authAPI: AuthAPI
) : ViewModel() {

    fun searchLatLon(cat : String ,lat:String?, lon:String?, boundries:String,context:Context,page:Int): LiveData<Resource<EcovveLatLonSearch>> {
        return  userDataRepo.searchLatLon(cat,lat,lon,boundries,context,page).map {
            it
        }
    }
    fun searchLatLon(lat:String?, lon:String?, boundries:String,context:Context,page:Int): LiveData<Resource<EcovveLatLonSearch>> {
        return  userDataRepo.searchLatLon(lat,lon,"500",context,page).map {
            it
        }
    }

    fun searchLatLon(caty:String?,context:Context,page:Int): LiveData<Resource<EcovveLatLonSearch>> {
        return  userDataRepo.searchLatLon(caty!!,context,page).map {
            it
        }
    }

    fun searchLatLon(city:String?,context:Context,page:Int, noon :String): LiveData<Resource<EcovveLatLonSearch>> {
        return  userDataRepo.searchLatLon(city!!,context,page,noon).map {
            it
        }
    }

    fun searchLatLon(caty:String?,city:String,context: Context,page:Int): LiveData<Resource<EcovveLatLonSearch>> {
        return  userDataRepo.searchLatLon(city!!,city,context,page).map {
            it
        }
    }

    fun searchLatLon(context:Context,url : String,page:Int): LiveData<Resource<EcovveLatLonSearch>> {
        return  userDataRepo.searchLatLon(context,url,page).map {
            it
        }
    }
}
