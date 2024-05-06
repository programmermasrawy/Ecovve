package com.q8intouch.ecovve.ui.login.new_password

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentNewPassowordBinding
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.extension.errorResponse
import kotlinx.android.synthetic.main.fragment_new_passoword.*
import kotlinx.android.synthetic.main.fragment_verfication_code.*

class NewPasswordFragment : BaseFragment<NewPasswordViewModel,FragmentNewPassowordBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_new_passoword


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnResetPassword.setOnClickListener { onResetClicked() }
        binding.spanSignIn.setOnClickListener {findNavController().popBackStack(R.id.loginFragment,false)}
        val resetNonMatchingPasswordError = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                binding.etPassword.error = null
                binding.etConfirmPassword.error = null
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        }
        binding.etPassword.addTextChangedListener(resetNonMatchingPasswordError)
        binding.etConfirmPassword.addTextChangedListener(resetNonMatchingPasswordError)
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
    private fun onResetClicked() {
        if (validatePasswordConfirmation()){
//            findNavController().popBackStack()
            if (etToken.text.toString().isEmpty())
                etToken.error = getString(R.string.invaidorempty)
            else {
                if (isInternetAvailable(context!!)) {
                    val dialog = LoadingDialog.showDialog(view!!.context)
                    var email = arguments!!.getString("amount")
                    if (!email.toString().contains("@") && !email.toString().contains(".")) {
                        viewModel.resetPassByPhone(etToken.text.toString(),
                                email, etPassword.text.toString(), etConfirmPassword.text.toString()).observe(this, Observer {
                            dialog.dismiss()
                            if (it.isSuccess) {
                                Snackbar.make(this.view!!, "" + it.resource!!.message, Snackbar.LENGTH_LONG)
                                        .show()
//                                var bundle = bundleOf("amount" to "" + etcode.text.toString())
                                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                            } else {
                                Snackbar.make(view!!, it.errorResponse().message.toString() + "", Snackbar.LENGTH_LONG).show()
                            }
                        })
                    } else {
                        viewModel.resetPassByEmail(etToken.text.toString(),
                                email, etPassword.text.toString(), etConfirmPassword.text.toString()).observe(this, Observer {
                            dialog.dismiss()
                            if (it.isSuccess) {
//                                dialog.dismiss()
                                Snackbar.make(this.view!!, "" + it.resource!!.message, Snackbar.LENGTH_LONG)
                                        .show()
//                                var bundle = bundleOf("amount" to "" + etcode.text.toString())
                                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                            } else {
                                Snackbar.make(view!!, it.errorResponse().message.toString() + "", Snackbar.LENGTH_LONG).show()
                            }
                        })
                    }
                }
                else NoInternet()
            }
        }
    }

    private fun validatePasswordConfirmation() : Boolean  {
        val isPasswordConfirmedCorrectly = binding.etPassword.text.toString() == binding.etConfirmPassword.text.toString()
        if(!isPasswordConfirmedCorrectly){
            binding.etPassword.error = getString(R.string.password_dont_match)
            binding.etConfirmPassword.error = getString(R.string.password_dont_match)
        }
       return isPasswordConfirmedCorrectly
    }
}
