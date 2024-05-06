package com.q8intouch.ecovve.ui.top_new_shops

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveALLOUTLETS
import com.q8intouch.ecovve.network.model.EcovveTopCafes
import com.q8intouch.ecovve.util.extension.map

import javax.inject.Inject

class TopNewShopsViewModel @Inject constructor(
        private val userDataRepo: UserDataRepo
) : ViewModel() {


    fun topCafes(page: Int): LiveData<Resource<EcovveTopCafes>> {
        return userDataRepo.topCafes(page).map {
            it
        }
    }

    fun allOutlets(page: Int): LiveData<Resource<EcovveALLOUTLETS>> {
        return userDataRepo.allOutlets(page).map {
            it
        }
    }
}
