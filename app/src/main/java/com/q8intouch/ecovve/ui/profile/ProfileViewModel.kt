package com.q8intouch.ecovve.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveUserPhoto
import com.q8intouch.ecovve.util.extension.map
import okhttp3.MultipartBody

class ProfileViewModel @javax.inject.Inject constructor(private val userDataRepo: UserDataRepo
) : ViewModel()  {

    fun changeImage(user:String, photo: MultipartBody.Part): LiveData<Resource<EcovveUserPhoto>> {
        return  userDataRepo.changeImage(user,photo).map {
            it
        }
    }

}
