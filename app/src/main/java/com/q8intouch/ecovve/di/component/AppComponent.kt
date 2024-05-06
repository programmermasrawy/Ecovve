package com.q8intouch.ecovve.di.component

import android.content.Context
import android.view.View
import com.q8intouch.ecovve.EcovveApplication
import com.q8intouch.ecovve.base.BaseViewModelFactory
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.di.module.AppModule
import com.q8intouch.ecovve.di.module.NetworkModule

import com.q8intouch.ecovve.di.module.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [ ViewModelModule::class,AppModule::class , NetworkModule::class ])
interface AppComponent {
    fun inject(ecovveApplication: EcovveApplication)

    fun baseViewModelFactory(): BaseViewModelFactory

    fun cartRepo():CartRepo

}