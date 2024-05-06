package com.q8intouch.ecovve.data.repo

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.apis.SearchBrandAPI
import com.q8intouch.ecovve.network.model.EcovveBrandSearch
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class SearchBrandRepo @Inject constructor(private val searchBrandAPI: SearchBrandAPI) {

    fun searchBrand(search: String): LiveData<Resource<EcovveBrandSearch>> {
        return  searchBrandAPI.searchBrand(search).map {
            if (it.isSuccess) {

            }
            return@map it
        }
    }}