package com.q8intouch.ecovve.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.mapper.GiftCardMapper
import com.q8intouch.ecovve.data.repo.GiftCardRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveUser
import com.q8intouch.ecovve.network.model.GiftCardsListResponse
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class GiftCardOrderViewModel @Inject constructor(
        private val giftCardRepo: GiftCardRepo,
        private val giftCardMapper: GiftCardMapper,
        private val userDataRepo: UserDataRepo
) : ViewModel() {

    fun giftCards(page: Int): LiveData<Resource<GiftCardsListResponse>> {
    return  giftCardRepo.getGiftCards(page).map {
        it
    }
    }

    fun fetchUserData(id : String): LiveData<Resource<EcovveUser>> {
        return userDataRepo.userInfo(id).map {
            it
        }
    }
}