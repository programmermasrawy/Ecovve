package com.q8intouch.ecovve.network.apis

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.model.AccountInfoResponse
import com.q8intouch.ecovve.util.URLs
import retrofit2.http.GET
import retrofit2.http.Path

interface AccInfoApi {

    @GET(URLs.ACCOUNT_INFO)
    fun getAccountInfo(
        @Path("userId") userId: String
    ): LiveData<Resource<AccountInfoResponse>>
}