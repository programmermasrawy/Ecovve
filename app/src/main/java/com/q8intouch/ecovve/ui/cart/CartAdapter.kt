package com.q8intouch.ecovve.ui.cart


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.model.ShopCart
import com.q8intouch.ecovve.ui.order.order_details.ItemsAdapter
import kotlinx.android.synthetic.main.shoping_cart_item.view.*

class CartAdapter (
    val items: MutableLiveData<MutableMap<Int, ShopCart>>, val context: Context,
    val controlsListeners: ControlsListeners) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.shoping_cart_item, parent, false))
    }
    override fun getItemCount(): Int {
        return   items.value!!.size
    }
    interface ControlsListeners {
        fun click(postion: CartItem, shpId: Int)
    }
    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var a = items.value!!.get(position)
        var shpId = items.value!!.get(position)!!.id
        holder.name.setText(items.value!!.get(position)!!.name)
//        val menuAdapter = ItemsAdapter(items.value!!.get(position)!!.items,context!!, object :
//            ItemsAdapter.ControlsListeners {
//            override fun click(pos: Int) {
////                controlsListeners.click(1,shpId)
//            }
//        })
//        holder.items.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
//        holder.items.adapter = menuAdapter
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val name = view.shpname
    val items = view.items
}