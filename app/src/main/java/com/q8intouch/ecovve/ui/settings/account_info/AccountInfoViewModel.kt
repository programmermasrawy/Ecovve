package com.q8intouch.ecovve.ui.settings.account_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.AccountInfoRepo
import com.q8intouch.ecovve.network.model.AccountInfoResponse
import com.q8intouch.ecovve.ui.Event
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class AccountInfoViewModel @Inject constructor(
    private val accountInfoRepo: AccountInfoRepo
) : ViewModel() {

    var email = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var birthDate = MutableLiveData<String>()
    var isMale = MutableLiveData<Boolean>()

    private var _saveClickEvent = MutableLiveData<Event<Unit>>()

    val saveClickEvent: LiveData<Event<Unit>>
        get() = _saveClickEvent

    fun loadAccountInfo(): LiveData<Resource<AccountInfoResponse>> {
        return accountInfoRepo.getAccountInfo().map { it }
    }

    fun onSaveClicked() {
        _saveClickEvent.value = Event(Unit)
    }
}
