package com.q8intouch.ecovve.ui.settings.account_info

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentAccountInfoBinding
import com.q8intouch.ecovve.util.LoadingDialog

class AccountInfoFragment : BaseFragment<AccountInfoViewModel, FragmentAccountInfoBinding>() {

    companion object {
        fun newInstance() = AccountInfoFragment()
    }

    override fun getLayoutRes() = R.layout.fragment_account_info

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialog = LoadingDialog.showDialog(view.context)
        observeData(dialog)
    }

    private fun observeData(dialog: Dialog) {
        viewModel.loadAccountInfo().observe(this, Observer { response ->
            dialog.dismiss()
            if (response.isSuccess) {
                val data = response.resource?.data
                data?.let {
                    viewModel.email.value = data.email
                    viewModel.name.value = data.name
                    viewModel.birthDate.value = data.birthday
                    viewModel.isMale.value = data.gender.toLowerCase() == "male"
                }
            }
        })
        viewModel.saveClickEvent.observe(this, Observer { })
    }
}
