package com.q8intouch.ecovve.network.apis

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.model.FAQListResponse
import com.q8intouch.ecovve.network.model.FAQResponse
import com.q8intouch.ecovve.util.URLs
import retrofit2.http.GET

interface FAQApi {

    @GET(URLs.FAQ_LIST)
    fun getFAQList(): LiveData<Resource<FAQListResponse>>

    @GET(URLs.FAQ)
    fun getFAQ(faqId: String): LiveData<Resource<FAQResponse>>
}
