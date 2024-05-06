package com.q8intouch.ecovve.ui.order_checkout

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.model.ShopCart
import com.q8intouch.ecovve.databinding.OrderCheckoutFragmentBinding
import com.q8intouch.ecovve.network.model.EcovveOutletArea
import com.q8intouch.ecovve.network.model.EcovveUser
import com.q8intouch.ecovve.network.model.cart.CartPost
import com.q8intouch.ecovve.ui.cart.order_type.AdressAdapter
import com.q8intouch.ecovve.ui.cart.payment_method_select.PaymentMethodSelectViewModel
import com.q8intouch.ecovve.util.*
import com.q8intouch.ecovve.util.extension.errorResponse
import kotlinx.android.synthetic.main.order_checkout_fragment.*
import org.jetbrains.anko.textColor
import java.text.SimpleDateFormat
import java.util.*

class OrderCheckoutFragment : BaseFragment<PaymentMethodSelectViewModel, OrderCheckoutFragmentBinding>(), View.OnClickListener {
    override fun getLayoutRes(): Int {
        return R.layout.order_checkout_fragment
    }

    var payment = ""
    var id = ""
    var addressID = ""
    var time = ""
    var gift = ArrayList<String>()
    var expected_price = ""
    var coupon = ""
    var outlet_id = ""
    val gift_list = HashMap<String, String>()
    var notes = ""
    var area_id = ""
    var deliveryType = true
    val fields = HashMap<String, String>()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (viewModel.cart.value!!.toList()!=null && viewModel.cart.value!!.toList().size!=0) {
            initViews()
            finishOrder()
            setOrderData()
            setOrderType()
        }
        else {
          AppUtils.showDailog(activity!!,resources.getString(R.string.cart_empty))
        }
    }

    private fun initViews() {
        MainLayout.requestFocus()
        Cash.setOnClickListener(this)
        add_address.setOnClickListener(this)
        Visa.setOnClickListener(this)
        Knet.setOnClickListener(this)
        Master.setOnClickListener(this)
        txtVis.setOnClickListener(this)
        txtMaster.setOnClickListener(this)
        txtCash.setOnClickListener(this)
        txtKnet.setOnClickListener(this)
        btnKnet.setOnClickListener(this)
        btnVisa.setOnClickListener(this)
        btnMaster.setOnClickListener(this)
        btnCash.setOnClickListener(this)
        txtCoupon.setOnClickListener(this)
        txtGiftCard.setOnClickListener(this)

        val total = CartUtils.calculateShopCartTotal(viewModel.cart.value!!.toList()[0].second.items)
        binding.txtSubTotal.text = total.value!!.toString()
        shopInfoTabs.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(v: TabLayout.Tab?) {
                if (v!!.position == 0) {
                    btnSelectOrderTime.visibility = View.GONE
                    layoutDelivey.visibility = View.VISIBLE
                    deliveryType = true
                }
                if (v!!.position == 1) {
                    btnSelectOrderTime.visibility = View.VISIBLE
                    layoutDelivey.visibility = View.GONE
                    deliveryType = false
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(v: TabLayout.Tab?) {
                if (v!!.position == 0) {
                    btnSelectOrderTime.visibility = View.GONE
                    layoutDelivey.visibility = View.VISIBLE
                    deliveryType = true
                }
                if (v.position == 1) {
                    btnSelectOrderTime.visibility = View.VISIBLE
                    layoutDelivey.visibility = View.GONE
                    deliveryType = false
                }
            }

        })
    }

    private fun setOrderData() {
        val sharedPreferences: Shared = Shared(context!!)
        id = activity!!.intent.extras.getString("amount", "")
        notes = "" + sharedPreferences.getValueString(URLs.Notes) + "."
        val items = ArrayList<CartPost.Item>() as ArrayList<CartPost.Item>
        viewModel.cart.value!!.forEach { item: Map.Entry<Int, ShopCart> ->
            item.value.items.value!!.forEach { ad: Map.Entry<CartItem, Int> ->
                items.add(CartPost.Item("" + ad.key.id, "" + ad.value
                        , "" + ad.key.price, "on", "" + ad.key.shopId, ad.key.extra))
            }
        }

        var cartRow = CartPost(items, notes)

        cartRow.item.forEachIndexed { index, odm ->
            outlet_id = odm.outlet_id
            fields.put("items[$index][id]", odm.id)
            fields.put("items[$index][qty]", odm.qty)
            odm.extra.forEachIndexed { i, s ->
                fields.put("items[$index][extra_choices][$i][id]", s)
            }
        }
        gift.forEachIndexed { index, odm ->
            fields.put("choices[$index][id]", gift[index])
        }
    }

    var arrayItems1 = ArrayList<EcovveUser.Data.Addresse>()
    var delArea = ArrayList<EcovveOutletArea.Data.DeliveryArea>()
    @SuppressLint("WrongConstant")
    private fun setOrderType() {
        arrayItems1 = ArrayList<EcovveUser.Data.Addresse>()
        val sharedPreference: Shared = Shared(context!!)
        delArea = ArrayList<EcovveOutletArea.Data.DeliveryArea>()
        var arrayItems = ArrayList<EcovveUser.Data.Addresse>() as List<EcovveUser.Data.Addresse>
        var city_name = ""
        var list_of_dates = ArrayList<String>()
        list_of_dates.add(getString(com.q8intouch.ecovve.R.string.choose_your_time))
        list_of_dates.add(getString(com.q8intouch.ecovve.R.string.one_hour))
        list_of_dates.add(getString(com.q8intouch.ecovve.R.string.two_hour))
        list_of_dates.add(getString(com.q8intouch.ecovve.R.string.three_hours))
        list_of_dates.add(getString(com.q8intouch.ecovve.R.string.four_hours))

        var list_of_dates2 = ArrayList<String>()
        list_of_dates2.add(getString(com.q8intouch.ecovve.R.string.choose_your_outlet))
        val dialog = LoadingDialog.showDialog(context!!)
        viewModel.getOouletArea("" + viewModel.cart.value!!.toList().get(0).second.items.value!!.toList()[0].first.shopId).observe(this, androidx.lifecycle.Observer {
            dialog.dismiss()
            if (it.isSuccess) {
                var temp = -1
                if (it.resource!!.data.deliveryArea.isEmpty()) {
                    temp = it.resource!!.data.area.id!!
                    area_id = ""+ outlet_id
                    if (URLs.lang == "en") {
                        city_name = "" + it.resource!!.data.area.nameEn
                    } else {
                        city_name = "" + it.resource!!.data.area.nameAr
                        // Cname= "" + it.resource!!.data.area.nameAr
                    }
                } else {
//                    delArea.addAll(it.resource!!.data.deliveryArea)
                    it.resource!!.data.deliveryArea.forEachIndexed { index, data ->
                        if (URLs.lang == "en")
                            list_of_dates2.add(data.nameEn!!)
                        else
                            list_of_dates2.add(data.nameAr!!)
                    }
                    area_id = "" + outlet_id
                    city_name = if (URLs.lang == "en")
                        "" + it.resource!!.data.deliveryArea[0].nameEn
                    else
                        "" + it.resource!!.data.deliveryArea[0].nameAr
                }

                val serializedObject = sharedPreference.getValueString("addresses")
                if (serializedObject != null) {
                    val gson = Gson()
                    val type = object : TypeToken<List<EcovveUser.Data.Addresse>>() {
                    }.type
                    arrayItems = gson.fromJson(serializedObject, type)
                }
                delArea.addAll(it.resource!!.data.deliveryArea)
                arrayItems.forEachIndexed { index, addresse ->
                    it.resource!!.data.deliveryArea.forEachIndexed { index2, data ->
                        if (arrayItems[index].areaId!! == data.id) {
                            arrayItems1.add(addresse)
                        }
                    }
                }
                if (arrayItems1.size != 0) {
                    val promoadapter = AdressAdapter(arrayItems1, context!!, object :
                            AdressAdapter.ControlsListeners {
                        override fun click(postion: Int) {
                            addressID = "" + arrayItems1.get(postion).id
                            area_id = "" + arrayItems1[postion].areaId
                            Log.e("chi", area_id)
                        }

                        override fun remove(postion: Int) {
                            val factory = LayoutInflater.from(activity!!)
                            val addToCartDialogView = factory.inflate(R.layout.dailog_delete_item, null)
                            val deleteDialog = AlertDialog.Builder(activity!!).create()
                            deleteDialog.setView(addToCartDialogView)
                            deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            deleteDialog.show()

                            deleteDialog.findViewById<AppCompatButton>(R.id.delete).setOnClickListener {
                                //delete
                                deleteDialog.dismiss()
                            }

                            deleteDialog.findViewById<AppCompatButton>(R.id.btnClose).setOnClickListener {
                                deleteDialog.dismiss()
                            }
                        }
                    })
                    binding.rvDeliveryAddress.layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
                    binding.rvDeliveryAddress.adapter = promoadapter

                    binding.txtMenuItem2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }

                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            if (it.resource!!.data.deliveryArea.isNotEmpty()) {
                                if (binding.txtMenuItem2.selectedItemPosition != 0)
                                    area_id = "" + it.resource!!.data.deliveryArea[binding.txtMenuItem2.selectedItemPosition - 1].id
                            } else {
                                if (binding.txtMenuItem2.selectedItemPosition != 0)
                                    area_id = "" + it.resource!!.data.area.city.id
                            }
                        }
                    }
                }

            } else Log.e("error_area", it.errorResponse().message.toString())
        })

        val aa = ArrayAdapter(context, R.layout.simple_spinner_dropdown_item_ecovve, list_of_dates)
//            // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_delivery)
        // Set Adapter to Spinner
        binding.txtMenuItem.setAdapter(aa)

        binding.txtMenuItem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                time = gettime(binding.txtMenuItem)
            }
        }

        val aa2 = ArrayAdapter(context, com.q8intouch.ecovve.R.layout.simple_spinner_dropdown_item_ecovve, list_of_dates2)
//            // Set layout to use when the list of choices appear
        aa2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_delivery)
        // Set Adapter to Spinner
        binding.txtMenuItem2.setAdapter(aa2)
    }

    private fun gettime(time: Spinner): String {
        val sdf = SimpleDateFormat("yyyy-M-d HH:mm:ss", Locale.US)
        val currentDateandTime = sdf.format(Date())
        val date: Long
        val auctionDate: String
        if (time.selectedItemPosition != 0) {
            date = System.currentTimeMillis() + time.selectedItemPosition * 60 * 60 * 1000

            auctionDate = sdf.format(Date(date))
            return auctionDate
        } else {
            return ""
        }
    }

    @SuppressLint("SetTextI18n")
    fun checkPrice(payment: String, fields: HashMap<String, String>) {
        val sharedPreferences = Shared(context!!)
        Log.e("OOOOOO", id)
        Log.e("OOOOOO", outlet_id)
        Log.e("OOOOOO", ".." + time)
        Log.e("OOOOOO", notes)
        Log.e("OOOOOO", coupon)
        Log.e("OOOOOO", area_id)
        Log.e("OOOOOO1", fields.toString())

        if (time != null) {
            if (coupon != "") {
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
                        , area_id = area_id
                ).observe(this, Observer {
                    if (it.isSuccess) {
                        txtTotalPayment.text = " " + it.resource!!.data.total + getString(R.string.kd)
                        expected_price = "" + it.resource!!.data.total
                        txtValueDiscount.text = ""+it.resource!!.data.discounts.freeCreditDiscount
                        txtDeliveryFee.text = ""+it.resource!!.data.costs.deliveryCost
                        txtSubTotal.text = ""+it.resource!!.data.costs.itemCosts
                    } else {
                        Log.e("OOOOOO", it.errorResponse().message)
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
                        items = fields, area_id = area_id
                ).observe(this, Observer {
                    if (it.isSuccess) {
                        txtTotalPayment.text = " " + it.resource!!.data.total + getString(R.string.kd)
                        expected_price = "" + it.resource!!.data.total
                        txtValueDiscount.text = ""+it.resource!!.data.discounts.freeCreditDiscount
                        txtDeliveryFee.text = ""+it.resource!!.data.costs.deliveryCost
                        txtSubTotal.text = ""+it.resource!!.data.costs.itemCosts
                    } else {
                        Log.e("OOOOOO", it.errorResponse().message)
                    }
                })
            } else {
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
                        , area_id = area_id
                ).observe(this, Observer {
                    if (it.isSuccess) {
                        txtTotalPayment.text = " " + it.resource!!.data.total + getString(R.string.kd)
                        expected_price = "" + it.resource!!.data.total
                        txtValueDiscount.text = ""+it.resource!!.data.discounts.freeCreditDiscount
                        txtDeliveryFee.text = ""+it.resource!!.data.costs.deliveryCost
                        txtSubTotal.text = ""+it.resource!!.data.costs.itemCosts
                    } else {
                        Log.e("OOOOOO", it.errorResponse().message)
                    }
                })
            }
        }
        if (addressID != null) {
            if (coupon != "") {
                viewModel.checkPriceAddressCoupon(user_id = "" + id, outlet_id = outlet_id, delivery_type = "delivery", address_id = addressID,
                        free_credit = "on", notes = notes, assigned_user_id = "" + id, payment_method = "" + payment, coupon_code = coupon, items = fields, area_id = area_id
                ).observe(this, Observer {
                    if (it.isSuccess) {
                        txtTotalPayment.text = " " + it.resource!!.data.total + getString(R.string.kd)
                        expected_price = "" + it.resource!!.data.total
                        txtValueDiscount.text = ""+it.resource!!.data.discounts.freeCreditDiscount
                        txtDeliveryFee.text = ""+it.resource!!.data.costs.deliveryCost
                        txtSubTotal.text = ""+it.resource!!.data.costs.itemCosts
                    } else {
                        Log.e("OOOOOO", it.errorResponse().message)
                    }
                })
            }
            if (gift.size == 0) {
                viewModel.checkPriceAddress(user_id = "" + id, outlet_id = outlet_id, delivery_type = "delivery", address_id = addressID,
                        free_credit = "on", notes = notes, assigned_user_id = "" + id, payment_method = "" + payment, items = fields, area_id = area_id
                ).observe(this, Observer {
                    if (it.isSuccess) {
                        txtTotalPayment.text = " " + it.resource!!.data.total + getString(R.string.kd)
                        expected_price = "" + it.resource!!.data.total
                        txtValueDiscount.text = ""+it.resource!!.data.discounts.freeCreditDiscount
                        txtDeliveryFee.text = ""+it.resource!!.data.costs.deliveryCost
                        txtSubTotal.text = ""+it.resource!!.data.costs.itemCosts
                    } else {
                        Log.e("OOOOOO", it.errorResponse().message)
                    }
                })
            } else {
                viewModel.checkPriceAddressGift(
                        user_id = "" + id, outlet_id = outlet_id, delivery_type = "delivery", address_id = addressID,
                        free_credit = "on", notes = notes, assigned_user_id = "" + id, payment_method = "" + payment, gift_card_id = gift_list, items = fields, area_id = area_id
                ).observe(this, Observer {
                    if (it.isSuccess) {
                        txtTotalPayment.text = " " + it.resource!!.data.total + getString(R.string.kd)
                        expected_price = "" + it.resource!!.data.total
                        txtValueDiscount.text = ""+it.resource!!.data.discounts.freeCreditDiscount
                        txtDeliveryFee.text = ""+it.resource!!.data.costs.deliveryCost
                        txtSubTotal.text = ""+it.resource!!.data.costs.itemCosts
                    } else {
                        Log.e("OOOOOO", it.errorResponse().message)
                    }
                })
            }


        }
    }


    override fun onClick(v: View?) {
        if (v!!.id == R.id.txtCash || v.id == R.id.btnCash || v.id == R.id.Cash) {
            if (((binding.txtMenuItem.selectedItemPosition != 0 /*&& binding.txtMenuItem2.selectedItemPosition != 0*/)
                            || addressID != "") && area_id != "") {
                payment = "cash"
                checkPrice(payment, fields)
                btnCash.background = resources.getDrawable(R.drawable.payment_selected)
                btnVisa.background = resources.getDrawable(R.drawable.payment_selection)
                btnMaster.background = resources.getDrawable(R.drawable.payment_selection)
                btnKnet.background = resources.getDrawable(R.drawable.payment_selection)
                txtCash.textColor = resources.getColor(R.color.colorPrimary)
                txtVis.textColor = resources.getColor(R.color.textGray)
                txtMaster.textColor = resources.getColor(R.color.textGray)
                txtKnet.textColor = resources.getColor(R.color.textGray)
            }
            else {
                AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.no_address_orPickedTime))
            }
        }
        if (v.id == R.id.txtVis || v.id == R.id.btnVisa || v.id == R.id.Visa) {
            if (((binding.txtMenuItem.selectedItemPosition != 0 /*&& binding.txtMenuItem2.selectedItemPosition != 0*/)
                            || addressID != "") && area_id != "") {
                payment = "cc"
                checkPrice(payment, fields)
                btnVisa.background = resources.getDrawable(R.drawable.payment_selected)
                btnCash.background = resources.getDrawable(R.drawable.payment_selection)
                btnMaster.background = resources.getDrawable(R.drawable.payment_selection)
                btnKnet.background = resources.getDrawable(R.drawable.payment_selection)
                txtVis.textColor = resources.getColor(R.color.colorPrimary)
                txtCash.textColor = resources.getColor(R.color.textGray)
                txtMaster.textColor = resources.getColor(R.color.textGray)
                txtKnet.textColor = resources.getColor(R.color.textGray)
            }
            else {
                AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.no_address_orPickedTime))
            }
        }
        if (v.id == R.id.txtMaster || v.id == R.id.btnMaster || v.id == R.id.Master) {
            if (((binding.txtMenuItem.selectedItemPosition != 0 /*&& binding.txtMenuItem2.selectedItemPosition != 0*/)
                            || addressID != "") && area_id != "") {
            payment = "cc"
            checkPrice(payment, fields)
            btnMaster.background = resources.getDrawable(R.drawable.payment_selected)
            btnCash.background = resources.getDrawable(R.drawable.payment_selection)
            btnVisa.background = resources.getDrawable(R.drawable.payment_selection)
            btnKnet.background = resources.getDrawable(R.drawable.payment_selection)
            txtMaster.textColor = resources.getColor(R.color.colorPrimary)
            txtCash.textColor = resources.getColor(R.color.textGray)
            txtVis.textColor = resources.getColor(R.color.textGray)
            txtKnet.textColor = resources.getColor(R.color.textGray)
            }
            else {
                AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.no_address_orPickedTime))
            }
        }
        if (v.id == R.id.txtKnet || v.id == R.id.btnKnet || v.id == R.id.Knet) {
            if (((binding.txtMenuItem.selectedItemPosition != 0 /*&& binding.txtMenuItem2.selectedItemPosition != 0*/)
                            || addressID != "") && area_id != "") {
                payment = "knet"
                checkPrice(payment, fields)
                btnKnet.background = resources.getDrawable(R.drawable.payment_selected)
                btnCash.background = resources.getDrawable(R.drawable.payment_selection)
                btnMaster.background = resources.getDrawable(R.drawable.payment_selection)
                btnVisa.background = resources.getDrawable(R.drawable.payment_selection)
                txtKnet.textColor = resources.getColor(R.color.colorPrimary)
                txtCash.textColor = resources.getColor(R.color.textGray)
                txtMaster.textColor = resources.getColor(R.color.textGray)
                txtVis.textColor = resources.getColor(R.color.textGray)
            }
            else {
                AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.no_address_orPickedTime))
            }
        }
        if (v.id == R.id.txtCoupon) {
            findNavController().navigate(R.id.couponFragment)
        }
        if (v.id == R.id.txtGiftCard) {
            findNavController().navigate(R.id.SelectGiftCardOrderFragment)
        }
        if (v.id == R.id.add_address) {
            val shared = Shared(context!!)
            shared.save("bot", "")
            if (arrayItems1.isEmpty()) {
                shared.setList("delArea", delArea)
                findNavController().navigate(R.id.addAddressFragment)
            } else {
                findNavController().navigate(R.id.addAddressFragment)
            }
        }
    }

    private fun finishOrder() {
        btnCheckout.setOnClickListener {
            if (payment!="") {
                val image = activity!!.findViewById<TextView>(R.id.imageBadge)
                image.visibility = View.GONE
                val dialog = LoadingDialog.showDialog(view!!.context)
                viewModel.cart = MutableLiveData<MutableMap<Int, ShopCart>>().apply {  value = mutableMapOf()}
                if (payment != "") {
                    if (addressID != "") {
                        if (coupon != "") {
                            viewModel.checkoutAddressCoupon(outlet_id,
                                    notes,
                                    "" + id, "" + id, "" + addressID,
                                    "on", "" + payment, fields, "" + expected_price, coupon
                                    , context!!, findNavController(), area_id).observe(this, Observer {
                                dialog.dismiss()
                                if (it.isSuccess) {
                                    if (payment.equals("cash")) {
                                        var bundle = Bundle()
                                        bundle.putString("id", "" + it.resource!!.data.track_id)
                                        bundle.putString("data", it.resource!!.data.status)
                                        findNavController().navigate(R.id.action_OrderCheckoutFragment_to_orderCompleteFragment, bundle)
                                    } else {
                                        var bundle = androidx.core.os.bundleOf("amount" to "" + it.resource!!.data.paymentUrl)
                                        findNavController().navigate(com.q8intouch.ecovve.R.id.PaymentFragment, bundle)
                                    }
                                } else {
                                    Toast.makeText(context!!, it.errorResponse().message, Toast.LENGTH_LONG).show()
                                    Toast.makeText(context!!, it.error!!.localizedMessage, Toast.LENGTH_LONG).show()
                                }
                            })
                        } else
                            if (gift.size == 0) {
                                viewModel.checkAdress(outlet_id, notes, "" + id, "" + id, "" + addressID,
                                        "off", "" + payment, "delivery", fields, "" + expected_price
                                        , area_id).observe(this, Observer {
                                    dialog.dismiss()
                                    if (it.isSuccess) {
                                        if (payment.equals("cash")) {
                                            var bundle = Bundle()
                                            bundle.putString("id", "" + it.resource!!.data.track_id)
                                            bundle.putString("data", it.resource!!.data.status)
                                            findNavController().
                                                    navigate(R.id.action_OrderCheckoutFragment_to_orderCompleteFragment, bundle)
                                        } else {
                                            var bundle = androidx.core.os.bundleOf("amount" to "" + it.resource!!.data.paymentUrl)
                                            findNavController().navigate(com.q8intouch.ecovve.R.id.PaymentFragment, bundle)
                                        }
                                    } else {
                                        Toast.makeText(context!!, it.errorResponse().message, Toast.LENGTH_LONG).show()
                                        Toast.makeText(context!!, it.error!!.localizedMessage, Toast.LENGTH_LONG).show()
                                    }
                                })
                            } else {
                                viewModel.checkoutAddressGiftCard(outlet_id, notes, "" + id, "" + id,
                                        "" + addressID, "off", "" + payment, "delivery", fields
                                        , "" + expected_price, gift_list, area_id).observe(this, Observer {
                                    dialog.dismiss()
                                    if (it.isSuccess) {
                                        if (payment.equals("cash")) {
                                            var bundle = Bundle()
                                            bundle.putString("id", "" + it.resource!!.data.track_id)
                                            bundle.putString("data", it.resource!!.data.status)
                                            findNavController().navigate(R.id.action_OrderCheckoutFragment_to_orderCompleteFragment, bundle)
                                        } else {
                                            var bundle = androidx.core.os.bundleOf("amount" to "" + it.resource!!.data.paymentUrl)
                                            findNavController().navigate(com.q8intouch.ecovve.R.id.PaymentFragment, bundle)
                                        }
                                    } else {
                                        Toast.makeText(context!!, it.errorResponse().message, Toast.LENGTH_LONG).show()
                                        Toast.makeText(context!!, it.error!!.localizedMessage, Toast.LENGTH_LONG).show()
                                    }
                                })
                            }

                    } else {
                        if (time != "") {
                            if (coupon != "") {
                                viewModel.checkpickGiftCoupon(outlet_id, notes, "" + id, "" + id,
                                        "" + time, "off", "" + payment, "pickup", fields
                                        , expected_price, coupon, area_id).observe(this, Observer {
                                    dialog.dismiss()
                                    if (it.isSuccess) {
                                        if (payment.equals("cash")) {
                                            var bundle = Bundle()
                                            bundle.putString("id", "" + it.resource!!.data.track_id)
                                            bundle.putString("data", it.resource!!.data.status)
                                            findNavController().navigate(R.id.action_OrderCheckoutFragment_to_orderCompleteFragment, bundle)
                                        } else {
                                            var bundle = androidx.core.os.bundleOf("amount" to "" + it.resource!!.data.paymentUrl)
                                            findNavController().navigate(com.q8intouch.ecovve.R.id.PaymentFragment, bundle)
                                        }
                                    } else {
                                        Toast.makeText(context!!, it.errorResponse().message, Toast.LENGTH_LONG).show()
                                        Toast.makeText(context!!, it.error!!.localizedMessage, Toast.LENGTH_LONG).show()
                                    }
                                })
                            } else
                                if (gift.size == 0) {
                                    viewModel.checkpick(outlet_id, notes, "" + id, "" + id,
                                            "" + time, "off", payment, "pickup", fields
                                            , expected_price, area_id).observe(this, Observer {
                                        dialog.dismiss()
                                        if (it.isSuccess) {
                                            if (payment.equals("cash")) {
                                                var bundle = Bundle()
                                                bundle.putString("id", "" + it.resource!!.data.track_id)
                                                bundle.putString("data", it.resource!!.data.status)
                                                findNavController().navigate(R.id.action_OrderCheckoutFragment_to_orderCompleteFragment, bundle)
                                            } else {
                                                var bundle = androidx.core.os.bundleOf("amount" to "" + it.resource!!.data.paymentUrl)
                                                findNavController().navigate(com.q8intouch.ecovve.R.id.PaymentFragment, bundle)
                                            }
                                        } else {
                                            Toast.makeText(context!!, it.errorResponse().message, Toast.LENGTH_LONG).show()
                                            Toast.makeText(context!!, it.error!!.localizedMessage, Toast.LENGTH_LONG).show()
                                        }
                                    })
                                } else {
                                    viewModel.checkpickGift(outlet_id, notes, "" + id,
                                            "" + id, "" + time, "off", "" + payment, "pickup", fields
                                            , expected_price, gift_list, area_id).observe(this, Observer {
                                        dialog.dismiss()
                                        if (it.isSuccess) {
                                            if (payment.equals("cash")) {
                                                var bundle = Bundle()
                                                bundle.putString("id", "" + it.resource!!.data.track_id)
                                                bundle.putString("data", it.resource!!.data.status)
                                                findNavController().navigate(R.id.action_OrderCheckoutFragment_to_orderCompleteFragment, bundle)
                                            } else {
                                                var bundle = androidx.core.os.bundleOf("amount" to "" + it.resource!!.data.paymentUrl)
                                                findNavController().navigate(com.q8intouch.ecovve.R.id.PaymentFragment, bundle)
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
                    dialog.dismiss()
                    AppUtils.showDailog(activity!!, "" + resources.getString(com.q8intouch.ecovve.R.string.no_payment))
                }
            } else {
                AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.choose_payment_option))
            }
        }

    }
}
