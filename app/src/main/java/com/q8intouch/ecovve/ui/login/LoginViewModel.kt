package com.q8intouch.ecovve.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.EcovveNetworkInterceptor
import com.q8intouch.ecovve.network.apis.AuthAPI
import com.q8intouch.ecovve.network.model.LoginResponse
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userDataRepo: UserDataRepo,
                                         private val authAPI: AuthAPI,private val ecovveNetworkInterceptor: EcovveNetworkInterceptor
) : ViewModel() {
    val email = MutableLiveData<String>().apply { value = "admin@ecovve.com" }
    val password = MutableLiveData<String>().apply { value = "secret" }
    val rememberMe = MutableLiveData<Boolean>().apply { value = false }
    val isLogInLoading = MutableLiveData<Boolean>().apply { value = false }

    init {
      // email.value = userDataRepo.ecovveUser.userName
    }

    fun login(remeber: Boolean, emaildat : String, passworddata: String,mobile_id : String): LiveData<Resource<LoginResponse>> {
        isLogInLoading.value = true
        if (emaildat.toString().contains("@") || emaildat.toString().contains(".")) {
                return userDataRepo.login(emaildat, null, passworddata,mobile_id, false)
                    .map {
                        isLogInLoading.value = false
                        it
                    }
            }
        else {
            return userDataRepo.login(null,emaildat,passworddata,mobile_id,false)
                .map {
                    isLogInLoading.value = false
                    it
                }
        }
    }

    fun login(provider: String, id: String, token: String, context: Context,mobile_id:String, findNavController: NavController): LiveData<Resource<LoginResponse>> {
        Log.e("login","here")
        isLogInLoading.value = true
            return userDataRepo.login(provider,id,token,context,mobile_id,findNavController)
                .map {
                    isLogInLoading.value = false
                    it
                }
    }
}