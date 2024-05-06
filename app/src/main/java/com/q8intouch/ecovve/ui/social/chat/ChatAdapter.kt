package com.q8intouch.ecovve.ui.social.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import android.view.View
import com.fxn.utility.Constants
import com.q8intouch.ecovve.network.model.MessagesData
import kotlinx.android.synthetic.main.row_chat_me.view.*

private const val VIEW_TYPE_MY_MESSAGE = 1
private const val VIEW_TYPE_OTHER_MESSAGE = 2

class MessageAdapter (val context: Context) : RecyclerView.Adapter<MessageViewHolder>() {
    private val messages: ArrayList<MessagesData.Data> = ArrayList()

    fun addMessage(message: MessagesData.Data, b: Boolean){
        if (b)
        messages.add(0,message)
        else
        messages.add(messages.size,message)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages.get(position)

        return if(com.q8intouch.ecovve.util.Constants.USER_ID == message.user_id) {
            VIEW_TYPE_MY_MESSAGE
        }
        else {
            VIEW_TYPE_OTHER_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if(viewType == VIEW_TYPE_MY_MESSAGE) {
            MyMessageViewHolder(LayoutInflater.from(context).inflate(R.layout.row_chat_me, parent, false))
        } else {
            OtherMessageViewHolder(LayoutInflater.from(context).inflate(R.layout.row_chat_them, parent, false))
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages.get(position)

        holder?.bind(message)
    }

    inner class MyMessageViewHolder (view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.message
//        private var timeText: TextView = view.txtMyMessageTime

        override fun bind(message: MessagesData.Data) {
            messageText.text = message.message
//            timeText.text = DateUtils.fromMillisToTimeString(message.time)
        }
    }

    inner class OtherMessageViewHolder (view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.message
//        private var userText: TextView = view.txtOtherUser
//        private var timeText: TextView = view.txtOtherMessageTime

        override fun bind(message: MessagesData.Data) {
            messageText.text = message.message
//            userText.text =""+ message.user_id
//            timeText.text = DateUtils.fromMillisToTimeString(message.time)
        }
    }
}

open class MessageViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(message:MessagesData.Data) {}
}