package com.q8intouch.ecovve.ui.offers

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.malinskiy.superrecyclerview.OnMoreListener

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.model.ShopCart
import com.q8intouch.ecovve.databinding.OfferItemsFragmentBinding
import com.q8intouch.ecovve.network.model.EcovveOfferItems
import com.q8intouch.ecovve.ui.home.ChoicesAdapter
import com.q8intouch.ecovve.ui.home.ExtraAdapter
import com.q8intouch.ecovve.ui.home.ExtraModel
import com.q8intouch.ecovve.ui.home.ExtraModelItems
import com.q8intouch.ecovve.util.AppUtils
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.URLs
import kotlinx.android.synthetic.main.dailog_add_to_cart.*
import kotlinx.android.synthetic.main.fragment_offers.*
import org.jetbrains.anko.onClick
import java.text.DecimalFormat
import java.text.NumberFormat

class OfferItemsFragment : BaseFragment<OfferItemsViewModel,OfferItemsFragmentBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.offer_items_fragment
    }
    var extraList = ArrayList<String>()
    var extraModel = ArrayList<ExtraModelItems>()
    private var plusQuantity : FloatingActionButton? = null
    private var minusQuantity : FloatingActionButton? = null
    private var txtTotal : TextView? = null
    private var units : TextView? = null

    companion object {
        fun newInstance(
                amount: String
        ): OfferItemsFragment {
            val frag = OfferItemsFragment()
            val bundle = Bundle()
            bundle.putString("amount", amount)
            frag.arguments = bundle
            return frag
        }
    }
    lateinit var dialog : Dialog
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadItems()
    }
    var page = 0
    var data = ArrayList<EcovveOfferItems.Data.Item>()
    lateinit var menuAdapter : OfferItemsAdapter
    @SuppressLint("WrongConstant")
    private fun  loadItems(){
        if(page==0) {
             dialog = LoadingDialog.showDialog(view!!.context)
        }else{

        }

        var id = arguments!!.getString("amount")

        viewModel.showofferItems(id,++page).observe(this, Observer {
        if (page == 1)
            dialog.dismiss()
            if (it.isSuccess){
                if (page == 1) {
                    data.addAll(it.resource!!.data!!.item!!)
                } else {
                    data.addAll(it.resource!!.data!!.item!!)
                    menuAdapter.notifyDataSetChanged()
                }
                if (page ==1) {
                    if (it.resource!!.data!!.item!!.isNotEmpty()) {
                        extraList = ArrayList()
                        extraModel = ArrayList()
                        menuAdapter = OfferItemsAdapter(data, context!!, it.resource!!.data!!, object :
                                OfferItemsAdapter.ControlsListeners {
                            override fun click(postion: Int) {
                                extraList = ArrayList()
                                extraModel = ArrayList()
                                var resource = data
                                var brand = data.get(postion).brand
                                val dialog = LoadingDialog.showDialog(context!!)
                                viewModel.showItem("" + resource[postion].id!!).observe(this@OfferItemsFragment, Observer {
                                   dialog.dismiss()
                                    if (it.isSuccess) {
                                        val extraList = ArrayList<String>()
                                        val adapter = ExtraAdapter(it.resource!!.data!!.extras, context!!,
                                                object : ExtraAdapter.ControlsListeners {
                                                    @SuppressLint("WrongConstant")
                                                    override fun click(id: Int, choices: RecyclerView) {
                                                        viewModel.showExtra("" + id).observe(this@OfferItemsFragment, Observer {
                                                            if (it.isSuccess) {
                                                                val Extra = ExtraModelItems(id,it.resource!!.data!!.choices,it.resource!!.data!!.is_required!!)
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

                                        addTocartDialog(brand, resource, postion,adapter)
                                    } else {
                                        Log.e("}}}OOOOOOOOOOO}}}", it.error!!.localizedMessage)
                                    }
                                })
                            }
                        })
                                binding.rvMenuItems.setLayoutManager(
                                        LinearLayoutManager(
                                                context,
                                                LinearLayoutManager.VERTICAL,
                                                false
                                        )
                                )
                                binding.rvMenuItems.adapter = menuAdapter
                            }

                    else {
                        binding.rvMenuItems.isLoadingMore = false
                        binding.rvMenuItems.visibility = View.GONE
//                        Snackbar.make(view!!,getString(R.string.empty_list),Snackbar.LENGTH_LONG).show()
                        no_items.text = activity!!.resources.getString(R.string.no_offers)
                        binding.rvMenuItems.visibility = View.GONE

                    }
                }

                binding.rvMenuItems.setupMoreListener(OnMoreListener { numberOfItems, numberBeforeMore, currentItemPos ->
                    loadItems()
                }, 4)
            }
            else {
                binding.rvMenuItems.isLoadingMore = false
                binding.rvMenuItems.visibility = View.GONE
                no_items.text = activity!!.resources.getString(R.string.no_offers)
                binding.rvMenuItems.visibility = View.GONE
            }
        })

    }
    var isNotMatch = ArrayList<String>()
    @SuppressLint("WrongConstant")
    private fun addTocartDialog(brand: EcovveOfferItems.Data.Item.Brand?, resource: ArrayList<EcovveOfferItems.Data.Item>
                                , postion: Int, adapter: ExtraAdapter) {
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

        isNotMatch = ArrayList<String>()
        addToCartDialog.findViewById<AppCompatButton>(R.id.btnAddToCart).onClick {
            isNotMatch = ArrayList<String>()
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
                        resource.get(postion).brand!!.id!!,
                        resource.get(postion).name!!,
                        resource.get(postion).image[0]!!,
                        resource.get(postion).price!!, extraList)

//                            viewModel.addItem(resource.get(postion).brandId,cartItem!!
//                                ,NumberFormat.getInstance().parse(units!!.text.toString()).toInt())

                if (viewModel.cart.value!!.isEmpty()) {
                    viewModel.addItem(
                            resource.get(postion).brand!!.id!!, cartItem
                            , NumberFormat.getInstance().parse(units!!.text.toString()).toInt()
                            , brand!!.logo!!, brand!!.name!!
                            , activity!!, extraList)
//                                        cartBadge()
                } else {
                    var shhpid = 0
                    if (viewModel.cart.value!!.isNotEmpty()) {
                        viewModel.cart.value!!.forEach { item: Map.Entry<Int, ShopCart> ->
                            shhpid = item.value.id
                        }
                        if (shhpid != resource.get(postion).brandId!!) {
                            AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.cartnotmatch))

                        } else viewModel.addItem(
                                resource.get(postion).brand!!.id!!, cartItem
                                , NumberFormat.getInstance().parse(units!!.text.toString()).toInt()
                                , brand!!.logo!!, brand!!.name!!, activity!!, extraList)
//                                            cartBadge()
                    }
                }
                addToCartDialog.dismiss()
            }
        }
    }
    val myFormatter = DecimalFormat("############")

    @SuppressLint("SetTextI18n")
    private fun addToCartplus(total: TextView, units: TextView, price: Float){

        var doubletotal = NumberFormat.getInstance().parse(total.text.toString()).toFloat()
//        var unitPrice = NumberFormat.getInstance().parse(price).toDouble()
        doubletotal += price
        total.text = ""+myFormatter.format(doubletotal)
        var unitsadd = Integer.valueOf(units.text.toString())
        unitsadd+=1
        units.text = "$unitsadd"
    }

    @SuppressLint("SetTextI18n")
    private fun removeFromCart(total: TextView, units: TextView, price: Float) {
//        var unitPrice = NumberFormat.getInstance().parse(price).toDouble()
        var unitsnum = Integer.valueOf(units.text.toString())
        if (unitsnum != 0) {
            var doubletotal = NumberFormat.getInstance().parse(total.text.toString()).toFloat()
            doubletotal -= price
            total.text = "" + myFormatter.format(doubletotal)

            unitsnum -= 1
            units.text = "$unitsnum"
        }
    }

    fun cartBadge(){
        var num = 0
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
//                .maximumCounter(100)
//                .build()
//                .get(num)
//
//        activity!!.findViewById<ImageView>(R.id.imageBadge).setImageDrawable(drawable)
    }
}
