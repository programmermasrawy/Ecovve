package com.q8intouch.ecovve.network


import com.q8intouch.ecovve.data.repo.UserDataRepo

class EcovveNetworkInterceptor  :  okhttp3.Interceptor {
    var accessToken : String? = null
    override fun intercept(chain: okhttp3.Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val newRequest = request.newBuilder().apply {
            header("Accept","application/json")
            header("Content-Type","application/x-www-form-urlencoded")
            accessToken?.let {
                header("Authorization", "Bearer $it" )
            }
        }

        return chain.proceed(newRequest.build())
    }

}