package com.q8intouch.ecovve.ui.settings

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.onesignal.OneSignal

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.R.id.*
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentSettingsBinding
import com.q8intouch.ecovve.ui.MainActivity
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.Shared
import kotlinx.android.synthetic.main.fragment_settings.*
import org.jetbrains.anko.onClick

class SettingsFragment : BaseFragment<SettingsViewModel,FragmentSettingsBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_settings
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAccountInfo.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_settingsfragment_to_account_info_fragment))
        btnChangeEmail.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_settingsFragment_to_changeEmailFragment))
        brnChangePassword.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_settingsFragment_to_changePasswordFragment))
        banSavedAddresses.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_settingsFragment_to_changeAddressFragment))
        btnPaymentMethods.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_settingsFragment_to_changePaymentMethodFragment))
        btnNotificationsSettings.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_settingsFragment_to_notificationsSettingsFragment))
        val sharedPreference:Shared= Shared(context!!)
        txtLanguage.onClick {

            if (txtLanguage.text.toString().contains("عربي")){

                sharedPreference.save("lang", "ar")

                val intent = Intent(activity,MainActivity::class.java)
                activity!!.finish()
                startActivity(intent)
            }
            else {
                sharedPreference.save("lang", "en")
                val intent = Intent(activity,MainActivity::class.java)
                activity!!.finish()
                startActivity(intent)
            }
        }

        btnLogout.setOnClickListener{
            OneSignal.setExternalUserId(""+id)
            val dialog = LoadingDialog.showDialog(view!!.context)
            viewModel.logout().observe(this, Observer {
                dialog.dismiss()
                if (it.isSuccess){
                    val sharedPreference = Shared(context!!)
                    sharedPreference.removeValue("id")
                    sharedPreference.removeValue("token")
                    val intent = Intent(activity,MainActivity::class.java)
                    activity!!.finish()
                    startActivity(intent)
                }
                else {
                    val sharedPreference = Shared(context!!)
                    sharedPreference.removeValue("id")
                    sharedPreference.removeValue("token")
                    val intent = Intent(activity,MainActivity::class.java)
                    activity!!.finish()
                    startActivity(intent)
                }
            })
        }

    }

}
