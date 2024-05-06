package com.q8intouch.ecovve.ui.cart.giftOrCoupon

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.q8intouch.ecovve.R
import kotlinx.android.synthetic.main.gift_or_coupon_fragment.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textColor

class GiftOrCouponFragment : Fragment() {
    var list_gifts = ArrayList<String>()
    companion object {
        fun newInstance() = GiftOrCouponFragment()
    }

    private lateinit var viewModel: GiftOrCouponViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.gift_or_coupon_fragment, container, false)
    }
    var a = false
    var gift = false
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GiftOrCouponViewModel::class.java)
        choose_gift.onClick {
           a = true
           gift = true
            choose_gift.background = resources.getDrawable(R.drawable.btn_bg)
            choose_gift.textColor = resources.getColor(R.color.textWhite)
            choose_coupon.background = resources.getDrawable(R.drawable.btn_big_borders_only)
            choose_coupon.textColor = resources.getColor(R.color.colorPrimary)
        }

        choose_coupon.onClick {
            a = true
            gift = false
            choose_gift.background = resources.getDrawable(R.drawable.btn_big_borders_only)
            choose_gift.textColor = resources.getColor(R.color.colorPrimary)
            choose_coupon.background = resources.getDrawable(R.drawable.btn_bg)
            choose_coupon.textColor = resources.getColor(R.color.textWhite)
        }

        next.onClick {
            if (a){
                if (gift){
                    var id = activity!!.intent.extras.getString("amount")
                    var addressID = arguments!!.getString("address")
                    var time = arguments!!.getString("time")
                    val bundle = Bundle()
                    bundle.putString("address", addressID)
                    bundle.putString("time", time)
                    findNavController().navigate(com.q8intouch.ecovve.R.id.SelectGiftCardOrderFragment, bundle)
                }
                else {
                    var id = activity!!.intent.extras.getString("amount")
                    var addressID = arguments!!.getString("address")
                    var time = arguments!!.getString("time")
                    val bundle = Bundle()
                    bundle.putString("address", addressID)
                    bundle.putString("time", time)
                    findNavController().navigate(com.q8intouch.ecovve.R.id.couponFragment, bundle)
                }
            }
        }

        skip.onClick {
            var id = activity!!.intent.extras.getString("amount")
            var addressID = arguments!!.getString("address")
            var time = arguments!!.getString("time")
            val bundle = Bundle()
            bundle.putString("address", addressID)
            bundle.putString("time", time)
            bundle.putStringArrayList("gift", list_gifts)
            bundle.putString("coupon","")

            findNavController().navigate(com.q8intouch.ecovve.R.id.action_orderTypeFragment_to_paymentMethodSelectFragment, bundle)
        }
    }
}
