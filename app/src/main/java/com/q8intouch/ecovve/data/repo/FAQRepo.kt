package com.q8intouch.ecovve.data.repo

import com.q8intouch.ecovve.network.apis.FAQApi
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class FAQRepo @Inject constructor(private val faqApi: FAQApi) {

    fun getFAQList() = faqApi.getFAQList().map { it }

    fun getFAQById(faqId: String) = faqApi.getFAQ(faqId).map { it }
}