package com.q8intouch.ecovve.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveNotification
import com.q8intouch.ecovve.network.model.EcovveNotificationSeen
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class NotificationViewModel @Inject constructor(
    private val userDataRepo: UserDataRepo
) : ViewModel() {
    fun allnotifications() : LiveData<Resource<EcovveNotification>> {
        return userDataRepo.allNotifications().map {
            it
        }
    }

    fun seenNotification(seen: String , notificationSeen: String) : LiveData<Resource<EcovveNotificationSeen>> {
        return userDataRepo.seenNotification(seen,notificationSeen).map {
            it
        }
    }

}
