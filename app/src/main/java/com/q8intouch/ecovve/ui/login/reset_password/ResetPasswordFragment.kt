package com.q8intouch.ecovve.ui.login.reset_password

import android.animation.LayoutTransition
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.ilhasoft.support.validation.Validator
import com.google.android.material.snackbar.Snackbar
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentResetPasswordBinding
import com.q8intouch.ecovve.ui.login.register.CodeVerficationViewModel
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.extension.errorResponse
import kotlinx.android.synthetic.main.fragment_reset_password.*


class ResetPasswordFragment : BaseFragment<CodeVerficationViewModel, FragmentResetPasswordBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_reset_password
     lateinit var validator:Validator
    lateinit var currentStep:String

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        validator = Validator(binding)
        validator.enableFormValidationMode()

        login.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
        binding.btnResetPassword.setOnClickListener {
            //            moveToNextStep()
            if (etRestPhoneMail.text.toString().isEmpty()){
                etRestPhoneMail.error = getString(R.string.invaidorempty)
            }
            else {
                if (isInternetAvailable(context!!)) {
                    val dialog = LoadingDialog.showDialog(view!!.context)
                    if (!etRestPhoneMail.text.toString().contains("@") && !etRestPhoneMail.text.toString().contains(".")) {
                        viewModel.resendPassCode(etRestPhoneMail.text.toString()).observe(this, Observer {
                            dialog.dismiss()
                            if (it.isSuccess) {
                                Snackbar.make(this.view!!, "" + getString(R.string.verfication_sent), Snackbar.LENGTH_LONG)
                                        .show()
                                var bundle = bundleOf("amount" to "" + etRestPhoneMail.text.toString())
                                findNavController().navigate(R.id.newPasswordFragment, bundle)
                            } else {
                                Snackbar.make(view!!, it.errorResponse().message.toString() + "", Snackbar.LENGTH_LONG).show()
                            }
                        })
                    } else {
                        viewModel.resendPassCodeEmail(etRestPhoneMail.text.toString()).observe(this, Observer {
                            dialog.dismiss()
                            if (it.isSuccess) {
                                Snackbar.make(this.view!!, "" + it.resource!!.message, Snackbar.LENGTH_LONG)
                                        .show()
                                var bundle = bundleOf("amount" to "" + etRestPhoneMail.text.toString())
                                findNavController().navigate(R.id.newPasswordFragment, bundle)
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

    private fun showResetStep(step:String){

        currentStep = step
        when(step){
            ResetPasswordViewModel.STEP_RESET -> {
                binding.btnResetPassword.setText(R.string.reset_password)
//                binding.etRestPhoneMail.setHint(R.string.phone_number_)
                binding.etRestPhoneMail.setCompoundDrawablesRelativeWithIntrinsicBounds(ResourcesCompat.getDrawable(resources,R.drawable.user,null),null,null,null)
                binding.txtSubtitle.setText(R.string.let_us_help_you)
                binding.spanResendCode.visibility = View.INVISIBLE
               binding.frmTimer.visibility = View.INVISIBLE
            }
            else ->{
                binding.btnResetPassword.setText(R.string.validate_code)
//                binding.etRestPhoneMail.setHint(R.string.enter_the_code)
                binding.etRestPhoneMail.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null)
                binding.txtSubtitle.setText(R.string.we_sent_code)
                binding.spanResendCode.visibility = View.VISIBLE
                binding.frmTimer.visibility = View.VISIBLE
                viewModel.startTimer()

            }
        }
    }



    override fun onBackPressed(): Boolean {
        if (currentStep == ResetPasswordViewModel.STEP_CODE ) {
            viewModel.setStepBackToReset()
            return true
        }
        return super.onBackPressed()
    }
}
