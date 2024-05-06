package com.q8intouch.ecovve.ui.social.friends

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.q8intouch.ecovve.util.Constants
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentFriendsBinding
import com.q8intouch.ecovve.network.model.EcovveUser
import com.q8intouch.ecovve.network.model.EcovveUserChatRooms
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.Shared
import kotlinx.android.synthetic.main.fragment_friends.*
import org.jetbrains.anko.bundleOf

class FriendsFragment : BaseFragment<FriendsViewModel,FragmentFriendsBinding>() {
    var chatRoomsList = ArrayList<EcovveUserChatRooms.Data>()

    override fun getLayoutRes(): Int {
       return R.layout.fragment_friends
    }
    companion object {
        fun newInstance() = FriendsFragment()
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id = activity!!.intent.extras.getString("amount")
        val sharedPreference: Shared = Shared(context!!)
        val serializedObject = sharedPreference.getValueString(Constants.PROMPT_CHAT_ROOMS)
        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<EcovveUserChatRooms.Data>>() {
            }.type
            if (type!=null)
            chatRoomsList = gson.fromJson(serializedObject, type)
        }
        val dialog = LoadingDialog.showDialog(view!!.context)
        viewModel.userFriends(id).observe(this, Observer {
            dialog.dismiss()
            if (it.isSuccess){
                val friendsAdapter = FriendsAdapter(it.resource!!.data, context!!, object :
                        FriendsAdapter.ControlsListeners {
                    override fun click(postion: Int) {
                        chatRoomsList.forEachIndexed { index, addresse ->
                            if (it.resource!!.data.get(postion).id == chatRoomsList.get(index).pivot.userId){
                                val bundle = bundleOf( Constants.ROOM_ID to ""+ chatRoomsList.get(index).id)
                                findNavController().navigate(R.id.action_global_chatFragment,bundle)
                            }
                        }
                    }
                })
                rvYourFriends.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                rvYourFriends.adapter = friendsAdapter
            }
            else {
                Log.e("userFriend",  it.error!!.localizedMessage.toString());
                Snackbar.make(
                        view!!,
                        it.error!!.localizedMessage.toString(),
                        Snackbar.LENGTH_LONG
                ).show()
            }
        })


        viewModel.sentFriendRequests(id).observe(this, Observer {
            if (it.isSuccess){
                val friendsAdapter = FriendsRequestsAdapter(it.resource!!.data, context!!, object :
                        FriendsRequestsAdapter.ControlsListeners {
                    override fun add(iduser: Int) {
                        viewModel.acceptFriend(""+iduser).observe(this@FriendsFragment, Observer {
                            if (it.isSuccess){
                                Snackbar.make(
                                        view!!,
                                        it.resource!!.message!!,
                                        Snackbar.LENGTH_LONG
                                ).show()
                                FriendsFragment.newInstance()
                            }
                            else{
                                Snackbar.make(
                                        view!!,
                                        it.error.toString(),
                                        Snackbar.LENGTH_LONG
                                ).show()

                                Log.e("userFriendRequests",  it.error!!.localizedMessage.toString());

                            }
                        })
                    }

                    override fun decline(iduser: Int) {
                        viewModel.declineFriend(""+iduser).observe(this@FriendsFragment, Observer {
                            if (it.isSuccess){
                                Snackbar.make(
                                        view!!,
                                        it.resource!!.message!!,
                                        Snackbar.LENGTH_LONG
                                ).show()
                                FriendsFragment.newInstance()
                            }
                            else{
                                Snackbar.make(
                                        view!!,
                                        it.error.toString(),
                                        Snackbar.LENGTH_LONG
                                ).show()
                            }
                        })
                    }

                    override fun click(postion: Int) {

                    }
                })
                rvFriendRequests.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                rvFriendRequests.adapter = friendsAdapter
            }
            else {
                Snackbar.make(
                        view!!,
                        it.error.toString(),
                        Snackbar.LENGTH_LONG
                ).show()
            }
        })

    }

}
