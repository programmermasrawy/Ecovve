package com.q8intouch.ecovve.ui.cart.payment_methods_manage

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.q8intouch.ecovve.R

class PaymentMethodsManageFragment : Fragment() {

    companion object {
        fun newInstance() = PaymentMethodsManageFragment()
    }

    private lateinit var viewModel: PaymentMethodsManageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment_methods_manage, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PaymentMethodsManageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
