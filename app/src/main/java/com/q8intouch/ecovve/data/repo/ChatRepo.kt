package com.q8intouch.ecovve.data.repo

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.apis.ChatAPI
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class ChatRepo @Inject constructor(private val chatAPI: ChatAPI) {

    fun showRoom(id: String): LiveData<Resource<EcovveShowRoom>> {
        return  chatAPI.showChatRoom(id).map {
            it
        }
    }

    fun showAllRooms(): LiveData<Resource<EcovveAllChatRooms>> {
        return  chatAPI.showAllChatRooms().map {
            it
        }
    }
    fun showUserRooms(): LiveData<Resource<EcovveUserChatRooms>> {
        return  chatAPI.showUserChatRooms().map {
            it
        }
    }

    fun getUserMessages(): LiveData<Resource<EcovveUserChatRooms>> {
        return  chatAPI.showUserMessages().map {
            it
        }
    }

    fun showRoomMessages(room: String?): LiveData<Resource<MessagesData>> {
        return  chatAPI.getRoomMessages(room!!).map {
            it
        }
    }


    fun sendMessage(message:String,user:String,room: String): LiveData<Resource<EcovveNewMessageData>> {
        return  chatAPI.sendMessage(message,user,room).map {
            it
        }
    }

    fun addChatRoom(staus: String,sender: String,users : Map<String,String>): LiveData<Resource<EcovveAddChatRoom>> {
        return  chatAPI.addChatRoom(
            staus,
           sender,
           users
        ).map {
            it
        }
    }

    fun leaveChatRoom(staus: String,sender: String,users : Map<String,String>): LiveData<Resource<EcovveLeaveRoom>> {
        return  chatAPI.leaveChat(
                staus,
                sender,
                users
        ).map {
            it
        }
    }

    fun addRoomUsers(id: String,users : Map<String,String>): LiveData<Resource<EcovveChatRoomUsers>> {
        return  chatAPI.addUsersChatRoom(
            id,users
        ).map {
            it
        }
    }

}