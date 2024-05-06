package com.q8intouch.ecovve.ui.login.new_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveDelete
import com.q8intouch.ecovve.util.extension.map
import timber.log.Timber
import javax.inject.Inject

class NewPasswordViewModel @Inject constructor(private val userDataRepo: UserDataRepo) : ViewModel() {

    val newPassword = MutableLiveData<String>()
    val reNewPassword = MutableLiveData<String>()

    fun resetPassByPhone(token:String,phone:String,password: String,confirmpass: String): LiveData<Resource<EcovveDelete>> {
        return  userDataRepo.resetPassByPhone(token,phone,password,confirmpass).map {
            it
        }
    }

    fun resetPassByEmail(token:String,phone:String,password: String,confirmpass: String): LiveData<Resource<EcovveDelete>> {
        return  userDataRepo.resetPassByEmail(token,phone,password,confirmpass).map {
            it
        }
    }

    fun updatePassword(): LiveData<Unit> {
        Timber.d(newPassword.value)
        return MutableLiveData<Unit>().apply { value = Unit}
    }
}
