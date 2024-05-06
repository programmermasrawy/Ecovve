package com.q8intouch.ecovve.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class SavedUserData(@field:PrimaryKey val id:Int?,
                         val userId:Int,
                         val email: String?,
                         val phone: String?,
                         val password: String?,
                         val accessToken: String?,
                         val tokenExpirationDate:String?
                         )