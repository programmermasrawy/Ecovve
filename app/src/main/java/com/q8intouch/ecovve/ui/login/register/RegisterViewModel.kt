package com.q8intouch.ecovve.ui.login.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveSignup
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val userDataRepo: UserDataRepo) : ViewModel() {
    val fullName = MutableLiveData<String>().apply { String() }
    val email = MutableLiveData<String>().apply { String() }
    val phone = MutableLiveData<String>().apply { String() }
    val address = MutableLiveData<String>().apply { String() }
    val password = MutableLiveData<String>().apply { String() }

    fun submitRegisterRequest(
        fullname: String,
        email: String,
        phone: String,
        password: String,
        confirmpass: String
    ): LiveData<Resource<EcovveSignup>> {
      return  userDataRepo.signup(name = fullname,  email = email,password =  password,confirmpass =  confirmpass,phone = phone).map {
            it
        }
    }
//        val registerRequest =
//           // RegisterRequest(fullName.value!!, email.value!!, phone.value!!, address.value!!, password.value!!)
//       // registerRequest.apply {  }
//
//        Timber.w("$registerRequest")
//        registerRequest.email = "CYKA"
//        return MutableLiveData<Unit>().apply { Unit}
//    }
}

