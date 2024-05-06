package com.q8intouch.ecovve.ui.social

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.places.Places
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.malinskiy.superrecyclerview.SuperRecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.model.ShopCart
import com.q8intouch.ecovve.databinding.FragmentChatBotBinding
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.network.model.cart.CartPost
import com.q8intouch.ecovve.ui.home.ChoicesAdapter
import com.q8intouch.ecovve.ui.home.ExtraAdapter
import com.q8intouch.ecovve.ui.home.ExtraModelItems
import com.q8intouch.ecovve.ui.top_new_shops.BrandOutletsAdapter
import com.q8intouch.ecovve.util.AppUtils
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.URLs
import com.q8intouch.ecovve.util.extension.errorResponse
import kotlinx.android.synthetic.main.dailog_add_to_cart.*
import kotlinx.android.synthetic.main.fragment_chat_bot.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textColor
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ChatBotFragment : BaseFragment<ChatBotViewModel, FragmentChatBotBinding>(), View.OnClickListener {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_chat_bot
    }
    override fun onClick(v: View?) {
        if (v!!.id == R.id.txtCash || v.id == R.id.btnCash || v.id == R.id.Cash) {
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
        if (v.id == R.id.txtVis || v.id == R.id.btnVisa || v.id == R.id.Visa) {
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
        if (v.id == R.id.txtMaster || v.id == R.id.btnMaster || v.id == R.id.Master) {
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
        if (v.id == R.id.txtKnet || v.id == R.id.btnKnet || v.id == R.id.Knet) {
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
    }

    var extraList = ArrayList<String>()
    var extraModel = ArrayList<ExtraModelItems>()
    var isNotMatch = ArrayList<String>()
    var outlet_page = 0
    var outletData = ArrayList<EcovveTopCafes.Data.Outlets?>()
    var payment = ""
    var id = ""
    var addressID = ""
    var time = ""
    var gift = java.util.ArrayList<String>()
    var expected_price = ""
    var coupon = ""
    var outlet_id = ""
    val gift_list = HashMap<String, String>()
    var notes = ""
    var area_id = ""
    var deliveryType = true
    val fields = HashMap<String, String>()
    lateinit var adapter: BrandOutletsAdapter



    private val PLACE_PICKER_REQUEST = 1
    private var mGoogleApiClient: GoogleApiClient? = null

    companion object {
        fun newInstance() = ChatBotFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun intitFinishOrder() {
        ScrollViewLayout.getViewTreeObserver().addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                var scrollViewHeight = ScrollViewLayout.getHeight();
                if (scrollViewHeight > 0) {
                    ScrollViewLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    var lastView = ScrollViewLayout.getChildAt(ScrollViewLayout.getChildCount() - 1);
                    var lastViewBottom = lastView.getBottom() + ScrollViewLayout.getPaddingBottom();
                    var deltaScrollY = lastViewBottom - scrollViewHeight - ScrollViewLayout.getScrollY();
                    /* If you want to see the scroll animation, call this. */
                    ScrollViewLayout.smoothScrollBy(0, deltaScrollY);
                    /* If you don't want, call this. */
                    ScrollViewLayout.scrollBy(0, deltaScrollY); }

            }
        });
        startAgain.onClick {
            getBrand()
            selectOutlet.visibility = View.GONE
            order_type.visibility = View.GONE
            layoutFinishOrder.visibility = View.GONE
            newAddress.visibility = View.GONE
            chooseTimeLayout.visibility = View.GONE
            paymentLayout.visibility = View.GONE
            aferFinish.visibility = View.GONE
            promptFinishOrder.visibility = View.GONE
            btnSelectOrderTime2.visibility = View.GONE
            selectedTime.visibility = View.GONE
            paymentLayout.visibility = View.GONE
            prompt_payment.visibility = View.GONE
            ScrollViewLayout.fullScroll(View.FOCUS_UP);
        }


        chpFinishOrder.onClick {
            promptFinishOrder.visibility = View.VISIBLE
            order_type.visibility = View.VISIBLE
            setOrderType()
            ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
        }

        order_type.findViewById<AppCompatButton>(R.id.pickup).onClick {
            chooseTimeLayout.visibility = View.VISIBLE
            btnSelectOrderTime2.visibility = View.VISIBLE
            ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
        }
        order_type.findViewById<AppCompatButton>(R.id.delivery).onClick {
            promptAddress.visibility = View.VISIBLE
            AddressList.visibility = View.VISIBLE
            ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
        }

        newAddress.onClick {
            val shared = Shared(context!!)
            shared.save("bot", "chatBot")
            if (arrayItems1.isEmpty()) {
                shared.setList("delArea", delArea)
                findNavController().navigate(R.id.addAddressFragment)
            } else {
                findNavController().navigate(R.id.addAddressFragment)
            }
        }
        chooseTimeLayout.findViewById<Chip>(R.id.hour1).onClick {
            showPaymentLayout(1)
        }
        chooseTimeLayout.findViewById<Chip>(R.id.hour2).onClick {
            showPaymentLayout(2)
        }
        chooseTimeLayout.findViewById<Chip>(R.id.hour3).onClick {
            showPaymentLayout(3)

        }
        chooseTimeLayout.findViewById<Chip>(R.id.hour4).onClick {
            showPaymentLayout(4)
        }

        paymentLayout.setOnClickListener(this)
    }

    private fun showPaymentLayout(num: Int) {
        selectedTime.visibility = View.VISIBLE
        gettime(num)
        paymentLayout.visibility = View.VISIBLE
        prompt_payment.visibility = View.VISIBLE

        ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
    }

    private fun gettime(selectedTime: Int) {
        val sdf = SimpleDateFormat("yyyy-M-d HH:mm:ss", Locale.US)
        val currentDateandTime = sdf.format(Date())
        val date: Long
        val auctionDate: String

        date = System.currentTimeMillis() + selectedTime * 60 * 60 * 1000
        auctionDate = sdf.format(Date(date))
        time = auctionDate
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ScrollViewLayout.post { ScrollViewLayout.fullScroll(NestedScrollView.FOCUS_DOWN); };

        id = activity!!.intent.extras.getString("amount", "")
        val tv_click_me = binding.root.findViewById(R.id.chipSendLocation) as Chip
        getBrand()
        tv_click_me.setOnClickListener {
            getLocation()
//            val builder = PlacePicker.IntentBuilder()
//            startActivityForResult(builder.build(context as Activity?), PLACE_PICKER_REQUEST)
        }

        intitFinishOrder()
        Cash.setOnClickListener(this)
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

//        val total = CartUtils.calculateShopCartTotal(viewModel.cart.value!!.toList()[0].second.items)
        finishOrder()
    }

    private fun getBrand() {
        viewModel.topCafes(1).observe(this@ChatBotFragment, Observer {
            ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
            if (it.isSuccess) {
                var outletsOdapter = CafeAdapter(it.resource!!.data, context!!, object :
                        CafeAdapter.ControlsListeners {
                    @SuppressLint("WrongConstant")
                    override fun click(postion: Int) {
                        ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
                        outletData = ArrayList<EcovveTopCafes.Data.Outlets?>()

                        if (it.resource!!.data[postion].outlets != null && it.resource!!.data[postion].outlets!!.size != 0) {
                            it.resource!!.data[postion].outlets!!.forEachIndexed { index, data ->
                                outletData.add(data)
                            }

                            val factory = LayoutInflater.from(activity!!)
                            val addToCartDialogView = factory.inflate(R.layout.select_brand_outlet, null)
                            val deleteDialog = android.app.AlertDialog.Builder(activity!!).create()
                            deleteDialog.setView(addToCartDialogView)
                            deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            deleteDialog.window!!.setBackgroundDrawableResource(R.drawable.tab_layout_ui);
                            deleteDialog.window!!.setGravity(Gravity.BOTTOM);
                            deleteDialog.window!!.attributes.windowAnimations = R.style.DialogAnimation2; //style id

                            deleteDialog.show()
                            deleteDialog.findViewById<TextView>(R.id.caty_name).text = resources.getString(R.string.select_outlet)

                            deleteDialog.findViewById<ImageView>(R.id.cancel).setOnClickListener {
                                //delete
                                deleteDialog.dismiss()
                            }
                            adapter = BrandOutletsAdapter(outletData, context!!, object :
                                    BrandOutletsAdapter.ControlsListeners {
                                override fun click(position: Int) {
                                    allOutlets(outletData[position]!!.id!!, it.resource!!.data[postion]!!)
                                    ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
                                    deleteDialog.dismiss()
                                }
                            })
                            val layoutmanager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
                            val list = deleteDialog.findViewById<SuperRecyclerView>(R.id.sub_caty)
                            list.setLayoutManager(layoutmanager)
                            list.adapter = adapter
                            ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
                        } else {
                            //                            var bundle = bundleOf("amount" to "" + it.resource!!.data[postion]!!.id)
                            //                            findNavController().navigate(R.id.shopInfoFragment, bundle)
                            allOutlets(it.resource!!.data[postion]!!.id!!, it.resource!!.data[postion]!!)
                        }
                    }
                })
                outlets.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
                outlets.adapter = outletsOdapter
            } else {
                Log.e("testShougy", it.errorResponse().message)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.chat, menu)
        super.onCreateOptionsMenu(menu!!, inflater)
    }

    private fun buildGoogleApiClient() {
        mGoogleApiClient = context?.let {
            GoogleApiClient.Builder(it)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .enableAutoManage(context as FragmentActivity, null)
                    .build()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {
            val place = PlacePicker.getPlace(data, context)
            val toastMsg = String.format("Place: %s", place.name)
            //  tvPlace.text = place!!.name.toString().plus("\n".plus(place!!.address).plus("\n".plus(place!!.phoneNumber)))
            Toast.makeText(context, toastMsg, Toast.LENGTH_LONG).show()
        }
    }


    private fun showAlert() {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Enable Location")
                .setMessage("Locations Settings is set to 'Off'.\nEnable Location to use this app")
                .setPositiveButton("Location Settings") { paramDialogInterface, paramInt ->
                    val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(myIntent)
                }
                .setNegativeButton("Cancel") { paramDialogInterface, paramInt -> }
        dialog.show()
    }

    lateinit var outletsOdapter: itemsAdapter
    fun getLocation() {

        var locationManager = context!!.getSystemService(LOCATION_SERVICE) as LocationManager?
        var locationListener = object : LocationListener {
            @SuppressLint("WrongConstant")
            override fun onLocationChanged(location: Location?) {
                var latitute = location!!.latitude
                var longitute = location!!.longitude

                getBrand()
                Log.e("testShougy", "Latitute: $latitute ; Longitute: $longitute")

            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
                Log.e("testShougy", "D")

            }

        }

    }

    private var plusQuantity: FloatingActionButton? = null
    private var minusQuantity: FloatingActionButton? = null
    private var txtTotal: TextView? = null
    private var units: TextView? = null

    @SuppressLint("WrongConstant")
    private fun showDialogAddBrand(resource: EcovveCafeInfoBrand.Data.Items.DataItems, brand: EcovveTopCafes.Data
                                   , brand_id: Int?, postion: Int, adapter: ExtraAdapter) {
        val factory = LayoutInflater.from(context!!)
        val addToCartDialogView = factory.inflate(R.layout.dailog_add_to_cart, null)
        val addToCartDialog = android.app.AlertDialog.Builder(context!!).create()
        addToCartDialog.setView(addToCartDialogView)
        addToCartDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addToCartDialog.show()
        addToCartDialog.findViewById<RecyclerView>(R.id.extraList).layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        addToCartDialog.findViewById<RecyclerView>(R.id.extraList).adapter = adapter
        addToCartDialog.txtItemPrice.text = "   " + resource!!.price + "       "
        addToCartDialog.txtItemName.setText("" + resource!!.name)
        minusQuantity = addToCartDialog.findViewById(R.id.banMinusQuantity)
        plusQuantity = addToCartDialog.findViewById(R.id.banPlusQuantity)
        txtTotal = addToCartDialog.findViewById(R.id.txtTotal)
        txtTotal!!.text = "" + resource!!.price
        units = addToCartDialog.findViewById(R.id.units)

        plusQuantity?.setOnClickListener {
            addToCartplus(txtTotal!!, units!!, resource.price!!);
        }

        addToCartDialog.btnClose?.onClick {
            addToCartDialog.dismiss()
        }

        minusQuantity?.setOnClickListener {
            removeFromCart(txtTotal!!, units!!, resource.price!!);
        }

        isNotMatch = ArrayList()
        addToCartDialog.findViewById<AppCompatButton>(R.id.btnAddToCart).onClick {
            isNotMatch = ArrayList()
            extraModel.forEachIndexed { index, extraModel ->
                if (extraModel.is_required == 1) {
                    var isMatch = 0
                    extraModel.choices!!.forEach { onbjext ->
                        extraList.forEach { s2 ->
                            if (s2 == "" + onbjext!!.id) {
                                ++isMatch
                            }
                        }
                    }
                    if (isMatch == 0)
                        isNotMatch.add("1")
                }
            }
            if (isNotMatch.size != 0) {
                AppUtils.showDailog(activity!!, resources.getString(R.string.select_required_from_choices))
            } else {
                var cartItem = CartItem(
                        resource.id!!,
                        brand_id!!,
                        resource.name!!,
                        resource.image!![0]!!,
                        resource.price!!, extraList)

//                            viewModel.addItem(resource.brandId,cartItem!!
//                                ,NumberFormat.getInstance().parse(units!!.text.toString()).toInt())

                if (viewModel.cart.value!!.isEmpty()) {
                    layoutFinishOrder.visibility = View.VISIBLE
                    ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
                    viewModel.addItem(
                            brand_id, cartItem
                            , NumberFormat.getInstance().parse(units!!.text.toString()).toInt()
                            , "", brand.name!!
                            , activity!!, extraList)
                } else {
                    var shhpid = 0
                    if (viewModel.cart.value!!.isNotEmpty()) {
                        viewModel.cart.value!!.forEach { item: Map.Entry<Int, ShopCart> ->
                            shhpid = item.value.id
                        }
                        if (shhpid != brand_id) {
                            AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.cartnotmatch))

                        } else {
                            layoutFinishOrder.visibility = View.VISIBLE
                            ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
                            viewModel.addItem(
                                    brand_id, cartItem
                                    , NumberFormat.getInstance().parse(units!!.text.toString()).toInt()
                                    , "", brand.name!!, activity!!, extraList)
                        }
                    }
                }
                addToCartDialog.dismiss()
            }
            ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
        }
    }

    @SuppressLint("WrongConstant")
    private fun showDialogAdd(resource: EcovveCafeInfoBrand.Data.Items.DataItems, brand: EcovveALLOUTLETS.Data
                              , brand_id: Int?, postion: Int, adapter: ExtraAdapter) {
        val factory = LayoutInflater.from(context!!)
        val addToCartDialogView = factory.inflate(R.layout.dailog_add_to_cart, null)
        val addToCartDialog = android.app.AlertDialog.Builder(context!!).create()
        addToCartDialog.setView(addToCartDialogView)
        addToCartDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addToCartDialog.show()
        addToCartDialog.findViewById<RecyclerView>(R.id.extraList).layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        addToCartDialog.findViewById<RecyclerView>(R.id.extraList).adapter = adapter
        addToCartDialog.txtItemPrice.text = "   " + resource!!.price + "       "
        addToCartDialog.txtItemName.setText("" + resource!!.name)
        minusQuantity = addToCartDialog.findViewById(R.id.banMinusQuantity)
        plusQuantity = addToCartDialog.findViewById(R.id.banPlusQuantity)
        txtTotal = addToCartDialog.findViewById(R.id.txtTotal)
        txtTotal!!.text = "" + resource!!.price
        units = addToCartDialog.findViewById(R.id.units)

        plusQuantity?.setOnClickListener {
            addToCartplus(txtTotal!!, units!!, resource.price!!);
        }

        addToCartDialog.btnClose?.onClick {
            addToCartDialog.dismiss()
        }

        minusQuantity?.setOnClickListener {
            removeFromCart(txtTotal!!, units!!, resource.price!!);
        }

        isNotMatch = ArrayList()
        addToCartDialog.findViewById<AppCompatButton>(R.id.btnAddToCart).onClick {
            isNotMatch = ArrayList()
            extraModel.forEachIndexed { index, extraModel ->
                if (extraModel.is_required == 1) {
                    var isMatch = 0
                    extraModel.choices!!.forEach { onbjext ->
                        extraList.forEach { s2 ->
                            if (s2 == "" + onbjext!!.id) {
                                ++isMatch
                            }
                        }
                    }
                    if (isMatch == 0)
                        isNotMatch.add("1")
                }
            }
            if (isNotMatch.size != 0) {
                AppUtils.showDailog(activity!!, resources.getString(R.string.select_required_from_choices))
            } else {
                var cartItem = CartItem(resource.id!!, brand_id!!, resource.name!!, resource.image!![0]!!, resource.price!!, extraList)

                if (viewModel.cart.value!!.isEmpty()) {
                    layoutFinishOrder.visibility = View.VISIBLE
                    ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
                    viewModel.addItem(brand_id, cartItem, NumberFormat.getInstance().parse(units!!.text.toString()).toInt(), "", brand.name!!, activity!!, extraList)
                } else {
                    var shhpid = 0
                    if (viewModel.cart.value!!.isNotEmpty()) {
                        viewModel.cart.value!!.forEach { item: Map.Entry<Int, ShopCart> ->
                            shhpid = item.value.id
                        }
                        if (shhpid != brand_id) {
                            AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.cartnotmatch))

                        } else {
                            layoutFinishOrder.visibility = View.VISIBLE
                            ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
                            viewModel.addItem(brand_id, cartItem, NumberFormat.getInstance().parse(units!!.text.toString()).toInt(), "", brand.name!!, activity!!, extraList)
                        }
                    }
                }
                addToCartDialog.dismiss()
            }
        }
    }

    val myFormatter = DecimalFormat("############")
    private fun addToCartplus(total: TextView, units: TextView, price: Float) {

        var doubletotal = NumberFormat.getInstance().parse(total.text.toString()).toFloat()
//        var unitPrice = NumberFormat.getInstance().parse(price).toDouble()
        doubletotal = doubletotal + price
        total.setText("" + myFormatter.format(doubletotal))
        var unitsadd = Integer.valueOf(units.text.toString())
        unitsadd += 1
        units.setText("$unitsadd")
    }

    private fun removeFromCart(total: TextView, units: TextView, price: Float) {
//        var unitPrice = NumberFormat.getInstance().parse(price).toDouble()
        var unitsnum = Integer.valueOf(units.text.toString())
        if (unitsnum != 0) {
            var doubletotal = NumberFormat.getInstance().parse(total.text.toString()).toFloat()
            doubletotal = doubletotal - price
            total.setText("" + myFormatter.format(doubletotal))

            unitsnum -= 1
            units.setText("$unitsnum")
        }
    }

    var arrayItems1 = java.util.ArrayList<EcovveUser.Data.Addresse>()
    var delArea = java.util.ArrayList<EcovveOutletArea.Data.DeliveryArea>()
    @SuppressLint("WrongConstant")
    private fun setOrderType() {
        arrayItems1 = java.util.ArrayList<EcovveUser.Data.Addresse>()
        val sharedPreference: Shared = Shared(context!!)
        delArea = java.util.ArrayList<EcovveOutletArea.Data.DeliveryArea>()
        var arrayItems = java.util.ArrayList<EcovveUser.Data.Addresse>() as List<EcovveUser.Data.Addresse>
        var city_name = ""

        var list_of_dates2 = java.util.ArrayList<String>()
        list_of_dates2.add(getString(com.q8intouch.ecovve.R.string.choose_your_outlet))
        val dialog = LoadingDialog.showDialog(context!!)
        viewModel.getOouletArea("" + viewModel.cart.value!!.toList().get(0).second.items.value!!.toList()[0].first.shopId)
                .observe(this, androidx.lifecycle.Observer {
                    dialog.dismiss()
                    ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
                    if (it.isSuccess) {
                        var temp = -1
                        if (it.resource!!.data.deliveryArea.isEmpty()) {
                            temp = it.resource!!.data.area.id!!
                            area_id = "" + outlet_id
                            if (URLs.lang == "en") {
                                city_name = "" + it.resource!!.data.area.nameEn
                            } else {
                                city_name = "" + it.resource!!.data.area.nameAr
                            }
                        } else {
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
                            val promoadapter = AdressAdapterBot(arrayItems1, context!!, object :
                                    AdressAdapterBot.ControlsListeners {
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
                            AddressList.layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
                            AddressList.adapter = promoadapter
                            ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
                            txtMenuItem2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                    chooseTimeLayout.visibility = View.VISIBLE
                                    choose_time_prmp.visibility = View.VISIBLE
                                    ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
                                    if (it.resource!!.data.deliveryArea.isNotEmpty()) {
                                        if (txtMenuItem2.selectedItemPosition != 0)
                                            area_id = "" + it.resource!!.data.deliveryArea[txtMenuItem2.selectedItemPosition - 1].id
                                    } else {
                                        if (txtMenuItem2.selectedItemPosition != 0)
                                            area_id = "" + it.resource!!.data.area.id
                                    }
                                }
                            }
                        }

                    } else Log.e("error_area", it.errorResponse().message.toString())
                    ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
                })

        val aa2 = ArrayAdapter(context, R.layout.simple_spinner_dropdown_item_ecovve, list_of_dates2)
//            // Set layout to use when the list of choices appear
        aa2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_delivery)
        // Set Adapter to Spinner
        ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
        txtMenuItem2.setAdapter(aa2)
    }

    @SuppressLint("SetTextI18n")
    fun checkPrice(payment: String, fields: HashMap<String, String>) {
        setOrderData()
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

    private fun setOrderData() {
        val sharedPreferences: Shared = Shared(context!!)
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

    private fun finishOrder() {
        btnCheckout.setOnClickListener {
            if (payment != "") {
                aferFinish.visibility = View.VISIBLE
                order_completed.visibility = View.VISIBLE
                ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
                val dialog = LoadingDialog.showDialog(view!!.context)
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
                                        findNavController().navigate(R.id.orderCompleteFragment, bundle)
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
                                            findNavController().navigate(R.id.orderCompleteFragment, bundle)
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
                                            findNavController().navigate(R.id.orderCompleteFragment, bundle)
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
                                            findNavController().navigate(R.id.orderCompleteFragment, bundle)
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
                                                findNavController().navigate(R.id.orderCompleteFragment, bundle)
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
                                                findNavController().navigate(R.id.orderCompleteFragment, bundle)
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

    @SuppressLint("WrongConstant")
    private fun allOutlets(brandId: Int, data: EcovveTopCafes.Data) {
        val brand_id = brandId
        var brand = data
        viewModel.cafeMenu("" + brandId).observe(this@ChatBotFragment, Observer {
            if (it.isSuccess) {
                var itemsOdapter = itemsAdapter(it.resource!!.data!!.items!!.data!!, context!!,
                        object : itemsAdapter.ControlsListeners {
                            override fun click(postion: Int) {
                                isNotMatch = ArrayList<String>()
                                extraList = ArrayList()
                                extraModel = ArrayList()
                                var resource = it.resource!!.data!!.items!!.data!![postion]!!
                                val dailog = LoadingDialog.showDialog(context!!)
                                viewModel.showItem("" + resource!!.id).observe(
                                        this@ChatBotFragment, Observer {
                                    dailog.dismiss()
                                    if (it.isSuccess) {
                                        val adapter = ExtraAdapter(it.resource!!.data!!.extras, context!!,
                                                object : ExtraAdapter.ControlsListeners {
                                                    @SuppressLint("WrongConstant")
                                                    override fun click(id: Int, choices: RecyclerView) {
                                                        viewModel.showExtra("" + id).observe(this@ChatBotFragment, Observer {
                                                            if (it.isSuccess) {
                                                                val Extra = ExtraModelItems(id, it.resource!!.data!!.choices, it.resource!!.data!!.is_required!!)
                                                                extraModel.add(Extra)
                                                                var choiceAdapter = ChoicesAdapter(it.resource!!.data!!.choices!!, context!!,
                                                                        object : ChoicesAdapter.ControlsListeners {
                                                                            override fun click(id: Int) {
                                                                                extraList.add("" + id)
                                                                            }

                                                                            override fun remove(id: Int) {
                                                                                extraList.forEachIndexed { index, s ->
                                                                                    if (s == "" + id)
                                                                                        extraList.remove(s)
                                                                                }
                                                                            }
                                                                        })
                                                                choices.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
                                                                choices.adapter = choiceAdapter
                                                            } else {
                                                                Log.e("error", it.error!!.localizedMessage)
                                                            }
                                                        })
                                                    }
                                                })

                                        showDialogAddBrand(resource, brand, brand_id, postion, adapter)
                                    } else {
                                        Log.e("error", it.error!!.localizedMessage)
                                    }
                                })
                            }
                        })
                items.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
                items.adapter = itemsOdapter
                ScrollViewLayout.fullScroll(View.FOCUS_DOWN);
            }
        })

    }
}



