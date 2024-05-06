package com.q8intouch.ecovve.network.apis

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.model.EcovveUser
import com.q8intouch.ecovve.util.URLs
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPI {

    @GET(URLs.USER_DATA)
    fun getUserData(@Path("userId") userId:String): LiveData<Resource<EcovveUser>>
}