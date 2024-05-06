package com.q8intouch.ecovve.ui.social.add_friends

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.q8intouch.ecovve.util.Constants
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentFindFriendsBinding
import com.q8intouch.ecovve.util.AppUtils
import kotlinx.android.synthetic.main.fragment_find_friends.*
import kotlinx.android.synthetic.main.fragment_friends.*
import org.jetbrains.anko.onClick

class FindFriendsFragment : BaseFragment<FindFriendsViewModel, FragmentFindFriendsBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_find_friends
    }


    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val key = arguments!!.getString("key")
        when {
            key!!.contains("phone") -> etPhone.inputType = InputType.TYPE_CLASS_PHONE
            key.contains("name") -> etPhone.inputType = InputType.TYPE_CLASS_TEXT
            key.contains("email") -> etPhone.inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
        }
        search.onClick {
            if (etPhone.text.isEmpty()) {
                etPhone.error = getString(R.string.invaidorempty)
            } else viewModel.searchFriend(etPhone.text.toString(), key!!).observe(this, Observer {
                if (it.isSuccess) {

                    if (it.resource!!.result.isNotEmpty()) {
                        rvResult.visibility = View.VISIBLE
                        no_items.visibility=View.GONE
                        val friendsAdapter = FindFriendsAdapter(it.resource!!.result, context!!, object :
                                FindFriendsAdapter.ControlsListeners {
                            override fun click(position: Int) {

                            }

                            override fun add(id: Int,add : MaterialButton) {
                                viewModel.addFriend("pending", ""+Constants.USER_ID,""+it.resource!!.result!!.get(id)!!.id)
                                        .observe(this@FindFriendsFragment, Observer {
                                            if (it.isSuccess){
                                                AppUtils.showDailog(activity!!,resources.getString(R.string.sent))
                                                add.text = resources.getString(R.string.sent)
                                            }
                                            else {
                                                Log.e("error",it.error!!.localizedMessage)
                                            }
                                        })
                            }

                            override fun decline(id: Int) {

                            }

                        })
                        rvYourFriends.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        rvYourFriends.adapter = friendsAdapter
                    }
                    else{
                        rvResult.visibility = View.GONE
                        no_items.visibility=View.VISIBLE
                    }
                }else {
                    Snackbar.make(
                            view!!,
                            it.error!!.localizedMessage.toString(),
                            Snackbar.LENGTH_LONG
                    ).show()
                }
            })
        }


    }
//    override fun onStart() {
//        super.onStart()
//        activity!!.findViewById<View>(R.id.bottomBar).visibility = View.GONE
//        val lp =
//            FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
//        lp.setMargins(0, 0, 0, 0)
//
//    }
//
//    override fun onStop() {
//        super.onStop()
//        activity!!.findViewById<View>(R.id.bottomBar).visibility = View.VISIBLE
//    }
}
