package com.q8intouch.ecovve.data.repo

import com.q8intouch.ecovve.network.apis.AccInfoApi
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class AccountInfoRepo @Inject constructor(private val accInfoApi: AccInfoApi) {

    fun getAccountInfo() = accInfoApi.getAccountInfo("20").map { it }
}