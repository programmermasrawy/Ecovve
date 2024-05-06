package com.q8intouch.ecovve.network.apis

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.model.EcovveBrandSearch
import com.q8intouch.ecovve.util.URLs
import retrofit2.http.*

interface SearchBrandAPI {

    @POST(URLs.SEARCH_BRAND)
    @FormUrlEncoded
    fun searchBrand(@Field("user_id") search:String): LiveData<Resource<EcovveBrandSearch>>

}