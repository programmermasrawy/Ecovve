package com.q8intouch.ecovve.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.EcovveAllStatus
import com.q8intouch.ecovve.network.model.EcovveCancelOrder
import com.q8intouch.ecovve.network.model.EcovveUserOrders
import com.q8intouch.ecovve.network.model.EcovveUserPrevOrders
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class MyOrdersViewModel @Inject constructor(
    private val userDataRepo: UserDataRepo
) : ViewModel() {

    fun prevOrders(): LiveData<Resource<EcovveUserPrevOrders>> {
        return userDataRepo.prevOrders("9").map {
            it
        }
    }

    fun cancelOrder(id:String): LiveData<Resource<EcovveCancelOrder>> {
        return  userDataRepo.cancelOrder(id).map {
            it
        }
    }

    fun userOrders(id: String,delivery__:String): LiveData<Resource<EcovveUserOrders>> {
        return userDataRepo.userOrders(id,delivery__).map {
            it
        }
    }

    fun onGonigOrders(id: String): LiveData<Resource<EcovveUserOrders>> {
        return userDataRepo.onGoingOrders(id).map {
            it
        }
    }

    fun allStatus(): LiveData<Resource<EcovveAllStatus>> {
        return userDataRepo.allStatus().map {
            it
        }
    }

}