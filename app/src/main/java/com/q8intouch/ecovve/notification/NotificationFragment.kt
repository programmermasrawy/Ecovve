package com.q8intouch.ecovve.notification

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentNotificationBinding
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.extension.errorResponse

class NotificationFragment : BaseFragment<NotificationViewModel, FragmentNotificationBinding>() {
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
                           .observe(this@NotificationFragment, Observer {
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
        })
    }

}
