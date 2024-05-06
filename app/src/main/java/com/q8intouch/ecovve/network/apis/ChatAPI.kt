package com.q8intouch.ecovve.network.apis

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.util.URLs
import retrofit2.http.*

interface ChatAPI {

    @GET(URLs.SHOW_ROOM)
    fun showChatRoom(@Path("id") userId:String): LiveData<Resource<EcovveShowRoom>>

    @GET(URLs.ALL_CHATROOMS)
    fun showAllChatRooms(): LiveData<Resource<EcovveAllChatRooms>>

    @GET(URLs.USER_CHATROOMS)
    fun showUserChatRooms(): LiveData<Resource<EcovveUserChatRooms>>

    @GET(URLs.USER_PRIVATE_MESSAGES)
    fun showUserMessages(): LiveData<Resource<EcovveUserChatRooms>>


    @GET(URLs.CHATROOM_MESSAGES)
    fun getRoomMessages(@Path("id") room_id:String): LiveData<Resource<MessagesData>>


    @POST(URLs.SEND_MESSAGE)
    @FormUrlEncoded
    fun sendMessage(@Field("message") message:String,
                    @Field("user_id") user_id :String,
                    @Field("chat_room_id") chat_room_id :String
    ): LiveData<Resource<EcovveNewMessageData>>

    @POST(URLs.ADD_CHATROOM)
    @FormUrlEncoded
    fun addChatRoom(@Field("name") name:String,
                  @Field("description") description :String,
                    @FieldMap  user : Map<String,String>
                  ): LiveData<Resource<EcovveAddChatRoom>>

    @POST(URLs.LEAVE_ROOM)
    @FormUrlEncoded
    fun leaveChat(@Field("name") name:String,
                  @Field("description") description :String,
                    @FieldMap  user : Map<String,String>
                  ): LiveData<Resource<EcovveLeaveRoom>>

    @POST(URLs.ADD_CHATROOMUsers)
    @FormUrlEncoded
    fun addUsersChatRoom(@Field("chat_room_id") chat_room_id:String,
                             @FieldMap  user : Map<String,String>
                      ): LiveData<Resource<EcovveChatRoomUsers>>
}