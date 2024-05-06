package com.q8intouch.ecovve.ui.settings.change_password

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentChangePasswordBinding
import org.jetbrains.anko.onClick

class ChangePasswordFragment : BaseFragment<ChangePasswordViewModel,FragmentChangePasswordBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_change_password
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.onClick {
            if (binding.oldPass.text.toString().isEmpty()){
                binding.oldPass.error = getString(R.string.invaidorempty)
            }
            else
            if (binding.newPass.text.toString().isEmpty()){
                binding.newPass.error = getString(R.string.invaidorempty)
            }
            else
                if (binding.conPass.text.toString().isEmpty()){
                    binding.conPass.error = getString(R.string.invaidorempty)
                }
                else if (!binding.newPass.text.toString().equals(binding.conPass.text.toString())){
                    Snackbar.make(this.view!!,""+resources.getString(R.string.passwordnotmatch),Snackbar.LENGTH_LONG).show()

                }
                else
            viewModel.changePass(binding.oldPass.text.toString()
                ,binding.newPass.text.toString(),binding.conPass.text.toString()).observe(this, Observer {
                if (it.isSuccess){
                    if (it.resource!!.status.toString().contains("success")){
                        Snackbar.make(view!!,it.resource!!.message+"",Snackbar.LENGTH_LONG).show()
                    }
                }
                else{
                    Snackbar.make(this.view!!,""+ it.error!!.message, Snackbar.LENGTH_LONG).show()
                }
            })
        }

    }

}
