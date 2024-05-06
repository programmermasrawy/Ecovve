package com.q8intouch.ecovve.data.repo

import com.q8intouch.ecovve.network.apis.GiftCardApi
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class GiftCardRepo @Inject constructor(private val giftCardApi: GiftCardApi) {

    fun getGiftCards(page: Int) = giftCardApi.getGiftCards(page).map { it }

    fun getGiftCard(cardId: String) = giftCardApi.getGiftCard(cardId).map { it }
}