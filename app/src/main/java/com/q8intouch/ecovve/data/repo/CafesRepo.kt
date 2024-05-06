package com.q8intouch.ecovve.data.repo

import com.q8intouch.ecovve.network.apis.CafesApi
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class CafesRepo @Inject constructor(private val cafesApi: CafesApi) {

    fun getNewCafes() = cafesApi.getNewCafes().map { it }

    fun getTopCafes() = cafesApi.getTopCafes().map { it }
}