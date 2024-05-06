package com.q8intouch.ecovve.util

import android.content.Context
import android.view.MenuItem
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuItemImpl
import com.q8intouch.ecovve.R
import java.util.function.Consumer

//Work in progress
class EcovvePopup(overFlowMenuItem: MenuItem,val menuRes:Int,val onMenuItemClick : (itemRes:Int) -> Unit) {
    val parentView: View = overFlowMenuItem.actionView
    val context:Context = parentView.context


    init {
        inflateMenu ()
    }

    private fun inflateMenu() {
        val menu = MenuBuilder(context)
        MenuInflater(context).inflate(menuRes, menu)
        menu.actionItems.forEach {menuItemImpl: MenuItemImpl? ->

        }
    }
}