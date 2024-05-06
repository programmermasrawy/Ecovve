package com.q8intouch.ecovve.network.apis

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.network.model.friends.EcovveRecieviedRequests
import com.q8intouch.ecovve.network.model.friends.EcovveSentRequests
import com.q8intouch.ecovve.network.model.friends.EcovveShowUserFriends
import com.q8intouch.ecovve.util.URLs
import retrofit2.http.*

interface FriendsAPI {

    @GET(URLs.SHOW_FIEND)
    fun showFriend(@Path("id") userId:String): LiveData<Resource<EcovveShowFriend>>

    @GET(URLs.USER_FRIENDS)
    fun userFriends(@Path("id") userId:String): LiveData<Resource<EcovveShowUserFriends>>


    @GET(URLs.BLOCK_FRIEND)
    fun blockFriend(@Path("id") userId:String): LiveData<Resource<EcovveDelete>>

    @GET(URLs.ACCEPT_FRIEND)
    fun acceptRequest(@Path("id") userId:String): LiveData<Resource<EcovveDelete>>

    @GET(URLs.DECLINE_FRIEND)
    fun declineFriend(@Path("id") userId:String): LiveData<Resource<EcovveDelete>>

     @GET(URLs.SENT_FRIEND_REQUEST)
     fun sentRequests(@Path("id") userId:String): LiveData<Resource<EcovveSentRequests>>

    @GET(URLs.RECEIVED_FRIEND_REQUESTS)
    fun receivedRequests(@Path("id") userId:String): LiveData<Resource<EcovveRecieviedRequests>>

    @GET(URLs.UNBLOCK_FRIEND)
    fun unBlockFriend(@Path("id") userId:String): LiveData<Resource<EcovveDelete>>

    @GET(URLs.DELETE_FRIENDSHIP)
    fun deleteFriendship(@Path("id") userId:String): LiveData<Resource<EcovveDelete>>

     @GET(URLs.DELETE_FRIEND)
    fun unFriend(@Path("id") userId:String): LiveData<Resource<EcovveDelete>>


    @POST(URLs.ADD_FRIEND)
    @FormUrlEncoded
    fun addFriend(@Field("status") status:String,
                  @Field("sender_id") sender_id:String,
                  @Field("receiver_id") receiver_id:String
                  ): LiveData<Resource<EcovveAddFriend>>

    @POST(URLs.ECOVVE_SEARCH_FRIEND)
    @FormUrlEncoded
    fun searchFriend(@Field("search") status:String,
                  @Field("search_by") sender_id:String
    ): LiveData<Resource<EcovveSearchFriend>>


}