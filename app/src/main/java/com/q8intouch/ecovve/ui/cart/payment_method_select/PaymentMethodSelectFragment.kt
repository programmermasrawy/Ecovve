package com.q8intouch.ecovve.ui.cart.payment_method_select

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.model.ShopCart
import com.q8intouch.ecovve.databinding.FragmentPaymentMethodSelectBinding
import com.q8intouch.ecovve.network.model.cart.CartPost
import kotlinx.android.synthetic.main.fragment_payment_method_select.*
import com.q8intouch.ecovve.util.extension.errorResponse
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavOptions
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.URLs
import java.util.*


class PaymentMethodSelectFragment : BaseFragment<PaymentMethodSelectViewModel,FragmentPaymentMethodSelectBinding>() {
    override fun getLayoutRes(): Int {
      return com.q8intouch.ecovve.R.layout.fragment_payment_method_select
    }

    companion object {
        fun newInstance() = PaymentMethodSelectFragment()
    }
    var pairs: Map<String, String>? = null
    var id = ""
    var addressID = ""
    var time = ""
    var gift = ArrayList<String>()
    var expected_price = ""
    var coupon = ""
    var outlet_id = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
         id = activity!!.intent.extras.getString("amount","")
         addressID = arguments!!.getString("address","")
         time = arguments!!.getString("time","")
         gift = arguments!!.getStringArrayList("gift")
    }
    var payment = ""
    val gift_list = HashMap<String,String>()
    var notes = ""
    var area_id = ""
    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLang()
        id = activity!!.intent.extras.getString("amount","")
        addressID = arguments!!.getString("address","")
        time = arguments!!.getString("time","")
        gift = arguments!!.getStringArrayList("gift")
        coupon = arguments!!.getString("coupon")
        val sharedPreferences: Shared = Shared(context!!)
        notes = "" + sharedPreferences.getValueString(URLs.Notes) + "."
        area_id = ""+ sharedPreferences.getValueString(URLs.AREA_ID)
        val items = ArrayList<CartPost.Item>() as ArrayList<CartPost.Item>
        viewModel.cart.value!!.forEach {item: Map.Entry<Int, ShopCart> ->
            item.value.items.value!!.forEach{ad : Map.Entry<CartItem, Int>  ->
                items.add(CartPost.Item(""+ad.key.id,""+ad.value,""+ad.key.price,"on",""+ad.key.shopId,ad.key.extra))
            }
        }

        var cartRow = CartPost(
                items,
                notes
        ) as CartPost

        val fields = HashMap<String,String>()
        cartRow.item.forEachIndexed{index, odm ->
             outlet_id = odm.outlet_id
            fields.put("items[$index][id]",odm.id)
            fields.put("items[$index][qty]",odm.qty)
        }


        gift.forEachIndexed{index, odm ->
            fields.put("choices[$index][id]", gift[index])
        }


        checkPrice("knet",fields)

        val promoadapter = PaymentAdapter(context!!, object :
            PaymentAdapter.ControlsListeners {
            override fun click(postion: Int) {
//                Toast.makeText(context!!,""+payment,Toast.LENGTH_LONG).show()
                when (postion) {
                    0 -> {
        //                  Toast.makeText(context!!,""+payment,Toast.LENGTH_LONG).show()
                        payment = "knet"
                        checkPrice(payment,fields)
                    }
                    1 -> {
                        payment = "cc"
                        checkPrice(payment,fields)
                    }
                    2 -> {
                        payment = "cash"
                        checkPrice(payment,fields)
                    }
                }
            }
        })
        binding.rvPaymentMethodSelect.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        binding.rvPaymentMethodSelect.adapter = promoadapter


        btnContinue.setOnClickListener{
            val image =  activity!!.findViewById<TextView>(R.id.imageBadge)
            image.visibility = View.GONE
            val dialog = LoadingDialog.showDialog(view.context)
            if (payment!= "") {
                if (addressID != ""){
                            if (coupon!=""){
                                viewModel.checkoutAddressCoupon(outlet_id,
                                        notes,
                                        "" + id,
                                        "" + id,
                                        "" + addressID,
                                        "on",
                                        "" + payment,  fields
                                        , "" + expected_price , coupon
                                        , context!!, findNavController(),area_id).observe(this, Observer {
                                    dialog.dismiss()
                                    if (it.isSuccess) {
                                        if (payment.equals("cash")) {
                                            var bundle = Bundle()
                                            bundle.putString("id",""+it.resource!!.data.track_id)
                                            bundle.putString("data",it.resource!!.data.status)
                                            findNavController().navigate(R.id.orderCompleteFragment, bundle)
                                        } else {
                                            var bundle = androidx.core.os.bundleOf("amount" to "" + it.resource!!.data.paymentUrl)
                                            findNavController().navigate(com.q8intouch.ecovve.R.id.action_orderTypeFragment_to_PaymentFragment, bundle)
                                        }
                                    } else {
                                        Toast.makeText(context!!, it.errorResponse().message, Toast.LENGTH_LONG).show()
                                        Toast.makeText(context!!, it.error!!.localizedMessage, Toast.LENGTH_LONG).show()
                                    }
                                })
                            }else
                            if (gift.size == 0){
                                viewModel.checkAdress(outlet_id,
                                    notes,
                                    "" + id,
                                    "" + id,
                                    "" + addressID,
                                    "off",
                                    "" + payment, "delivery", fields
                                    , "" + expected_price
                                    , area_id).observe(this, Observer {
                                dialog.dismiss()
                                if (it.isSuccess) {
                                    if (payment.equals("cash")) {
                                        var bundle = Bundle()
                                        bundle.putString("id",""+it.resource!!.data.track_id)
                                        bundle.putString("data",it.resource!!.data.status)
                                        findNavController().navigate(R.id.orderCompleteFragment, bundle)
                                    } else {
                                    var bundle = androidx.core.os.bundleOf("amount" to "" + it.resource!!.data.paymentUrl)
                                    findNavController().navigate(com.q8intouch.ecovve.R.id.action_orderTypeFragment_to_PaymentFragment, bundle)
                                    }
                             } else {
                                    Toast.makeText(context!!, it.errorResponse().message, Toast.LENGTH_LONG).show()
                                    Toast.makeText(context!!, it.error!!.localizedMessage, Toast.LENGTH_LONG).show()
                                }
                            })
                            }
                            else {
                                viewModel.checkoutAddressGiftCard(outlet_id,
                                        notes,
                                        "" + id,
                                        "" + id,
                                        "" + addressID,
                                        "off",
                                        "" + payment, "delivery", fields
                                        , "" + expected_price,gift_list
                                        ,area_id).observe(this, Observer {
                                    dialog.dismiss()
                                    if (it.isSuccess) {
                                        if (payment.equals("cash")) {
                                            var bundle = Bundle()
                                            bundle.putString("id",""+it.resource!!.data.track_id)
                                            bundle.putString("data",it.resource!!.data.status)
                                            findNavController().navigate(R.id.orderCompleteFragment, bundle)
                                        } else {
                                            var bundle = androidx.core.os.bundleOf("amount" to "" + it.resource!!.data.paymentUrl)
                                            findNavController().navigate(com.q8intouch.ecovve.R.id.action_orderTypeFragment_to_PaymentFragment, bundle)
                                        }
                                    } else {
                                        Toast.makeText(context!!, it.errorResponse().message, Toast.LENGTH_LONG).show()
                                        Toast.makeText(context!!, it.error!!.localizedMessage, Toast.LENGTH_LONG).show()
                                    }
                                })
                            }

            }else{
                    if (time != ""){
                        if (coupon != ""){
                            viewModel.checkpickGiftCoupon(outlet_id,
                                    notes,
                                    "" + id,
                                    "" + id,
                                    "" + time,
                                    "off",
                                    "" + payment, "pickup", fields
                                    ,  expected_price,coupon
                                    , area_id).observe(this, Observer {
                                dialog.dismiss()
                                if (it.isSuccess) {
                                    if (payment.equals("cash")) {
                                        var bundle = Bundle()
                                        bundle.putString("id",""+it.resource!!.data.track_id)
                                        bundle.putString("data",it.resource!!.data.status)
                                        findNavController().navigate(R.id.orderCompleteFragment, bundle)
                                    } else {
                                        var bundle = androidx.core.os.bundleOf("amount" to "" + it.resource!!.data.paymentUrl)
                                        findNavController().navigate(com.q8intouch.ecovve.R.id.action_orderTypeFragment_to_PaymentFragment, bundle)
                                    }
                                } else {
                                    Toast.makeText(context!!, it.errorResponse().message, Toast.LENGTH_LONG).show()
                                    Toast.makeText(context!!, it.error!!.localizedMessage, Toast.LENGTH_LONG).show()
                                }
                            })
                        }else
                        if (gift.size == 0) {
                            viewModel.checkpick(outlet_id,
                                    notes,
                                    "" + id,
                                    "" + id,
                                    "" + time,
                                    "off",
                                     payment, "pickup", fields
                                    , expected_price
                                    , area_id).observe(this, Observer {
                                dialog.dismiss()
                                if (it.isSuccess) {
                                    if (payment.equals("cash")) {
                                        var bundle = Bundle()
                                        bundle.putString("id",""+it.resource!!.data.track_id)
                                        bundle.putString("data",it.resource!!.data.status)
                                        findNavController().navigate(R.id.orderCompleteFragment, bundle)
                                    } else {
                                    var bundle = androidx.core.os.bundleOf("amount" to "" + it.resource!!.data.paymentUrl)
                                    findNavController().navigate(com.q8intouch.ecovve.R.id.action_orderTypeFragment_to_PaymentFragment, bundle)
                                }
                                } else {
                                    Toast.makeText(context!!, it.errorResponse().message, Toast.LENGTH_LONG).show()
                                    Toast.makeText(context!!, it.error!!.localizedMessage, Toast.LENGTH_LONG).show()
                                }
                            })
                        }
                        else {
                            viewModel.checkpickGift(outlet_id,
                                    notes,
                                    "" + id,
                                    "" + id,
                                    "" + time,
                                    "off",
                                    "" + payment, "pickup", fields
                                    ,  expected_price,gift_list
                                    , area_id).observe(this, Observer {
                                dialog.dismiss()
                                if (it.isSuccess) {
                                    if (payment.equals("cash")) {
                                        var bundle = Bundle()
                                        bundle.putString("id",""+it.resource!!.data.track_id)
                                        bundle.putString("data",it.resource!!.data.status)
                                        findNavController().navigate(R.id.orderCompleteFragment, bundle)
                                    } else {
                                        var bundle = androidx.core.os.bundleOf("amount" to "" + it.resource!!.data.paymentUrl)
                                        findNavController().navigate(com.q8intouch.ecovve.R.id.action_orderTypeFragment_to_PaymentFragment, bundle)
                                    }
                                } else {
                                    Toast.makeText(context!!, it.errorResponse().message, Toast.LENGTH_LONG).show()
                                    Toast.makeText(context!!, it.error!!.localizedMessage, Toast.LENGTH_LONG).show()
                                }
                            })
                        }
                    }
                }
            } else {
                Snackbar.make(this.view!!, "" + resources.getString(com.q8intouch.ecovve.R.string.no_payment), Snackbar.LENGTH_LONG).show()
            }
        }


        btnEditPaymentMethods.setOnClickListener(Navigation.createNavigateOnClickListener(com.q8intouch.ecovve.R.id.action_paymentMethodSelectFragment_to_paymentMethodEditFragment))

    }

    @SuppressLint("SetTextI18n")
    fun checkPrice(payment: String, fields: HashMap<String, String>){
        val sharedPreferences: Shared = Shared(context!!)
        Log.e("OOOOOO",id)
        Log.e("OOOOOO",outlet_id)
        Log.e("OOOOOO",time)
        Log.e("OOOOOO",notes)
        Log.e("OOOOOO",coupon)
        Log.e("OOOOOO",area_id)
        if (time!=null) {
            if (coupon!= ""){
                viewModel.checkPricePickupCoupon(
                        user_id = "" + id,
                        outlet_id = outlet_id,
                        delivery_type = "pickup",
                        pickup_time = time,
                        free_credit = "on",
                        notes = notes,
                        assigned_user_id = "" + id,
                        payment_method = "" + payment,
                        gift_card_id = coupon,
                        items = fields
                        ,area_id = area_id
                ).observe(this, Observer {
                    if (it.isSuccess) {
                        txtTotalPayment.text = getString(R.string.total) + " " + it.resource!!.data.total + getString(R.string.kd)
                        expected_price =""+ it.resource!!.data.total
                    }
                    else {
                        Log.e("OOOOOO",it.errorResponse().toString())
                    }
                })
            }
            if (gift.size == 0) {
                viewModel.checkPricePickup(
                        user_id = "" + id,
                        outlet_id = outlet_id,
                        delivery_type = "pickup",
                        pickup_time = time,
                        free_credit = "on",
                        notes = notes,
                        assigned_user_id = "" + id,
                        payment_method = "" + payment,
                        items = fields ,area_id = area_id
                ).observe(this, Observer {
                    if (it.isSuccess) {
                        txtTotalPayment.text = getString(R.string.total) + " " + it.resource!!.data.total + getString(R.string.kd)
                        expected_price =""+ it.resource!!.data.total
                    }
                    else {
                        Log.e("OOOOOO",it.errorResponse().toString())
                    }
                })
            }
            else {
                viewModel.checkPricePickupGift(
                        user_id = "" + id,
                        outlet_id = outlet_id,
                        delivery_type = "pickup",
                        pickup_time = time,
                        free_credit = "on",
                        notes = notes,
                        assigned_user_id = "" + id,
                        payment_method = "" + payment,
                        gift_card_id = gift_list,
                        items = fields
                        ,area_id = area_id
                ).observe(this, Observer {
                    if (it.isSuccess) {
                        txtTotalPayment.text = getString(R.string.total) + " " + it.resource!!.data.total + getString(R.string.kd)
                        expected_price =""+ it.resource!!.data.total
                    }
                    else {
                        Log.e("OOOOOO",it.errorResponse().toString())
                    }
                })
            }
        }
        if (addressID!=null) {
            if (coupon!= ""){
                viewModel.checkPriceAddressCoupon(
                        user_id = "" + id,
                        outlet_id = outlet_id,
                        delivery_type = "delivery",
                        address_id = addressID,
                        free_credit = "on",
                        notes = notes,
                        assigned_user_id = "" + id,
                        payment_method = "" + payment,
                        coupon_code = coupon,
                        items = fields , area_id = area_id
                ).observe(this, Observer {
                    if (it.isSuccess) {
                        txtTotalPayment.text = getString(R.string.total) + " " + it.resource!!.data.total + getString(R.string.kd)
                        expected_price =""+ it.resource!!.data.total
                    }
                    else {
                        Log.e("OOOOOO",it.errorResponse().toString())
                    }
                })
            }
            if (gift.size == 0) {
                viewModel.checkPriceAddress(
                        user_id = "" + id,
                        outlet_id = outlet_id,
                        delivery_type = "delivery",
                        address_id = addressID,
                        free_credit = "on",
                        notes = notes,
                        assigned_user_id = "" + id,
                        payment_method = "" + payment,
                        items = fields,area_id = area_id
                ).observe(this, Observer {
                    if (it.isSuccess) {
                        txtTotalPayment.text = getString(R.string.total) + " " + it.resource!!.data.total + getString(R.string.kd)
                        expected_price =""+ it.resource!!.data.total
                    }
                    else {
                        Log.e("OOOOOO",it.errorResponse().toString())
                    }
                })
            }
            else {
                viewModel.checkPriceAddressGift(
                        user_id = "" + id,
                        outlet_id = outlet_id,
                        delivery_type = "delivery",
                        address_id = addressID,
                        free_credit = "on",
                        notes = notes,
                        assigned_user_id = "" + id,
                        payment_method = "" + payment,
                        gift_card_id = gift_list,
                        items = fields
                        ,area_id = area_id
                ).observe(this, Observer {
                    if (it.isSuccess) {
                        txtTotalPayment.text = getString(R.string.total) + " " + it.resource!!.data.total + getString(R.string.kd)
                        expected_price =""+ it.resource!!.data.total
                    }
                    else {
                        Log.e("OOOOOO",it.errorResponse().toString())
                    }
                })
            }


        }
    }

    var locale: Locale? = null
    private fun Language(lang: String) {
        val country = "US"
        if (lang == "en")
            locale = Locale(lang)
        else
            locale = Locale(lang)

        Locale.setDefault(locale)
        val configs = Configuration()
        configs.locale = locale
        context!!.resources.updateConfiguration(configs, context!!.resources.displayMetrics)
    }


    private fun setUpLang() {
        val sharedPreference: Shared = Shared( context!!)
        val lang =  sharedPreference.getValueString("lang")

        if (lang==null){
            Language("en")
        }
        else {
            if (lang.equals("ar")) {
                Language("ar")
                URLs.lang = "ar"
            }
            else {
                Language("en")
                URLs.lang = "en"
            }
        }
    }
}
