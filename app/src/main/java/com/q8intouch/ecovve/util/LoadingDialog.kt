package com.q8intouch.ecovve.util

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.q8intouch.ecovve.R


object LoadingDialog {

    fun showDialog(context: Context): Dialog {
        val dialog = Dialog(context, R.style.TransDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.layout_dialog_loading)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(true)
        dialog.show()
        return dialog
    }
}