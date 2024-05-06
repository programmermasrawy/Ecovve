package com.q8intouch.ecovve.util.extension

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import java.lang.RuntimeException

fun ViewDataBinding.bindViewModel(viewModel: ViewModel){
    try {
        this::class.java.getDeclaredMethod("setViewModel", viewModel::class.java)
            .invoke(this, viewModel)
    } catch (e: Throwable) {
        throw RuntimeException("You must set 'viewModel' variable inside the xml")
    }
}