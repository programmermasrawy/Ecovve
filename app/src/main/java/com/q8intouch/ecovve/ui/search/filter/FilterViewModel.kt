package com.q8intouch.ecovve.ui.search.filter

import androidx.lifecycle.ViewModel;
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.apis.AuthAPI
import javax.inject.Inject

class FilterViewModel @Inject constructor(private val userDataRepo: UserDataRepo,
                                          private val authAPI: AuthAPI
) : ViewModel() {
}
