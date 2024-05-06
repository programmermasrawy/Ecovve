package com.q8intouch.ecovve.ui.gift

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.mapper.GiftCardMapper
import com.q8intouch.ecovve.data.repo.GiftCardRepo
import com.q8intouch.ecovve.network.model.GiftCardsListResponse
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class BuyGiftCardViewModel @Inject constructor(
        private val giftCardRepo: GiftCardRepo,
        private val giftCardMapper: GiftCardMapper
) : ViewModel() {

    fun giftCards(page: Int): LiveData<Resource<GiftCardsListResponse>> {
        return  giftCardRepo.getGiftCards(page).map {
            it
        }
    }

}