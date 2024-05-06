package com.q8intouch.ecovve.util.extension

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun fillRV (rv:RecyclerView,@LayoutRes res:Int,rowsCount:Int,coulmns:Int?){
    var  layoutManager:RecyclerView.LayoutManager? = null

    if (coulmns == null){
        layoutManager = LinearLayoutManager(rv.context)
    }else {
        layoutManager = GridLayoutManager(rv.context,coulmns)
    }
    val adapter = PlaceholderAdapter(res,rowsCount,null)
    rv.layoutManager =layoutManager
    rv.adapter = adapter

}

fun fillRvWithAction(rv:RecyclerView,@LayoutRes res:Int,rowsCount:Int,coulmns:Int?,action:Int){
    var  layoutManager:RecyclerView.LayoutManager? = null

    if (coulmns == null){
        layoutManager = LinearLayoutManager(rv.context)
    }else {
        layoutManager = GridLayoutManager(rv.context,coulmns)
    }
    val adapter = PlaceholderAdapter(res,rowsCount,action)
    rv.layoutManager =layoutManager
    rv.adapter = adapter
}