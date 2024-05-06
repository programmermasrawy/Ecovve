package com.q8intouch.ecovve

//import com.twitter.sdk.android.core.DefaultLogger
//import com.twitter.sdk.android.core.Twitter
//import com.twitter.sdk.android.core.TwitterAuthConfig
//import com.twitter.sdk.android.core.TwitterConfig

import android.app.Application
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.ProcessLifecycleOwner
import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.onesignal.OneSignal
import com.parse.Parse
import com.parse.twitter.ParseTwitterUtils
import com.pusher.client.channel.Channel
import com.q8intouch.ecovve.di.component.AppComponent
import com.q8intouch.ecovve.di.component.DaggerAppComponent
import com.q8intouch.ecovve.di.module.AppModule
import com.q8intouch.ecovve.ui.CallActivity
import com.q8intouch.ecovve.util.ApplicationObserver
import com.q8intouch.ecovve.util.Constants
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.onesignal.OneSignalNotification
import com.sinch.android.rtc.Sinch
import com.sinch.android.rtc.SinchClient
import com.sinch.android.rtc.calling.Call
import com.sinch.android.rtc.calling.CallClient
import com.sinch.android.rtc.calling.CallClientListener
import timber.log.Timber


class EcovveApplication : Application() {
    lateinit var appComponent: AppComponent
    lateinit var channelaaa: Channel
    var call: Call? = null
    var sinchClient: SinchClient? = null


    companion object {
        var APP: EcovveApplication? = null

        fun getApp(): EcovveApplication? {
             return APP
         }
    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get()
                .lifecycle
                .addObserver(ApplicationObserver())

        Timber.plant(Timber.DebugTree())
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
        appComponent.inject(this)

        FacebookSdk.sdkInitialize(applicationContext)

        FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)

        Parse.initialize(Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        )
        ParseTwitterUtils.initialize(getString(R.string.com_twitter_sdk_android_CONSUMER_KEY), getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET))
        initOneSignal()
        val android_id = Settings.Secure.getString(this.contentResolver,
                Settings.Secure.ANDROID_ID)
        Log.e("android_id", android_id)
        sinchClient = Sinch.getSinchClientBuilder().context(this)
                .applicationKey(Constants.SNICH_KEY)
                .applicationSecret(Constants.SNICH_SECRET)
                .environmentHost(Constants.SNICH_HOST_NAME)
                .userId(android_id)
                .build()
        sinchClient!!.setSupportCalling(true)
        sinchClient!!.setSupportManagedPush(true)
        sinchClient!!.setSupportPushNotifications(true)
        sinchClient!!.setSupportActiveConnectionInBackground(true)
        sinchClient!!.startListeningOnActiveConnection()
        sinchClient!!.start()
        sinchClient!!.callClient.addCallClientListener(SinchCallClientListener())
    }

    private fun initOneSignal() {
        OneSignal.startInit(this).init()
        val utils = OneSignalNotification(this)

        val sharedPreference: Shared = Shared(applicationContext)
        var id = sharedPreference.getValueInt("id")
        if (id == -1) {
            OneSignal.setExternalUserId(null)
            OneSignal.startInit(this)
                    .autoPromptLocation(true)
                    .setNotificationReceivedHandler(utils.ExtraLifeNotificationReceivedHandler())
                    .setNotificationOpenedHandler(utils.ExampleNotificationOpenedHandler(this))
                    .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                    .init()
        } else {
            OneSignal.setExternalUserId("" + id)
            OneSignal.startInit(this)
                    .autoPromptLocation(true)
                    .setNotificationReceivedHandler(utils.ExtraLifeNotificationReceivedHandler())
                    .setNotificationOpenedHandler(utils.ExampleNotificationOpenedHandler(this))
                    .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                    .init()
        }
    }

    inner class SinchCallClientListener : CallClientListener {
        override fun onIncomingCall(callClient: CallClient, incomingCall: Call) {
            call = incomingCall
            var intent = Intent(applicationContext, CallActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            Constants.CALL = call
            startActivity(intent)
        }

    }

    fun getSinchClientM(): SinchClient? {
        return sinchClient!!
    }
}