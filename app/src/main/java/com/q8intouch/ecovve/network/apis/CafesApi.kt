package com.q8intouch.ecovve.network.apis

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.model.CafesResponse
import com.q8intouch.ecovve.util.URLs
import retrofit2.http.GET

interface CafesApi {

    @GET(URLs.NEW_CAFES)
    fun getNewCafes(): LiveData<Resource<CafesResponse>>

    @GET(URLs.TOP_CAFES)
    fun getTopCafes(): LiveData<Resource<*>>
}
