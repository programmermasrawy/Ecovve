package com.q8intouch.ecovve.ui.social.add_friends


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveSearchFriend
import kotlinx.android.synthetic.main.row_friend_request.view.*

class FindFriendsAdapter (val items: List<EcovveSearchFriend.Result>, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_friend_request, parent, false))
    } interface ControlsListeners {
        fun add(id: Int, btnAccept: MaterialButton)
        fun decline(id: Int)
        fun click(position: Int)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtContactName.text = items[position].name
        holder.btnAccept.setOnClickListener {
            itemClickListener.add(items[position].id,holder.btnAccept)
        }
        holder.btnDelete.visibility = View.GONE
        holder.btnAccept.text = context.resources.getString(R.string.send_request)
        holder.btnDelete.setOnClickListener {
            itemClickListener.decline(items[position].id)
        }
        holder.layout.setOnClickListener {
            itemClickListener.click(position)
        }
    }
}


class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
    val txtContactName = view.txtContactName
    val btnAccept = view.btnAccept
    val btnDelete = view.btnDelete
}
