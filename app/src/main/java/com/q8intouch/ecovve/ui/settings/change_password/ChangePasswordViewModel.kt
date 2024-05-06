package com.q8intouch.ecovve.ui.settings.change_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveDelete
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(private val userDataRepo: UserDataRepo) :  ViewModel() {
    fun changePass(old:String?, newpass:String?, conPass:String): LiveData<Resource<EcovveDelete>> {
        return  userDataRepo.changePass(old ,newpass,conPass).map {
            it
        }
    }
}
