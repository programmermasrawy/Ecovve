package com.q8intouch.ecovve.ui.social.friends


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.User
import com.q8intouch.ecovve.network.model.friends.EcovveShowUserFriends
import kotlinx.android.synthetic.main.row_your_friend.view.*

class FriendsAdapter (val items: List<User>, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_your_friend, parent, false))
    } interface ControlsListeners {
        fun click(postion: Int)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtContactName.text = items[position].name
        holder.lastActive.text = items[position].lastActive
        holder.layout.setOnClickListener {
            itemClickListener.click(position)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
    val txtContactName = view.txtContactName
    val lastActive = view.lastActive
}