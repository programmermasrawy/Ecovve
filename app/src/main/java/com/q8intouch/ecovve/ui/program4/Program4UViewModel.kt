package com.q8intouch.ecovve.ui.program4

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveAllRewards
import com.q8intouch.ecovve.network.model.EcovveFaqAll
import com.q8intouch.ecovve.network.model.EcovveUser
import com.q8intouch.ecovve.util.extension.map

class Program4UViewModel @javax.inject.Inject constructor(private val userDataRepo: UserDataRepo
) : ViewModel()  {

    fun allFaqs(page : Int): LiveData<Resource<EcovveFaqAll>> {
        return  userDataRepo.allFaqs(page).map {
            it
        }
    }

    fun getAllRewards(): LiveData<Resource<EcovveAllRewards>> {
        return  userDataRepo.getAllRewards().map {
            it
        }
    }

    fun exchangeReward(userId : String , rewardId:String): LiveData<Resource<EcovveUser>> {
        return  userDataRepo.exchangeReward(userId,rewardId).map {
            it
        }
    }
}
