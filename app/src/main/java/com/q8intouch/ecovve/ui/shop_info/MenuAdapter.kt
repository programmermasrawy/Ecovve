package com.q8intouch.ecovve.ui.shop_info

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveCafeInfoBrand
import kotlinx.android.synthetic.main.row_menu_cafe.view.*

class MenuAdapter (
        val items: List<EcovveCafeInfoBrand.Data.Menus.DataMenus>, val context: Context,
        val controlsListeners: ControlsListeners) : RecyclerView.Adapter<ViewHolderitem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderitem {
        return ViewHolderitem(LayoutInflater.from(context).inflate(R.layout.row_menu_cafe, parent, false))
    }
    override fun getItemCount(): Int {
        return items.size
    }
    interface ControlsListeners {
        fun click(postion: Int)
    }
    override fun onBindViewHolder(holder: ViewHolderitem, position: Int) {
        holder.txtShopName.setText(items.get(position).name)
        holder.layout.setOnClickListener {
            controlsListeners.click(position)
        }

    }
}

class ViewHolderitem (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val txtShopName = view.txtMenuItem
    val layout = view.layout

}