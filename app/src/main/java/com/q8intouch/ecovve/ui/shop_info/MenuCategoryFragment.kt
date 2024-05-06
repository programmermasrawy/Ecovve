package com.q8intouch.ecovve.ui.shop_info

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.malinskiy.superrecyclerview.OnMoreListener
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.model.ShopCart
import com.q8intouch.ecovve.databinding.FragmentMenuCatagoryBinding
import com.q8intouch.ecovve.network.model.MenuResponse
import com.q8intouch.ecovve.ui.home.ChoicesAdapter
import com.q8intouch.ecovve.ui.home.ExtraAdapter
import com.q8intouch.ecovve.ui.home.ExtraModelItems
import com.q8intouch.ecovve.util.AppUtils
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.URLs
import com.q8intouch.ecovve.util.extension.errorResponse
import kotlinx.android.synthetic.main.dailog_add_to_cart.*
import kotlinx.android.synthetic.main.fragment_menu_catagory.*
import org.jetbrains.anko.onClick
import java.text.DecimalFormat
import java.text.NumberFormat

class MenuCategoryFragment : BaseFragment<MenuCategoryViewModel, FragmentMenuCatagoryBinding>() {
    private var plusQuantity: FloatingActionButton? = null
    private var minusQuantity: FloatingActionButton? = null
    private var txtTotal: TextView? = null
    private var units: TextView? = null
    var extraList = ArrayList<String>()
    var extraModel = ArrayList<ExtraModelItems>()
    override fun getLayoutRes(): Int {
        return R.layout.fragment_menu_catagory;
    }

    companion object {
        fun newInstance() = MenuCategoryFragment()
    }

    override fun onStart() {
        findNavController().currentDestination!!.label = arguments!!.getString("title")
        super.onStart()
    }

    var cartItem: CartItem? = null
    var page = 0
    private var add = 0;
    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findNavController().currentDestination!!.label = arguments!!.getString("title")
        page = 0
        loadmenuItems()
    }

    lateinit var menuAdapter: MenuDetailsAdapter
    var data = ArrayList<MenuResponse.Data.Items.DataItems>()
    @SuppressLint("WrongConstant")
    private fun loadmenuItems() {
        var id = arguments!!.getString("amount")
        viewModel.cafeMenu(id, ++page).observe(this, Observer {
            if (it.isSuccess) {
                if (page == 1)
                    data.addAll(it.resource!!.data.items.data)
                else {
                    data.addAll(it.resource!!.data.items.data)
                    menuAdapter.notifyDataSetChanged()
                }
                if (page == 1) {
                    if (it.resource!!.data.items.data.isNotEmpty()) {
                        isNotMatch = ArrayList<String>()
                        extraList = ArrayList()
                        extraModel = ArrayList()
                        menuAdapter = MenuDetailsAdapter(it.resource!!.data.items.data, context!!, object :
                                MenuDetailsAdapter.ControlsListeners {
                            override fun click(postion: Int) {
                                isNotMatch = ArrayList<String>()
                                extraList = ArrayList()
                                extraModel = ArrayList()
                                var brand_id = it.resource!!.data.brandId
                                var resource = it.resource!!.data.items.data
                                var brand = it.resource!!.data.brand
                                val dialog = LoadingDialog.showDialog(context!!)
                                viewModel.showItem("" + resource[postion].id!!).observe(this@MenuCategoryFragment, Observer {
                                    dialog.dismiss()
                                    if (it.isSuccess) {
                                        val adapter = ExtraAdapter(it.resource!!.data!!.extras, context!!,
                                                object : ExtraAdapter.ControlsListeners {
                                                    @SuppressLint("WrongConstant")
                                                    override fun click(id: Int, choices: RecyclerView) {
                                                        viewModel.showExtra("" + id).observe(this@MenuCategoryFragment, Observer {
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

                                        showDialogAdd(resource, brand, brand_id, postion, adapter)
                                    } else {
                                        Log.e("error", it.error!!.localizedMessage)
                                    }
                                })
                            }
                        }, it.resource!!.data)
                        rvMenuItems.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
                        rvMenuItems.adapter = menuAdapter
                    } else {
                        no_items.text = resources.getString(R.string.list_empty)
                        rvMenuItems.visibility = View.GONE
                    }
                }
                rvMenuItems.setupMoreListener(OnMoreListener { numberOfItems, numberBeforeMore, currentItemPos ->
                    // Fetch more from Api or DB
                    loadmenuItems()
                }, 1)
            } else {
                Log.e("ett0.", it.errorResponse().message.toString())
            }
        })
    }

    var isNotMatch = ArrayList<String>()

    @SuppressLint("WrongConstant")
    private fun showDialogAdd(resource: List<MenuResponse.Data.Items.DataItems>, brand: MenuResponse.Data.Brand
                              , brand_id: Int?, postion: Int, adapter: ExtraAdapter) {
        val factory = LayoutInflater.from(context!!)
        val addToCartDialogView = factory.inflate(R.layout.dailog_add_to_cart, null)
        val addToCartDialog = android.app.AlertDialog.Builder(context!!).create()
        addToCartDialog.setView(addToCartDialogView)
        addToCartDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addToCartDialog.show()
        addToCartDialog.findViewById<RecyclerView>(R.id.extraList).layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        addToCartDialog.findViewById<RecyclerView>(R.id.extraList).adapter = adapter
        addToCartDialog.txtItemPrice.text = "   " + resource!!.get(postion).price + "       "
        addToCartDialog.txtItemName.setText("" + resource!!.get(postion).name)
        minusQuantity = addToCartDialog.findViewById(R.id.banMinusQuantity)
        plusQuantity = addToCartDialog.findViewById(R.id.banPlusQuantity)
        txtTotal = addToCartDialog.findViewById(R.id.txtTotal)
        txtTotal!!.text = "" + resource!!.get(postion).price
        units = addToCartDialog.findViewById(R.id.units)
        if (resource.get(postion).image.size != 0)
            if (!resource.get(postion).image.get(0).toString().contains("http"))
                Glide.with(context!!).load(URLs.IMAGES_URL + resource.get(postion).image.get(0)).into(addToCartDialog.findViewById(R.id.imageView3))
            else
                Glide.with(context!!).load(resource.get(postion).image.get(0)).into(addToCartDialog.findViewById(R.id.imageView3))

        plusQuantity?.setOnClickListener {
            addToCartplus(txtTotal!!, units!!, resource.get(postion).price!!);
        }

        addToCartDialog.btnClose?.onClick {
            addToCartDialog.dismiss()
        }

        minusQuantity?.setOnClickListener {
            removeFromCart(txtTotal!!, units!!, resource.get(postion).price!!);
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
                        resource.get(postion).id!!,
                        brand_id!!,
                        resource.get(postion).name!!,
                        resource.get(postion).image[0]!!,
                        resource.get(postion).price!!, extraList)

//                            viewModel.addItem(resource.get(postion).brandId,cartItem!!
//                                ,NumberFormat.getInstance().parse(units!!.text.toString()).toInt())

                if (viewModel.cart.value!!.isEmpty()) {
                    viewModel.addItem(
                            brand_id, cartItem
                            , NumberFormat.getInstance().parse(units!!.text.toString()).toInt()
                            , brand.logo!!, brand.name!!
                            , activity!!, extraList)
                } else {
                    var shhpid = 0
                    if (viewModel.cart.value!!.isNotEmpty()) {
                        viewModel.cart.value!!.forEach { item: Map.Entry<Int, ShopCart> ->
                            shhpid = item.value.id
                        }
                        if (shhpid != resource.get(postion).brandId!!) {
                            AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.cartnotmatch))

                        } else viewModel.addItem(
                                brand_id, cartItem
                                , NumberFormat.getInstance().parse(units!!.text.toString()).toInt()
                                , brand.logo!!, brand.name!!, activity!!, extraList)
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

    fun cartBadge() {
//        var num = 0
//        viewModel.cart.value!!.toList()[0].second.items.value!!.toList().forEach { item ->
//            num += item.second
//        }
//
//        val drawable = DrawableBadge.Builder(context!!)
//                .drawableResId(R.color.transparent)
//                .badgeColor(R.color.tw__composer_red)
//                .badgeSize(R.dimen._19sdp)
//                .badgePosition(BadgePosition.TOP_LEFT)
//                .textColor(R.color.textWhite)
//                .showBorder(true)
//                .badgeBorderColor(R.color.textWhite)
//                .badgeBorderSize(R.dimen._1sdp)
//                .maximumCounter(100)
//                .build()
//                .get(num)
//
//        activity!!.findViewById<ImageView>(R.id.imageBadge).setImageDrawable(drawable)
    }
}
