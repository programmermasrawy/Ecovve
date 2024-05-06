package com.q8intouch.ecovve.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.fxn.pix.Pix
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.URLs
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import java.util.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var LanguageEnglish = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setSupportActionBar(toolbar)
        SetUpLang()

        val homeNavController = Navigation.findNavController(this, R.id.nav_home)

        homeNavController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.label) {
                "" -> configureToolbar(destination.label.toString(), R.drawable.img_cafe_background, false)
                //for 4u program
                "                    " -> configureToolbar("", R.drawable.navbar, false)
                resources.getString(R.string.profile) -> configureToolbar(destination.label.toString(), R.drawable.profile_image, false)
                "Live Chat" -> configureToolbar(destination.label.toString(), null, true)
                else -> {
                    configureToolbar(destination.label.toString(), null, false)
                }
            }
            val p = mainScroll.getLayoutParams() as ViewGroup.MarginLayoutParams
            when (destination.label) {
                "Live Chat" -> {
                    p.setMargins(0, 0, 0, 0)
                    mainScroll.requestLayout()
                    toolbar.visibility = View.VISIBLE
                    bottomBar.visibility = View.GONE
                    collapseToolbar.visibility = View.VISIBLE
                }
                "rima" -> {
                    p.setMargins(0, 0, 0, 0)
                    mainScroll.requestLayout()
                    bottomBar.visibility = View.GONE
                    toolbar.visibility = View.VISIBLE
                    collapseToolbar.visibility = View.VISIBLE
                }
                "Find Friends" -> {
                    p.setMargins(0, 0, 0, 0)
                    mainScroll.requestLayout()
                    bottomBar.visibility = View.GONE
                    toolbar.visibility = View.VISIBLE
                    collapseToolbar.visibility = View.VISIBLE
                }
                "Mohammad" -> {
                    p.setMargins(0, 0, 0, 0)
                    mainScroll.requestLayout()
                    toolbar.visibility = View.VISIBLE
                    collapseToolbar.visibility = View.VISIBLE
                    bottomBar.visibility = View.GONE
                }
                resources.getString(R.string.choose_coupon) -> {
                    p.setMargins(0, 0, 0, 0)
                    mainScroll.requestLayout()
                    toolbar.visibility = View.GONE
                    collapseToolbar.visibility = View.GONE
                }
                resources.getString(R.string.buy_gift_card_) -> {
                    p.setMargins(0, 0, 0, 0)
                    mainScroll.requestLayout()
                    toolbar.visibility = View.GONE
                    collapseToolbar.visibility = View.GONE
                }
                else -> {
                    p.setMargins(0, 0, 0, resources!!.getDimension(R.dimen.bottom_bar_height).toInt())
                    mainScroll.requestLayout()
                    bottomBar.visibility = View.VISIBLE
                    toolbar.visibility = View.VISIBLE
                    collapseToolbar.visibility = View.VISIBLE
                }

            }
        }

        mainScroll.scrollTo(0, 0)
        mainScroll.requestFocus()

        NavigationUI.setupWithNavController(collapseToolbar, toolbar, homeNavController, drawer_layout)
        NavigationUI.setupWithNavController(nav_view, homeNavController)

        val typeface = ResourcesCompat.getFont(this, R.font.nunito_bold)
        collapseToolbar.setCollapsedTitleTypeface(typeface)
        collapseToolbar.setExpandedTitleTypeface(typeface)

        btnOpenCart.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_global_cartFragment))
        wishList.setOnClickListener {
            nav_home.findNavController().navigate(R.id.wishListFragment)
        }

        btnOpenCart.setOnClickListener {
            nav_home.findNavController().navigate(R.id.action_global_cartFragment)
        }
        btnChatbot.setOnClickListener {
            val bundle = bundleOf("amount" to intent.extras.getString("amount"))
            nav_home.findNavController().navigate(R.id.action_global_social_activity, bundle)
        }
        // btnProfileSettings.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_global_profileFragment))
        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.getHeaderView(0).findViewById<CircleImageView>(R.id.imgProfilePic).setOnClickListener {
            val bundle = bundleOf("image" to "")
            nav_home.findNavController().navigate(R.id.action_global_profileFragment, bundle)
            drawer_layout.closeDrawers()
        }
        navView.getHeaderView(0).findViewById<Button>(R.id.btnProfileSettings).setOnClickListener {
            nav_home.findNavController().navigate(R.id.action_global_settingsActivity)
            drawer_layout.closeDrawers()
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ) {
          var a =  arrayOf(android.Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_PHONE_STATE)
           ActivityCompat.requestPermissions(this,a,1)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            val sharedPreference: Shared = Shared(baseContext)
            if (sharedPreference.getValueString("searchInfoFragment") != null && sharedPreference.getValueString("searchInfoFragment") != "") {
                var arrayItems = ArrayList<String>()
                val serializedObject = sharedPreference.getValueString("longtideAndLat")
                if (serializedObject != null) {
                    val gson = Gson()
                    val type = object : TypeToken<List<String>>() {
                    }.type
                    arrayItems = gson.fromJson(serializedObject, type)
                }
                val bundle = bundleOf("longtideAndLat" to arrayItems)
                nav_home.findNavController().navigateUp()
//                nav_home.findNavController().navigate(R.id.searchFragment, bundle)
            } else if (sharedPreference.getValueString("goToHome") != null) {
                nav_home.findNavController().navigateUp()
//                nav_home.findNavController().navigate(R.id.homeFragment)
            } else
                super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            val returnValue = data!!.getStringArrayListExtra(Pix.IMAGE_RESULTS)
            val bundle = bundleOf("image" to returnValue[0].toString())
            nav_home.findNavController().navigate(R.id.action_global_profileFragment, bundle)

        } else {

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
//            android.R.id.navNewCafes -> {
//                nav_view.count
//                nav_home.findNavController().navigateUp()
//            }
            R.id.notificationFragment -> {
                nav_home.findNavController().navigate(R.id.notificationFragment)
            }
            R.id.ContactUsFragment -> {
                nav_home.findNavController().navigate(R.id.ContactUsFragment)
            }
            android.R.id.home -> {
                onBackPressed()
            }

//            R.id.nav_gallery -> {
//
//            }
//            R.id.nav_slideshow -> {
//
//            }
//            R.id.nav_manage -> {
//
//            }
//            R.id.nav_share -> {
//
//            }
//            R.id.nav_send -> {
//
//            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

     fun configureToolbar(label: String, imageRes: Int?, altToolbar: Boolean) {
        val collapsibleToolbarImage = collapseToolbar.findViewById<ImageView>(R.id.imgToolbar)
        if (imageRes == null) {
            collapsibleToolbarImage.setImageDrawable(null)
            collapsibleToolbarImage.visibility = View.GONE
            toolbarTitle.visibility = View.VISIBLE
            toolbarTitle.text = label
            collapseToolbar.isTitleEnabled = false
        } else {
            collapsibleToolbarImage.setImageResource(imageRes)
            collapsibleToolbarImage.visibility = View.VISIBLE
            toolbarTitle.visibility = View.GONE
            collapseToolbar.isTitleEnabled = true
            toolbarTitle.text = ""
        }

        if (altToolbar) {
            toolbarTitle.visibility = View.GONE
            imgToolbarProfile.visibility = View.VISIBLE
            searchToolbar.visibility = View.VISIBLE
        } else {
            toolbarTitle.visibility = View.VISIBLE
            imgToolbarProfile.visibility = View.GONE
            searchToolbar.visibility = View.GONE
        }
    }

    var locale: Locale? = null
    private fun Language(lang: String) {
        val country = "US"
        if (lang == "en")
            locale = Locale(lang)
        else
            locale = Locale(lang)

        Locale.setDefault(locale)
        val configs = Configuration()
        configs.locale = locale
        baseContext.resources.updateConfiguration(configs, baseContext.resources.displayMetrics)
    }


    private fun SetUpLang() {
        val sharedPreference: Shared = Shared(this)
        val lang = sharedPreference.getValueString("lang")

        if (lang == null) {
            LanguageEnglish = false
            Language("en")
        } else {
            if (lang.equals("ar")) {
                LanguageEnglish = false
                Language("ar")
                URLs.lang = "ar"
            } else {
                LanguageEnglish = false
                Language("en")
                URLs.lang = "en"
            }
        }
    }
}
