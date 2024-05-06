package com.q8intouch.ecovve.ui.notifications

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentNotificationBinding
import com.q8intouch.ecovve.notification.NotificationViewModel
import com.q8intouch.ecovve.notification.NotificationsAdapter
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.extension.errorResponse

class Notifications1 : BaseFragment<NotificationViewModel, FragmentNotificationBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_notification
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialog = LoadingDialog.showDialog(view!!.context)
        viewModel.allnotifications().observe(this, Observer {
            if (it.isSuccess){
                dialog.dismiss()
                val notificationsAdapter = NotificationsAdapter(it.resource!!.data!!, context!!, object :
                        NotificationsAdapter.ControlsListeners {
                    override fun click(postion: Int, layout: MaterialCardView) {
                        viewModel.seenNotification("yes",""+it.resource!!.data!!.get(postion)!!.id)
                                .observe(this@Notifications1, Observer {
                                    layout.setBackgroundColor(resources.getColor(R.color.seen));
                                })

                    }
                })
                binding.notifications.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.notifications.adapter = notificationsAdapter

            }
            else{
                Snackbar.make(view, it.errorResponse().message + "", Snackbar.LENGTH_LONG).show()
            }
        })    }

}
