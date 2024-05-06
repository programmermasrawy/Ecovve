package com.q8intouch.ecovve.ui.order.order_details


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveUserOrders
import kotlinx.android.synthetic.main.item_details_order.view.*

class ItemsAdapter (
        val items: List<EcovveUserOrders.Data.DataItems>, val context: Context,
        val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_details_order, parent, false))
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    interface ControlsListeners {
        fun click(postion: Int)
        fun remove(postion: Int)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtItemName.text = ""+ items[position].name
        holder.txtItemTotal.text = ""+ items!![position]!!.price_after_promotion
        holder.txtItemQuantity.text = "1"

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val txtItemName = view.txtItemName
        val txtItemTotal = view.txtItemTotal
        val txtItemQuantity = view.txtItemQuantity

    }
}