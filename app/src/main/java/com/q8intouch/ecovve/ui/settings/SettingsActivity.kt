package com.q8intouch.ecovve.ui.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.q8intouch.ecovve.R
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import org.jetbrains.anko.onClick

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setSupportActionBar(toolbar)
       supportActionBar?.setDisplayHomeAsUpEnabled(true)
        NavigationUI.setupWithNavController(collapseToolbar,toolbar,findNavController(R.id.nav_settings))
        val settingsNavController =  findNavController(R.id.nav_settings)
        settingsNavController.addOnDestinationChangedListener { controller, destination, arguments ->
            toolbarTitle.visibility = View.VISIBLE
            toolbarTitle.text = destination.label
            collapseToolbar.isTitleEnabled = false
            collapseToolbar.title = " "
            toolbar.title = " "
            }

    }
}
