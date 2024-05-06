package com.q8intouch.ecovve.ui.gift.gift_card_payment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.leonardoxh.livedatacalladapter.Resource
import com.google.android.material.snackbar.Snackbar

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.GiftCardSecletMethodFragmentBinding
import com.q8intouch.ecovve.network.EcovveGiftCardCheckout
import com.q8intouch.ecovve.network.model.GiftCardsListResponse
import com.q8intouch.ecovve.ui.cart.payment_method_select.PaymentAdapter
import com.q8intouch.ecovve.ui.gift.GiftCardSecletMethodViewModel
import com.q8intouch.ecovve.util.extension.errorResponse
import com.q8intouch.ecovve.util.extension.map
import kotlinx.android.synthetic.main.gift_card_seclet_method_fragment.*
import kotlinx.android.synthetic.main.shoping_cart_detail_item.*

class GiftCardSecletMethod : BaseFragment<GiftCardSecletMethodViewModel,GiftCardSecletMethodFragmentBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.gift_card_seclet_method_fragment
    }

    var payment = ""
    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val promoadapter = PaymentAdapter(context!!, object :
                PaymentAdapter.ControlsListeners {
            override fun click(postion: Int) {
//                Toast.makeText(context!!,""+payment,Toast.LENGTH_LONG).show()
                if (postion == 0 ){
//                  Toast.makeText(context!!,""+payment,Toast.LENGTH_LONG).show()
                    payment = "knet"
                }
                else{
                    payment = "cc"
                }
            }
        })
        rvPaymentMethodSelect.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        rvPaymentMethodSelect.adapter = promoadapter

        val data  = arguments!!.getParcelable("data") as GiftCardsListResponse.DataEntity
        viewModel.giftCardCheckoutByEmail(name = arguments!!.getString("name"),
                description = arguments!!.getString("message") + ""
                ,amount = data.amount!!
                ,expire_date = data.expireDate!!
                ,status = data.status!!,
                notification_type = "mail"
        ,taker_email = arguments!!.getString("email")).observe(this, Observer {
            if (it.isSuccess){
                Snackbar.make(view!!,it.resource!!.data!!.status!!+"",Snackbar.LENGTH_LONG).show()
                var bundle = androidx.core.os.bundleOf("amount" to "" +it.resource!!.data!!.paymentUrl!!)
                findNavController().navigate(R.id.PaymentFragment, bundle)
            }
            else {
             Snackbar.make(view!!,it.errorResponse().message,Snackbar.LENGTH_LONG).show()
            }
        })
    }

}
