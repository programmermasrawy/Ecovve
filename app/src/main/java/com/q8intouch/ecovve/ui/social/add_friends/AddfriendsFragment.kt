package com.q8intouch.ecovve.ui.social.add_friends


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentFindFriendsBinding
import kotlinx.android.synthetic.main.fragment_addfriends.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.onClick


class AddfriendsFragment : BaseFragment<FindFriendsViewModel,FragmentFindFriendsBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_addfriends
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        nearByMe.onClick {
            click("near")
        }
        byPhone.onClick {
            click("phone")
        }
        byEmail.onClick {
            click("email")
        }
        byName.onClick {
            click("name")
        }
    }

    private fun click(key:String){
        var bundle = androidx.core.os.bundleOf("key" to "" + key)
        findNavController().navigate(R.id.action_addfriendsFragment_to_findFriendsFragment,bundle)
    }
//    override fun onStart() {
//        super.onStart()
//        activity!!.findViewById<View>(R.id.bottomBar).visibility = View.GONE
//    }
//
//    override fun onStop() {
//        super.onStop()
//        activity!!.findViewById<View>(R.id.bottomBar).visibility = View.VISIBLE
//    }
}
