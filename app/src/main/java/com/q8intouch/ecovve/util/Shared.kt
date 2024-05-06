package com.q8intouch.ecovve.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.q8intouch.ecovve.network.model.City
import com.google.gson.Gson



/**
 * Created by Kolincodes on 10/05/2018.
 */

class Shared(val context: Context) {
    private val PREFS_NAME = "kotlincodes"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    fun save(KEY_NAME: String, text: String) {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(KEY_NAME, text)

        editor!!.commit()
    }

    fun save(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.apply {

        }
        editor.putInt(KEY_NAME, value)
        editor.apply()
        editor.commit()
    }


    fun <T> setList(key: String, list: List<T>) {
        val gson = Gson()
        val json = gson.toJson(list)

        set(key, json)
    }

    operator fun set(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun save(KEY_NAME: String, status: Boolean) {

        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_NAME, status!!)

        editor.commit()
    }

    fun getValueString(KEY_NAME: String): String? {

        return sharedPref.getString(KEY_NAME, null)


    }

    fun getValueInt(KEY_NAME: String): Int {

        return sharedPref.getInt(KEY_NAME, -1)
    }

    fun getValueBoolien(KEY_NAME: String, defaultValue: Boolean): Boolean {

        return sharedPref.getBoolean(KEY_NAME, defaultValue)

    }

    fun clearSharedPreference() {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        //sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        editor.clear()
        editor.commit()
    }

    fun removeValue(KEY_NAME: String) {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.remove(KEY_NAME)
        editor.commit()
    }
}