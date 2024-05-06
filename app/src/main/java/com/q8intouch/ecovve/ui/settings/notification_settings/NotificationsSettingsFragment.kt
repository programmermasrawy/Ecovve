package com.q8intouch.ecovve.ui.settings.notification_settings

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.q8intouch.ecovve.R

class NotificationsSettingsFragment : Fragment() {

    companion object {
        fun newInstance() = NotificationsSettingsFragment()
    }

    private lateinit var viewModel: NotificationsSettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NotificationsSettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
