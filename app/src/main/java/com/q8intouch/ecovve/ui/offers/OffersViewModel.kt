package com.q8intouch.ecovve.ui.offers

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveAllPromotion
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class OffersViewModel @Inject constructor(private val userDataRepo: UserDataRepo
) : ViewModel()  {

    fun allPromotions(page: Int): LiveData<Resource<EcovveAllPromotion>> {
        return userDataRepo.allPromotions(page).map {
            it
        }
    }

   //
}
