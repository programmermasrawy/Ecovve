package com.q8intouch.ecovve.ui.social.friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.FriendRepo
import com.q8intouch.ecovve.network.model.EcovveDelete
import com.q8intouch.ecovve.network.model.EcovveShowFriend
import com.q8intouch.ecovve.network.model.friends.EcovveRecieviedRequests
import com.q8intouch.ecovve.network.model.friends.EcovveSentRequests
import com.q8intouch.ecovve.network.model.friends.EcovveShowUserFriends
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class FriendsViewModel @Inject constructor(
    private val friendRepo: FriendRepo
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
