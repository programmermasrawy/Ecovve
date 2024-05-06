package com.q8intouch.ecovve.network.apis

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.model.GiftCardResponse
import com.q8intouch.ecovve.network.model.GiftCardsListResponse
import com.q8intouch.ecovve.util.URLs
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GiftCardApi {

    @GET(URLs.GIFT_CARDS)
    fun getGiftCards(@Query("page") page:Int): LiveData<Resource<GiftCardsListResponse>>

    @GET(URLs.GIFT_CARD)
    fun getGiftCard(@Path("giftId") cardId: String): LiveData<Resource<GiftCardResponse>>
}