package com.q8intouch.ecovve.network.apis

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.model.cart.CartRow
import com.q8intouch.ecovve.util.URLs
import retrofit2.http.*

interface CartAPI {

    @POST(URLs.UPDATE_CART_STORE)
    fun UpdateCart(@Path("id") id :String ,@Body cartRow: CartRow): LiveData<Resource<CartRow>>

    @DELETE(URLs.UPDATE_CART_STORE)
    fun deleteCart(@Path("id") id :String ,@Body cartRow: CartRow): LiveData<Resource<CartRow>>
}