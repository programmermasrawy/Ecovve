package com.q8intouch.ecovve.ui.cart.payment_method_edit

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.q8intouch.ecovve.R
import kotlinx.android.synthetic.main.fragment_payment_method_edit.*

class PaymentMethodEditFragment : Fragment() {

    companion object {
        fun newInstance() = PaymentMethodEditFragment()
    }

    private lateinit var viewModel: PaymentMethodEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment_method_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PaymentMethodEditViewModel::class.java)
        // TODO: Use the ViewModel
        btnVisaMethod.setOnClickListener {
            btnVisaMethod.strokeColor = resources.getColor(R.color.colorPrimary)
            btnKnetMethod.strokeColor = resources.getColor(R.color.GrayED)
            btnMasterCardMethod.strokeColor = resources.getColor(R.color.GrayED)
            secNumberCard.visibility = View.GONE
            btnAddPaymentMethod.text = "Add Visa"
        }
        btnKnetMethod.setOnClickListener {
            btnVisaMethod.strokeColor = resources.getColor(R.color.GrayED)
            btnKnetMethod.strokeColor = resources.getColor(R.color.colorPrimary)
            btnMasterCardMethod.strokeColor = resources.getColor(R.color.GrayED)
            secNumberCard.visibility = View.VISIBLE
            btnAddPaymentMethod.text = "Add Knet"
        }
        btnMasterCardMethod.setOnClickListener {
            btnVisaMethod.strokeColor = resources.getColor(R.color.GrayED)
            btnKnetMethod.strokeColor = resources.getColor(R.color.GrayED)
            btnMasterCardMethod.strokeColor = resources.getColor(R.color.colorPrimary)
            secNumberCard.visibility = View.GONE
            btnAddPaymentMethod.text = "Add MasterCard"
        }
    }

}
