package com.q8intouch.ecovve.ui.social.groups

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.ChatRepo
import com.q8intouch.ecovve.data.repo.FriendRepo
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class GroupsViewModel @Inject constructor(
    private val chatRepo: ChatRepo
) : ViewModel() {
    public fun showAllRooms(id:String) : LiveData<Resource<EcovveAllChatRooms>> {
        return chatRepo.showAllRooms().map { it }
    }

    fun showUserRooms(): LiveData<Resource<EcovveUserChatRooms>> {
        return  chatRepo.showUserRooms().map {
            it
        }
    }

    fun addChatRoom(staus: String,sender: String,users : Map<String,String>): LiveData<Resource<EcovveAddChatRoom>> {
        return  chatRepo.addChatRoom(
                staus,
                sender,
                users
        ).map {
            it
        }
    }

    fun leaveChatRoom(staus: String,sender: String,users : Map<String,String>): LiveData<Resource<EcovveLeaveRoom>> {
        return  chatRepo.leaveChatRoom(
                staus,
                sender,
                users
        ).map {
            it
        }
    }

    fun addRoomUsers(id: String,users : Map<String,String>): LiveData<Resource<EcovveChatRoomUsers>> {
        return  chatRepo.addRoomUsers(
                id,users
        ).map {
            it
        }
    }
}
