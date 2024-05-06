package com.q8intouch.ecovve.network.apis

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.model.MenuResponse

import com.q8intouch.ecovve.util.URLs
import retrofit2.http.*

interface MenuAPI {

    @GET(URLs.ShowAllMenu)
    fun showAllMenu(): LiveData<Resource<MenuResponse>>

     @GET(URLs.ShowMenu)
     fun showMenu(@Path("id") id :String): LiveData<Resource<MenuResponse>>

}