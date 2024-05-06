package com.q8intouch.ecovve.ui.social.messages


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveAllPromotion
import com.q8intouch.ecovve.network.model.EcovveUserChatRooms
import kotlinx.android.synthetic.main.row_contact_message.view.*

class MessageAdapter (val items: List<EcovveUserChatRooms.Data>, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_contact_message, parent, false))
    } interface ControlsListeners {
        fun click(postion: Int)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtContactName.text = items[position].name!!
        if (items[position].lastMessage!=null && items[position].lastMessage!!.message!=null)
        holder.txtLastMessage.text = items[position].lastMessage!!.message
        holder.itemView.setOnClickListener {
            itemClickListener.click(position)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to

    val txtContactName = view.txtContactName
    val txtLastMessage = view.txtLastMessage
}