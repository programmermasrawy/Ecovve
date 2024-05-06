package com.q8intouch.ecovve.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.mapper.GiftCardMapper
import com.q8intouch.ecovve.data.repo.GiftCardRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveContactUs
import com.q8intouch.ecovve.network.model.GiftCardsListResponse
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class ContactUsViewModel @Inject constructor(
        private val userDataRepo: UserDataRepo
) : ViewModel() {
    fun contactUs(name:String,phone:String,address:String): LiveData<Resource<EcovveContactUs>> {
        return  userDataRepo.contactUs(name,phone,address).map {
            it
        }
    }
}