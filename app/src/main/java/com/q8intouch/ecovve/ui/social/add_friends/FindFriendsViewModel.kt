package com.q8intouch.ecovve.ui.social.add_friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.data.repo.FriendRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.apis.UserAPI
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.network.model.friends.EcovveRecieviedRequests
import com.q8intouch.ecovve.network.model.friends.EcovveSentRequests
import com.q8intouch.ecovve.network.model.friends.EcovveShowUserFriends
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class FindFriendsViewModel @Inject constructor(
    private val friendRepo: FriendRepo
) : ViewModel() {

    private fun userFriends(id:String) : LiveData<Resource<EcovveShowUserFriends>> {
        return friendRepo.userFriends(id).map { it }
    }

    fun blockFriend(id: String): LiveData<Resource<EcovveDelete>> {
        return  friendRepo.blockFriend(id).map {
            it
        }
    }
    fun searchFriend(search: String,searchBy: String): LiveData<Resource<EcovveSearchFriend>> {
        return  friendRepo.searchFriend(
                search,searchBy
        ).map {
            it
        }
    }
    fun addFriend(staus: String,sender: String,receiver: String): LiveData<Resource<EcovveAddFriend>> {
        return  friendRepo.addFriend(
                staus,
                sender = sender,
                reciver = receiver
        ).map {
            it
        }
    }
    fun unBlockFriend(id: String): LiveData<Resource<EcovveDelete>> {
        return  friendRepo.unBlockFriend(id).map {
            it
        }
    }

    fun acceptFriend(id: String): LiveData<Resource<EcovveDelete>> {
        return  friendRepo.acceptFriend(id).map {
            it
        }
    }

    fun declineFriend(id: String): LiveData<Resource<EcovveDelete>> {
        return  friendRepo.declineFriend(id).map {
            it
        }
    }

    fun sentFriendRequests(id: String): LiveData<Resource<EcovveSentRequests>> {
        return  friendRepo.sentFriendRequests(id).map {
            it
        }
    }

    fun receivedFriendRequests(id: String): LiveData<Resource<EcovveRecieviedRequests>> {
        return  friendRepo.receivedFriendRequests(id).map {
            it
        }
    }
}
