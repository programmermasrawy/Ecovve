package com.q8intouch.ecovve.ui.cart.coupon

import android.animation.LayoutTransition
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.*
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
import com.q8intouch.ecovve.ui.login.reset_password.ResetPasswordViewModel
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.extension.errorResponse
import kotlinx.android.synthetic.main.fragment_coupon.*
import org.jetbrains.anko.onClick


class CouponFragment : BaseFragment<CodeVerficationViewModel, FragmentResetPasswordBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_coupon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

    var list_gifts = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bundle = Bundle()
        bundle.putStringArrayList("gift", list_gifts)
        skip.onClick{

            val bundle = Bundle()
            bundle.putStringArrayList("gift", list_gifts)
            bundle.putString("coupon","")

            findNavController().navigate(com.q8intouch.ecovve.R.id.OrderCheckoutFragment, bundle)

        }
        btnResetPassword.onClick {
            if (etRestPhoneMail.text.toString().isEmpty()){
                etRestPhoneMail.error = getString(R.string.invaidorempty)
            }
            else {
                if (isInternetAvailable(context!!)) {
                    bundle.putString("coupon",etRestPhoneMail.text.toString())
                    findNavController().navigate(com.q8intouch.ecovve.R.id.OrderCheckoutFragment, bundle)
                }
                else NoInternet()
            }
        }

    }



}
