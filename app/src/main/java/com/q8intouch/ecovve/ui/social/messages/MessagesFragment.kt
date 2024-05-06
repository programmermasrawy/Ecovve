package com.q8intouch.ecovve.ui.social.messages

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.util.Constants
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.Channel
import com.pusher.client.channel.PrivateChannel
import com.pusher.client.channel.PrivateChannelEventListener
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange
import com.pusher.client.util.HttpAuthorizer
import com.q8intouch.ecovve.EcovveApplication

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.extension.fillRV
import kotlinx.android.synthetic.main.fragment_messages.*
import java.util.HashMap
import com.pusher.client.channel.SubscriptionEventListener
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentMessagesBinding
import com.q8intouch.ecovve.ui.social.groups.UserGroupsAdapter
import com.q8intouch.ecovve.util.LoadingDialog
import kotlinx.android.synthetic.main.fragment_groups.*
import org.jetbrains.anko.bundleOf


class MessagesFragment : BaseFragment<MessagesViewModel,FragmentMessagesBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_messages
    }

    companion object {
        fun newInstance() = MessagesFragment()
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sharedPreference: Shared = Shared(context!!)
        val dialog = LoadingDialog.showDialog(view!!.context)
        viewModel.getUserMessages().observe(this, Observer {
            dialog.dismiss()
            if (it.isSuccess){
                sharedPreference.setList(Constants.PROMPT_CHAT_ROOMS, it.resource!!.data)
                val friendsAdapter = MessageAdapter(it.resource!!.data, context!!, object :
                        MessageAdapter.ControlsListeners {
                    override fun click(postion: Int) {
                        val bundle = bundleOf( Constants.ROOM_ID to ""+ it.resource!!.data[postion].id)
                        findNavController().navigate(R.id.action_global_chatFragment,bundle)
                    }
                })
                rvMessages.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                rvMessages.adapter = friendsAdapter
            }
            else {
                Log.e("messagesFragment",it.error!!.localizedMessage)
            }
        })

    }

}
