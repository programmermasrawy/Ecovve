package com.q8intouch.ecovve.ui.top_new_shops


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveNewBrand
import kotlinx.android.synthetic.main.area_row.view.*
import org.jetbrains.anko.onClick

class BrandOutletsLatestAdapter (
        val items: ArrayList<EcovveNewBrand.Data.Outlets?>, val context: Context,
        val controlsListeners: ControlsListeners) : RecyclerView.Adapter<ViewHolderOutl>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderOutl {
        return ViewHolderOutl(LayoutInflater.from(context).inflate(R.layout.area_row, parent, false))
    }
    override fun getItemCount(): Int {
        return items.size
    }
    interface ControlsListeners {
        fun click(postion: Int)
    }
    override fun onBindViewHolder(holder: ViewHolderOutl, position: Int) {
        holder.area_data.text = items[position]!!.name
        holder.layout.onClick {
            controlsListeners.click(position)
        }
    }
}

class ViewHolderOutl (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
    val area_data = view.area_data
}