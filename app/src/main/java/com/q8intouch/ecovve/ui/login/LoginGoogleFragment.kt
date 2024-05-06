package com.q8intouch.ecovve.ui.login

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentLoginBinding
import com.q8intouch.ecovve.ui.HomeActivity
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.Shared


class LoginGoogleFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_login


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val userID = arguments!!.getString("id", "")
        val Token = arguments!!.getString("token", "")

        if (userID.isNotEmpty() && Token.isNotEmpty()) {
            getlogingoogle(userID, Token)
        }
    }


    private fun getlogingoogle(id: String, accessToken: String?) {
        socialLogin("google", id, accessToken!!)

    }

    var a = true
    private fun socialLogin(authProvider: String, Id: String, TOKEN: String) {
        if (a) {
            val dialog = LoadingDialog.showDialog(view!!.context)
            val android_id = Settings.Secure.getString(activity!!.contentResolver,
                    Settings.Secure.ANDROID_ID)
            viewModel.login(authProvider, Id, TOKEN, context!!,android_id, findNavController())
                    .observe(this, Observer {
                        if (it.isSuccess) {
                            dialog.dismiss()
                            Snackbar.make(view!!, it.resource.toString(), Snackbar.LENGTH_LONG)
                            if (it.resource!!.data.phone != null) {
                                val sharedPreference: Shared = Shared(context!!)
                                sharedPreference.save("id", it.resource!!.data.id!!)
                                sharedPreference.save("token", "" + it.resource!!.accessToken)
//                                var bundle = bundleOf("amount" to "" + it.resource!!.data.id!!)
//                                findNavController().navigate(R.id.action_loginFragment_to_homeActivity, bundle)
                                val intent = Intent(activity, HomeActivity::class.java)
                                intent.putExtra("amount", "" + it.resource!!.data.id!!)
                                activity!!.finish()
                                startActivity(intent)
                            }
                        } else {
                            Snackbar.make(view!!, it.error.toString() + "", Snackbar.LENGTH_LONG).show()
                            dialog.dismiss()
                        }
                    })
            a = false
        }
    }
}
