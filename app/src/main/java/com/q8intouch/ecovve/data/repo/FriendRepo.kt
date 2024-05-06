package com.q8intouch.ecovve.data.repo

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.R.drawable.star
import com.q8intouch.ecovve.network.apis.FriendsAPI
import com.q8intouch.ecovve.network.apis.SearchBrandAPI
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.network.model.friends.EcovveRecieviedRequests
import com.q8intouch.ecovve.network.model.friends.EcovveSentRequests
import com.q8intouch.ecovve.network.model.friends.EcovveShowUserFriends
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class FriendRepo @Inject constructor(private val friendsAPI: FriendsAPI) {

    fun showFriend(id: String): LiveData<Resource<EcovveShowFriend>> {
        return  friendsAPI.showFriend(id).map {
            it
        }
    }
    fun unFriend(id: String): LiveData<Resource<EcovveDelete>> {
        return  friendsAPI.unFriend(id).map {
            it
        }
    }

    fun userFriends(id: String): LiveData<Resource<EcovveShowUserFriends>> {
        return  friendsAPI.userFriends(id).map {
            it
        }
    }

    fun blockFriend(id: String): LiveData<Resource<EcovveDelete>> {
        return  friendsAPI.blockFriend(id).map {
            it
        }
    }

    fun acceptFriend(id: String): LiveData<Resource<EcovveDelete>> {
        return  friendsAPI.acceptRequest(id).map {
            it
        }
    }

    fun declineFriend(id: String): LiveData<Resource<EcovveDelete>> {
        return  friendsAPI.declineFriend(id).map {
            it
        }
    }

    fun sentFriendRequests(id: String): LiveData<Resource<EcovveSentRequests>> {
        return  friendsAPI.sentRequests(id).map {
            it
        }
    }

    fun receivedFriendRequests(id: String): LiveData<Resource<EcovveRecieviedRequests>> {
        return  friendsAPI.receivedRequests(id).map {
            it
        }
    }

    fun unBlockFriend(id: String): LiveData<Resource<EcovveDelete>> {
        return  friendsAPI.unBlockFriend(id).map {
            it
        }
    }

    fun deleteFriend(id: String): LiveData<Resource<EcovveDelete>> {
        return  friendsAPI.deleteFriendship(id).map {
            it
        }
    }
    fun addFriend(staus: String,sender: String,reciver: String): LiveData<Resource<EcovveAddFriend>> {
        return  friendsAPI.addFriend(
            staus,
            sender_id = sender,
            receiver_id = reciver
        ).map {
            it
        }
    }
    fun searchFriend(search: String,searchBy: String): LiveData<Resource<EcovveSearchFriend>> {
        return  friendsAPI.searchFriend(
            search,searchBy
        ).map {
            it
        }
    }

}