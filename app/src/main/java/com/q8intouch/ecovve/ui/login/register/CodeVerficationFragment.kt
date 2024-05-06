package com.q8intouch.ecovve.ui.login.register

import android.animation.LayoutTransition
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.ilhasoft.support.validation.Validator
import com.google.android.material.snackbar.Snackbar
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.extension.errorResponse
import kotlinx.android.synthetic.main.fragment_verfication_code.*


class CodeVerficationFragment : BaseFragment<CodeVerficationViewModel,com.q8intouch.ecovve.databinding.FragmentVerficationCodeBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_verfication_code
     lateinit var validator:Validator
    lateinit var currentStep:String
    lateinit var countDownTimer: CountDownTimer
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        validator = Validator(binding)
        validator.enableFormValidationMode()
        currentStep = CodeVerficationViewModel.STEP_RESET
        binding.llRoot.layoutTransition
            .enableTransitionType(LayoutTransition.CHANGING)
        viewModel.getCurrentResetStep().observe(this, Observer {showResetStep(it)})
//         Runnable {
//            activity!!.runOnUiThread(Runnable {
                Handler().postDelayed(Runnable {
                    viewModel.getCurrentResetStep().observe(this, Observer {showResetStep(it)})
                }, 60*1000)
//            })
//        }


//        binding.btnResetPassword.setOnClickListener { }
        moveToNextStep()
        binding.btnResetPassword.setOnClickListener {
//            moveToNextStep()
            if (etcode.text.toString().isEmpty()){
                etcode.error = getString(R.string.invaidorempty)
            }
            else {
                if (isInternetAvailable(context!!)) {
                    val dialog = LoadingDialog.showDialog(view!!.context)
                    viewModel.activeMobile(etcode.text.toString()).observe(this, Observer {
                        if (it.isSuccess) {
                            dialog.dismiss()
                            Snackbar.make(this.view!!, "" + getString(R.string.successfull_activation), Snackbar.LENGTH_LONG)
                                    .show()

                            findNavController().navigate(R.id.loginFragment)
                        } else {
                            dialog.dismiss()
                            Snackbar.make(view!!, it.errorResponse().message + "", Snackbar.LENGTH_LONG).show()
                        }
                    })
                }
                else {
                    val factory = LayoutInflater.from(activity!!)
                    val addToCartDialogView = factory.inflate(R.layout.no_internet, null)
                    val deleteDialog = android.app.AlertDialog.Builder(activity!!).create()
                    deleteDialog.setView(addToCartDialogView)
                    deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    deleteDialog.show()

                    deleteDialog.findViewById<AppCompatButton>(R.id.cancel).setOnClickListener {
                        //delete
//                        activity!!.recreate()
                        deleteDialog.dismiss()
                    }
                }
            }
            }

        val phone = arguments!!.getString("amount")
        binding.spanResendCode.setOnClickListener {
            if (isInternetAvailable(context!!)) {
                val dialog = LoadingDialog.showDialog(view!!.context)
                viewModel.resendMobile(phone + "").observe(this, Observer {
                    if (it.isSuccess) {
                        dialog.dismiss()
                        Snackbar.make(this.view!!, "" + it.resource!!.message, Snackbar.LENGTH_LONG)
                                .show()
                        viewModel.stopTimer()
                        currentStep = CodeVerficationViewModel.STEP_RESET

//         Runnable {
//            activity!!.runOnUiThread(Runnable {
                        Handler().postDelayed(Runnable {
                            viewModel.getCurrentResetStep().observe(this, Observer { showResetStep(it) })
                        }, 60 * 1000)
                        moveToNextStep()

                    } else {
                        dialog.dismiss()
                        Snackbar.make(view!!, it.errorResponse().message + "", Snackbar.LENGTH_LONG).show()
                    }
                })
            }
            else {
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

    private fun showResetStep(step:String){
        currentStep = step
        when(step){
            CodeVerficationViewModel.STEP_RESET -> {
                binding.etcode.isClickable = true
                binding.etcode.isEnabled = true;
//                binding.btnResetPassword.setText(R.string.reset_password)
//                binding.etcode.setHint(R.string.phone_number_or_email)
                binding.etcode.setCompoundDrawablesRelativeWithIntrinsicBounds(ResourcesCompat.getDrawable(resources,R.drawable.user,null),null,null,null)
                binding.txtSubtitle.setText(R.string.let_us_help_you)
                binding.spanResendCode.visibility = View.VISIBLE
               binding.frmTimer.visibility = View.INVISIBLE

            }
            else ->{
//                binding.btnResetPassword.setText(R.string.validate_code)
//                binding.etcode.setHint(R.string.enter_the_code)
                binding.etcode.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null)
                binding.txtSubtitle.setText(R.string.we_sent_code)
                binding.spanResendCode.visibility = View.GONE
                binding.frmTimer.visibility = View.VISIBLE
                viewModel.startTimer()
//                binding.etcode.isClickable = false
//                binding.etcode.setEnabled(false);

            }
        }
    }

    private fun moveToNextStep() {
        when(currentStep) {
            CodeVerficationViewModel.STEP_RESET -> {
                viewModel.setStepToCode()
            }
            else -> {
                if (etcode.text.toString().isEmpty()) {
                    etcode.error = getString(R.string.invaidorempty)
                } else {
                    viewModel.activeMobile(etcode.text.toString()).observe(this, Observer {
                        if (it.isSuccess) {
                            Snackbar.make(this.view!!, "" + getString(R.string.successfull_activation), Snackbar.LENGTH_LONG)
                                    .show()
                            val sharedPreference: Shared = Shared(context!!)
//                        sharedPreference.save("id", it.resource!!.message!!.id!!)
//                        sharedPreference.save("token", "" + it.resource!!.accessToken)
//                        var bundle = bundleOf("amount" to "" + it.resource!!.message!!.id!!)
                            findNavController().navigate(R.id.loginFragment)
                        } else {
                            Snackbar.make(view!!, it.errorResponse().message + "", Snackbar.LENGTH_LONG).show()
                        }
                    })
                }
            }
        }
    }

    override fun onBackPressed(): Boolean {
        if (currentStep == CodeVerficationViewModel.STEP_CODE) {
            viewModel.setStepBackToReset()
            return true
        }
        return super.onBackPressed()
    }
}
