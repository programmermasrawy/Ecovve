package com.q8intouch.ecovve.ui.social.chat

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.util.Constants
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.PrivateChannel
import com.pusher.client.channel.PrivateChannelEventListener
import com.pusher.client.util.HttpAuthorizer
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.network.model.MessagesData
import com.q8intouch.ecovve.util.Shared
import kotlinx.android.synthetic.main.fragment_chat.*
import org.jetbrains.anko.onClick
import org.json.JSONArray
import java.util.*


class ChatFragment : BaseFragment<ChatViewModel, com.q8intouch.ecovve.databinding.FragmentChatBinding>() {
    private lateinit var adapter: MessageAdapter

    override fun getLayoutRes(): Int {
        return R.layout.fragment_chat
    }

    companion object {
        fun newInstance() = ChatFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.chatBody.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, true)
        adapter = MessageAdapter(context!!)
        binding.chatBody.adapter = adapter
        var shared = Shared(context!!)
        Constants.USER_ID = shared.getValueInt("id")
        viewModel.showRoomMessages(arguments!!.getString(Constants.ROOM_ID)).observe(this, Observer {
            if (it.isSuccess) {
                it.resource!!.data!!.forEachIndexed { index, data ->
                    adapter.addMessage(data!!,false)
                    adapter.notifyDataSetChanged()
                    // scroll the RecyclerView to the last added element
                    binding.chatBody.scrollToPosition(0);
                    connectTopusher()
                }
            } else {
                Toast.makeText(context!!, "mahmoud" + it.error!!.localizedMessage, Toast.LENGTH_LONG).show()
                connectTopusher()
                Log.e("socketErr", it.error!!.localizedMessage)
            }
        })

        binding.btnChatSend.onClick {
            if (messageBody.text.isNotEmpty()) {
                viewModel.sendMessage(messageBody.text.toString(), "" + Constants.USER_ID
                        , arguments!!.getString(Constants.ROOM_ID)!!).observe(this, Observer {

                })
                messageBody.setText("")
            }
        }

    }

    fun connectTopusher() {
        var shared = Shared(context!!)
        val options: PusherOptions
        val pusher: Pusher
        options = PusherOptions()
        options.setHost("ecovve.com")
        options.isEncrypted = true
        options.setWsPort(6001)
        options.setWssPort(6001)


        val headers = HashMap<String, String>()
        headers["Authorization"] = "Bearer " + shared.getValueString("token")
        headers["Accept"] = "application/json"

        val authorizer = HttpAuthorizer("https://ecovve.com/broadcasting/auth")
        authorizer.setHeaders(headers)
        options.authorizer = authorizer


        pusher = Pusher("ecovve_websocket_key", options)
        pusher.connect()

        var channelaaa = pusher.subscribePrivate("private-chatNewMessage." + arguments!!.getString(Constants.ROOM_ID))
        (channelaaa as PrivateChannel?)!!.bind("newMessage", object : PrivateChannelEventListener {
            override fun onEvent(channelName: String?, eventName: String?, data: String?) {
                var logTask = LogTask(context!!, data!!, adapter, binding.chatBody)
                logTask.execute()
            }

            override fun onAuthenticationFailure(message: String?, e: java.lang.Exception?) {
            }

            override fun onSubscriptionSucceeded(channelName: String?) {
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.chat, menu)
        super.onCreateOptionsMenu(menu!!, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.menuAddToGroup -> {
                val factory = LayoutInflater.from(context!!)
                val addToGroupDialogView = factory.inflate(R.layout.dailog_add_to_group, null)
                val addToGroupDialog = AlertDialog.Builder(context!!).create()
                addToGroupDialog.setView(addToGroupDialogView)
                addToGroupDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                addToGroupDialog.show()
            }
            R.id.menuBlock -> {
                viewModel.blockFriend("1").observe(this, Observer {
                    if (it.isSuccess) {
                        Snackbar.make(
                                view!!,
                                it.resource!!.message!!,
                                Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                                view!!,
                                it.error!!.localizedMessage,
                                Snackbar.LENGTH_LONG
                        ).show()
                    }
                })
            }
            R.id.menuViewProfile -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroy() {
        super.onDestroy()

    }

    // Used for logging on the UI thread
    class LogTask(val context: Context, val data: String, val adapter: MessageAdapter, val chatBody: RecyclerView) : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void?): String {
            return data
        }

        var i = 0
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var gson = Gson()

            var json = JSONArray(result);

            if (i==0) {
                for (i in 0..(json.length() - 1)) {
                    var e = json.getJSONObject(i);
                    var data = MessagesData.Data(e.getInt("chat_room_id"), e.getString("created_at"), e.getInt("id"),
                            e.getString("message"), e.getString("updated_at"), e.getInt("user_id"))
                    adapter.addMessage(data, true)
                    adapter.notifyDataSetChanged()
                    chatBody.requestFocus(DOWN)
                }
                i = 1
            }
        }

    }
}
