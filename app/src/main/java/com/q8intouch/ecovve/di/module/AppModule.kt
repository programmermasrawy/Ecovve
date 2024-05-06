package com.q8intouch.ecovve.di.module

import android.content.Context
import androidx.room.Room
import com.q8intouch.ecovve.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable



@Module
class AppModule (val context:Context) {

    @Provides
    fun provideContext():Context = context

    @Reusable
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context,AppDatabase::class.java,"ecovve-db").build()
    }



//    @Provides
//    fun providesSplashViewModel():SplashViewModel = SplashViewModel()
//    @Provides
//    fun providesSHomeViewModel():HomeViewModel = HomeViewModel()
//    @Provides
//    fun providesLoginViewModel():LoginViewModel = LoginViewModel()
}