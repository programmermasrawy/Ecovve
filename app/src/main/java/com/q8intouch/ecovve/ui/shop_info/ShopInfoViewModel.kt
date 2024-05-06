package com.q8intouch.ecovve.ui.shop_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveCafeInfo
import com.q8intouch.ecovve.network.model.EcovveCafeInfoBrand
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class ShopInfoViewModel @Inject constructor(
    private val userDataRepo: UserDataRepo
) : ViewModel() {

    fun cafeInfo(id:String) : LiveData<Resource<EcovveCafeInfo>> {
        return userDataRepo.cafeInfo(id).map {
            it
        }
    }

    fun cafeMenu(id:String) : LiveData<Resource<EcovveCafeInfoBrand>> {
        return userDataRepo.cafeMenu(id).map {
            it
        }
    }
}
