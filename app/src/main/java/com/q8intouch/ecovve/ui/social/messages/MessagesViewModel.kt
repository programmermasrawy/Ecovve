package com.q8intouch.ecovve.ui.social.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.ChatRepo
import com.q8intouch.ecovve.network.model.EcovveUserChatRooms
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class MessagesViewModel  @Inject constructor(
        private val chatRepo: ChatRepo
) : ViewModel() {
    fun getUserMessages(): LiveData<Resource<EcovveUserChatRooms>> {
        return  chatRepo.getUserMessages().map {
            it
        }
    }
}
