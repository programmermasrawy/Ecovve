package com.q8intouch.ecovve.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.q8intouch.ecovve.R
import kotlinx.android.synthetic.main.activity_filter.*
import com.q8intouch.ecovve.ui.search.filter.FilterFragment
import org.jetbrains.anko.onClick

class FilterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        back.onClick {
            onBackPressed()
            finish()
        }

            var roomsFragment =  FilterFragment();
            supportFragmentManager.beginTransaction().replace(R.id.container,
                    roomsFragment, FilterFragment::class.java.simpleName).addToBackStack("").commit();

    }

    override fun onBackPressed() {
        super.onBackPressed()
        onBackPressed()
        finish()
    }
}
