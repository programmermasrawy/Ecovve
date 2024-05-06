//package com.q8intouch.ecovve.ui.cart
//
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.lifecycle.MutableLiveData
//import androidx.recyclerview.widget.RecyclerView
//import com.q8intouch.ecovve.R
//import com.q8intouch.ecovve.R.id.name
//import com.q8intouch.ecovve.data.model.CartItem
//import kotlinx.android.synthetic.main.shoping_cart_detail_item.view.*
//
//class ItemsAdapter (
//    val items: MutableLiveData<MutableMap<CartItem, Int>>, val context: Context,
//    val controlsListeners: ControlsListeners) : RecyclerView.Adapter<ViewHolderitem>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderitem {
//        return ViewHolderitem(LayoutInflater.from(context).inflate(R.layout.shoping_cart_detail_item, parent, false))
//    }
//    override fun getItemCount(): Int {
//        return   items.value!!.size
//    }
//    interface ControlsListeners {
//        fun click(postion: Int)
//    }
//    override fun onBindViewHolder(holder: ViewHolderitem, position: Int) {
//        holder.name.setText(items.value!!.[0].name)
//        holder.price.setText(""+items.get(position).price)
//        holder.qunitity.setText("1")
//        holder.additem.setOnClickListener {
//            controlsListeners.click(position)
//        }
//
//    }
//}
//
//class ViewHolderitem (view: View) : RecyclerView.ViewHolder(view) {
//    // Holds the TextView that will add each animal to
//    val name = view.name
//    val price = view.price
//    val qunitity = view.qunitity
//    val additem = view.additem
//
//}