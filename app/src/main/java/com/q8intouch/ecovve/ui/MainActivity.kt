package com.q8intouch.ecovve.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.URLs
//import com.twitter.sdk.android.core.DefaultLogger
//import com.twitter.sdk.android.core.Twitter
//import com.twitter.sdk.android.core.TwitterAuthConfig
//import com.twitter.sdk.android.core.TwitterConfig
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


class MainActivity : AppCompatActivity() {
     var LanguageEnglish = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            val info = packageManager.getPackageInfo(
                packageName, //Or replace to your package name directly, instead getPackageName()  "com.your.app"
                PackageManager.GET_SIGNATURES
            )

            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())

                Log.e("KeyHash:", android.util.Base64.encodeToString(md.digest(), android.util.Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        SetUpLang()
    }

    override fun onBackPressed() {

        val navHostFragment = supportFragmentManager.fragments[0]

        val fragmentList = navHostFragment.childFragmentManager.fragments

        var handled: Boolean
        for (f in fragmentList) {
            val baseFragment = f as BaseFragment<*,*>
            handled = baseFragment.onBackPressed()
            if (handled) {
                return
            }
        }
        super.onBackPressed()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment = supportFragmentManager
        if (fragment != null) {
            fragment.findFragmentById(R.id.loginFragment)?.onActivityResult(requestCode, resultCode, data)
        } else
            fragment!!.findFragmentById(R.id.loginFragment)?.onActivityResult(requestCode, resultCode, data)
    }
    var locale: Locale? = null
    private fun Language(lang: String) {
        val country = "US"
        if (lang == "en") {
            locale = Locale(lang)
        }else {
            locale = Locale(lang)
        }
        Locale.setDefault(locale)
        val configs = Configuration()
        configs.locale = locale
        baseContext.resources.updateConfiguration(configs, baseContext.resources.displayMetrics)
    }


    private fun SetUpLang() {
        val sharedPreference: Shared = Shared(this)
//        val sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE)

//        val editor = sharedPreferences.edit()
//        editor.putString("lang", "")
//        editor.apply()

        val lang =  sharedPreference.getValueString("lang")

        if (lang==null){
            LanguageEnglish = false
            Language("en")
        }
        else {
            if (lang.equals("ar")) {
                LanguageEnglish = false
                Language("ar")
                URLs.lang = "ar"
            }
            else {
                LanguageEnglish = false
                Language("en")
                URLs.lang = "en"
            }
        }
    }
}
