package com.q8intouch.ecovve.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.q8intouch.ecovve.data.model.SavedUserData


@Dao
interface EcovveUserDao {
    @get:Query("select * from SavedUserData where userId = 0")
    val get: LiveData<SavedUserData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: SavedUserData)
}