package com.q8intouch.ecovve.ui.social.groups

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fxn.utility.Constants
import com.google.android.material.snackbar.Snackbar

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.R.id.*
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentGroupsBinding
import com.q8intouch.ecovve.ui.social.friends.FriendsAdapter
import com.q8intouch.ecovve.ui.social.friends.FriendsFragment
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.extension.fillRV
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.fragment_groups.*
import org.jetbrains.anko.bundleOf

class GroupsFragment : BaseFragment<GroupsViewModel,FragmentGroupsBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_groups
    }
    companion object {
        fun newInstance() = GroupsFragment()
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialog = LoadingDialog.showDialog(view!!.context)
        viewModel.showUserRooms().observe(this, Observer {
            dialog.dismiss()
            if(it.isSuccess){
                val friendsAdapter = UserGroupsAdapter(it.resource!!.data, context!!, object :
                        UserGroupsAdapter.ControlsListeners {
                    override fun click(postion: Int) {
                     val bundle = bundleOf( com.q8intouch.ecovve.util.Constants.ROOM_ID to ""+ it.resource!!.data[postion].id)
                        findNavController().navigate(R.id.action_global_chatFragment,bundle)
                    }
                })
                rvYourGroups.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                rvYourGroups.adapter = friendsAdapter
            }
            else{
                Snackbar.make(
                        view!!,
                        it.error!!.localizedMessage.toString(),
                        Snackbar.LENGTH_LONG
                ).show()
            }
        })
    }

}
