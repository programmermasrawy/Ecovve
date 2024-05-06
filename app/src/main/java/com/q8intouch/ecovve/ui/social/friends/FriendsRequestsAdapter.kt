package com.q8intouch.ecovve.ui.social.friends


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.friends.EcovveSentRequests
import kotlinx.android.synthetic.main.row_friend_request.view.*

class FriendsRequestsAdapter (val items: List<EcovveSentRequests.Data>, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolderA>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderA {
        return ViewHolderA(LayoutInflater.from(context).inflate(R.layout.row_friend_request, parent, false))
    } interface ControlsListeners {
        fun add(id: Int)
        fun decline(id: Int)
        fun click(position: Int)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolderA, position: Int) {
        holder.txtContactName.text = items[position].sender.name
        holder.btnAccept.setOnClickListener {
            itemClickListener.add(items[position].id)
        }
        holder.btnDelete.setOnClickListener {
            itemClickListener.decline(items[position].id)
        }
        holder.layout.setOnClickListener {
            itemClickListener.click(position)
        }
    }
}

class ViewHolderA (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
    val txtContactName = view.txtContactName
    val btnAccept = view.btnAccept
    val btnDelete = view.btnDelete
}