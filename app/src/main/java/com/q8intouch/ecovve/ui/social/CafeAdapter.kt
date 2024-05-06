package com.q8intouch.ecovve.ui.social


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveTopCafes
import com.q8intouch.ecovve.util.URLs
import kotlinx.android.synthetic.main.row_top_new_cafe.view.*

class CafeAdapter (
        val items: List<EcovveTopCafes.Data>, val context: Context,
        val controlsListeners: ControlsListeners) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_top_new_cafe_bot, parent, false))
    }
    override fun getItemCount(): Int {
        return items.size
    }
    interface ControlsListeners {
        fun click(postion: Int)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtShopName.setText(items.get(position).name)
//        holder.txtRate.setText(items.get(position).current_orders_number)
        if (items.get(position).logo!!.isNotEmpty())
            if (!items.get(position).logo!!.contains("http"))
        Glide.with(context).load(URLs.IMAGES_URL+items.get(position).logo).into(holder.imageview)
        else
        Glide.with(context).load(items.get(position).logo).into(holder.imageview)
        holder.layout.setOnClickListener {
            controlsListeners.click(position)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val txtShopName = view.txtShopName
    val txtRate = view.txtRate
    val layout = view.materialCardView3
    val imageview = view.imageview
}