package com.q8intouch.ecovve.ui.login.register

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.ilhasoft.support.validation.Validator
import com.google.android.material.snackbar.Snackbar
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentRegisterBinding
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.extension.errorResponse
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment<RegisterViewModel,FragmentRegisterBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_register

    private lateinit var validator: Validator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        validator = Validator(binding)
        validator.enableFormValidationMode()
        binding.btnRegister.setOnClickListener { onSignUpClick() }
        login.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }

    private fun onSignUpClick() {
        if(validation()) {
            if (isInternetAvailable(context!!)) {
                val dialog = LoadingDialog.showDialog(view!!.context)
                viewModel.submitRegisterRequest(binding.etFullName.text.toString(),
                        binding.etEmail.text.toString(),
                        binding.etPhone.text.toString(),
                        binding.etPassword.text.toString(),
                        binding.etPasswordconfirm.text.toString()
                ).observe(this, Observer {
                    dialog.dismiss()
                    if (it.isSuccess) {
                        if (it.resource!!.message.contains("email exist and not activated")){
                            Snackbar.make(this.view!!, "" + it.resource!!.message, Snackbar.LENGTH_LONG).show()
                            var bundle = bundleOf("amount" to "" + etPhone.text.toString())
                            findNavController().navigate(R.id.CodeVerficationFragment, bundle)
                        }
                       else if (it.resource!!.status.contains("failed")) {
                            if (it.resource!!.status.contains("given")) {
                                Snackbar.make(this.view!!, "" + getString(R.string.email_used), Snackbar.LENGTH_LONG).show()
                            } else {
                                if (it.resource!!.status.contains("activ")) {
                                    var bundle = bundleOf("amount" to "" + etPhone.text.toString())
                                    findNavController().navigate(R.id.CodeVerficationFragment, bundle)
                                } else
                                    Snackbar.make(this.view!!, "" + resources.getString(R.string.fialed_signup), Snackbar.LENGTH_LONG)
                                            .show()
//                                    Snackbar.make(this.view!!, "" + it.errorResponse().message, Snackbar.LENGTH_LONG)
//                                            .show()
                            }
                        } else {
                                Snackbar.make(this.view!!, "" + it.resource!!.message, Snackbar.LENGTH_LONG)
                                        .show()
                                var bundle = bundleOf("amount" to "" + etPhone.text.toString())
                                findNavController().navigate(R.id.CodeVerficationFragment, bundle)
//
                        }
                    } else {
                            Snackbar.make(this.view!!, "" + it.errorResponse().message, Snackbar.LENGTH_LONG)
                                    .show()
                    }
                })
            }
            else NoInternet()
        }
    }
    fun NoInternet(){
        val factory = LayoutInflater.from(activity!!)
        val addToCartDialogView = factory.inflate(R.layout.no_internet, null)
        val deleteDialog = android.app.AlertDialog.Builder(activity!!).create()
        deleteDialog.setView(addToCartDialogView)
        deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        deleteDialog.show()

        deleteDialog.findViewById<AppCompatButton>(R.id.cancel).setOnClickListener {
            //delete
//                    activity!!.recreate()
            deleteDialog.dismiss()
        }
    }
    fun isInternetAvailable(context : Context):Boolean {
        var connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager
                .getActiveNetworkInfo().isConnected();
    }
    private fun validation() : Boolean {
        var state = false
        if (binding.etFullName.text.toString().isEmpty()){
            binding.etFullName.setError(getString(R.string.invaidorempty))
            state = false
        }
        else if (binding.etPhone.text.toString().isEmpty()){
            binding.etPhone.error = getString(R.string.invaidorempty)
            state = false
        }
        else if (binding.etPhone.text.toString().length < 8){
            Snackbar.make(this.view!!,""+resources.getString(R.string.phonelength),Snackbar.LENGTH_LONG).show()
            state = false
        }
        else if (binding.etEmail.text.toString().isEmpty()){
            binding.etEmail.error = getString(R.string.invaidorempty)
            state = false
        }
        else if (binding.etPassword.text.toString().isEmpty()){
            binding.etPassword.error = getString(R.string.invaidorempty)
            state = false
        }
        else if (binding.etPasswordconfirm.text.toString().isEmpty()){
            binding.etPasswordconfirm.error = getString(R.string.invaidorempty)
            state = false
        }
        else if (!binding.etPasswordconfirm.text.toString().equals(binding.etPassword.text.toString())){
            Snackbar.make(this.view!!,""+resources.getString(R.string.passwordnotmatch),Snackbar.LENGTH_LONG).show()
            state = false
        }
        else if (binding.etPassword.text.toString().length < 8){
            binding.etPassword.error = getString(R.string.passlength)
            state = false
        }
        else state = true
        return  state
    }
}
