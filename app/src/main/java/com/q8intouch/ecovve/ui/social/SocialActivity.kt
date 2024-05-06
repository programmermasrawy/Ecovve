package com.q8intouch.ecovve.ui.social

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.ActivityNavigator
import androidx.navigation.findNavController
import com.q8intouch.ecovve.R
import kotlinx.android.synthetic.main.activity_social.*

class SocialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        val settingsNavController =  findNavController(R.id.nav_settings)
//        settingsNavController.addOnNavigatedListener { _, destination ->
//            toolbarTitle.visibility = View.VISIBLE
//            toolbarTitle.text = destination.label
//            collapseToolbar.isTitleEnabled = false
//        }

    }
}
