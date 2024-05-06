package com.q8intouch.ecovve.ui.top_new_shops

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveALLOUTLETS
import com.q8intouch.ecovve.network.model.EcovveNewBrand
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class NavNewCafesViewModel @Inject constructor(
    private val userDataRepo: UserDataRepo
) : ViewModel() {

    fun allOutlets(page: Int): LiveData<Resource<EcovveALLOUTLETS>> {
        return userDataRepo.allOutlets(page).map {
            it
        }
    }

    fun latestCafes(page: Int): LiveData<Resource<EcovveNewBrand>> {
        return userDataRepo.latestCafes(page).map {
            it
        }
    }
}