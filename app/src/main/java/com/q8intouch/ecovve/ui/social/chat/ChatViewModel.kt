package com.q8intouch.ecovve.ui.social.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.ChatRepo
import com.q8intouch.ecovve.data.repo.FriendRepo
import com.q8intouch.ecovve.network.model.EcovveDelete
import com.q8intouch.ecovve.network.model.EcovveNewMessageData
import com.q8intouch.ecovve.network.model.MessagesData
import com.q8intouch.ecovve.network.model.friends.EcovveShowUserFriends
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class ChatViewModel  @Inject constructor(
        private val friendRepo: FriendRepo,
        private val chatRepo: ChatRepo
) : ViewModel() {

    fun userFriends(id:String) : LiveData<Resource<EcovveShowUserFriends>> {
        return friendRepo.userFriends(id).map { it }
    }

    fun blockFriend(id: String): LiveData<Resource<EcovveDelete>> {
        return  friendRepo.blockFriend(id).map {
            it
        }
    }

    fun unBlockFriend(id: String): LiveData<Resource<EcovveDelete>> {
        return  friendRepo.unBlockFriend(id).map {
            it
        }
    }
    fun sendMessage(message:String,user:String,room: String): LiveData<Resource<EcovveNewMessageData>> {
        return  chatRepo.sendMessage(message,user,room).map {
            it
        }
    }
    fun showRoomMessages(room: String?): LiveData<Resource<MessagesData>> {
        return  chatRepo.showRoomMessages(room).map {
            it
        }
    }
}
