package com.q8intouch.ecovve.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.q8intouch.ecovve.data.dao.EcovveUserDao
import com.q8intouch.ecovve.data.model.SavedUserData


@Database(entities = [SavedUserData::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): EcovveUserDao
}