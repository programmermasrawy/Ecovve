package com.q8intouch.ecovve.ui.social.groups


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveAllPromotion
import kotlinx.android.synthetic.main.promotion_list_item.view.*

class RecommendedGroupsAdapter (val items: List<EcovveAllPromotion.Data>, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolderm>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderm {
        return ViewHolderm(LayoutInflater.from(context).inflate(R.layout.row_recommended_groups, parent, false))
    } interface ControlsListeners {
        fun click(postion: Int)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolderm, position: Int) {
        holder.layout.setOnClickListener {
            itemClickListener.click(position)
        }
    }
}

class ViewHolderm (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
}