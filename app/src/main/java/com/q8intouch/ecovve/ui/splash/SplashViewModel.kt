package com.q8intouch.ecovve.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.EcovveNetworkInterceptor
import com.q8intouch.ecovve.network.model.EcovveRandomAD
import com.q8intouch.ecovve.util.extension.map
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val userDataRepo: UserDataRepo,private val ecovveNetworkInterceptor: EcovveNetworkInterceptor) : ViewModel() {


    @SuppressLint("CheckResult")
    fun startSplashAndInitializeApp(splashTimeMS: Long, booool: Boolean, token: String): LiveData<Boolean> {
        return MutableLiveData<Boolean>().apply {
            Flowable.timer(splashTimeMS,TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    if (booool == true) {
                        ecovveNetworkInterceptor.accessToken = token
                        value = true
                    }
                    else
                    value = false
                }
        }
    }
    fun randomAD(): LiveData<Resource<EcovveRandomAD>> {
        return  userDataRepo.randomAD().map {
            it
        }
    }

}
