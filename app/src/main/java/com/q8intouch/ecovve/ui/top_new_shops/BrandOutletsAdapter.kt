package com.q8intouch.ecovve.ui.top_new_shops


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveTopCafes
import kotlinx.android.synthetic.main.area_row.view.*
import org.jetbrains.anko.onClick

class BrandOutletsAdapter (
        val items: ArrayList<EcovveTopCafes.Data.Outlets?>, val context: Context,
        val controlsListeners: ControlsListeners) : RecyclerView.Adapter<ViewHolderOutlets>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderOutlets {
        return ViewHolderOutlets(LayoutInflater.from(context).inflate(R.layout.area_row, parent, false))
    }
    override fun getItemCount(): Int {
        return items.size
    }
    interface ControlsListeners {
        fun click(postion: Int)
    }
    override fun onBindViewHolder(holder: ViewHolderOutlets, position: Int) {
        holder.area_data.text = items[position]!!.name
        holder.layout.onClick {
            controlsListeners.click(position)
        }
    }
}

class ViewHolderOutlets (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
    val area_data = view.area_data
}