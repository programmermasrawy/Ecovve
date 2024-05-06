package com.q8intouch.ecovve.ui.settings.change_payment_methods

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.q8intouch.ecovve.R

class ChangePaymentMethodFragment : Fragment() {

    companion object {
        fun newInstance() = ChangePaymentMethodFragment()
    }

    private lateinit var viewModel: ChangePaymentMethodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.change_payment_method_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChangePaymentMethodViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
