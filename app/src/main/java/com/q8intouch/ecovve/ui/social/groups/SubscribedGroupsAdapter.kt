package com.q8intouch.ecovve.ui.social.groups


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveAllPromotion
import kotlinx.android.synthetic.main.promotion_list_item.view.*

class SubscribedGroupsAdapter (val items: List<EcovveAllPromotion.Data>, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolders>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolders {
        return ViewHolders(LayoutInflater.from(context).inflate(R.layout.row_subscribed_group, parent, false))
    } interface ControlsListeners {
        fun click(postion: Int)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolders, position: Int) {
        holder.layout.setOnClickListener {
            itemClickListener.click(position)
        }
    }
}

class ViewHolders (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
}