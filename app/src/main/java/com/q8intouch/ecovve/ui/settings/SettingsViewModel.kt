package com.q8intouch.ecovve.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.LogoutResponse
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class SettingsViewModel  @Inject constructor( private val userDataRepo: UserDataRepo
) :  ViewModel() {
    fun logout() : LiveData<Resource<LogoutResponse>> {
        return userDataRepo.logout().map {
            it
        }
    }
}
