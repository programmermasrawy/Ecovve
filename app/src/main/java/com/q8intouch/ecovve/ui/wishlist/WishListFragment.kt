package com.q8intouch.ecovve.ui.wishlist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ramotion.cardslider.CardSliderLayoutManager
import com.ramotion.cardslider.CardSnapHelper
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.model.ShopCart
import com.q8intouch.ecovve.databinding.WishListFragmentBinding
import com.q8intouch.ecovve.ui.home.ExtraAdapter
import com.q8intouch.ecovve.ui.wishlist.slider.WishListAdapter
import com.q8intouch.ecovve.util.AppUtils
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.URLs
import kotlinx.android.synthetic.main.dailog_add_to_cart.*
import kotlinx.android.synthetic.main.wish_list_fragment.*
import org.jetbrains.anko.onClick
import java.text.DecimalFormat
import java.text.NumberFormat

class WishListFragment : BaseFragment<WishListViewModel,WishListFragmentBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.wish_list_fragment
    }
    private var plusQuantity : FloatingActionButton? = null
    private var minusQuantity : FloatingActionButton? = null
    private var txtTotal : TextView? = null
    private var units : TextView? = null

    companion object {
        fun newInstance() = WishListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialog = LoadingDialog.showDialog(view.context)
        viewModel.favorites().observe(this, Observer {
            dialog.dismiss()
            if (it.isSuccess) {
                val suborder = it.resource!!.data.get(0).favorites

                if (suborder!!.isEmpty()) {
                    binding.prevorders.visibility = View.GONE
                    no_items.text = resources.getString(R.string.empty_list)

                } else {
                    val WishListAdapter = WishListAdapter(it.resource!!.data.get(0).favorites, it.resource!!.data.size,context!!,
                        WishListAdapter.ControlsListeners {
                            var position = it
                            viewModel.showItem("" + suborder.get(position).id).observe(this@WishListFragment, Observer {
                                if (it.isSuccess) {
                                    val extraList = ArrayList<String>()
                                    it.resource!!.data!!.extras!!.forEachIndexed { index, extra ->
                                        if (extra!!.is_required == 1)
                                            extraList.add("" + extra.id)
                                    }
                                    val adapter = ExtraAdapter(it.resource!!.data!!.extras, context!!,
                                            object : ExtraAdapter.ControlsListeners {
                                                override fun click(postion: Int, choices: RecyclerView) {

                                                }
                                            })
                                    val factory = LayoutInflater.from(context!!)
                                    val addToCartDialogView = factory.inflate(R.layout.dailog_add_to_cart, null)
                                    val addToCartDialog = android.app.AlertDialog.Builder(context!!).create()
                                    addToCartDialog.setView(addToCartDialogView)
                                    addToCartDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                                    addToCartDialog.show()
                                    addToCartDialog.findViewById<RecyclerView>(R.id.extraList).layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
                                    addToCartDialog.findViewById<RecyclerView>(R.id.extraList).adapter = adapter

                                    addToCartDialog.txtItemPrice.text = "" + suborder!!.get(position).price + "       "
                                    addToCartDialog.txtItemPrice.text = "" + suborder!!.get(position).price + "       "
                                    addToCartDialog.txtItemName.setText("" + suborder!!.get(position).name)
                                    minusQuantity = addToCartDialog.findViewById(R.id.banMinusQuantity)
                                    plusQuantity = addToCartDialog.findViewById(R.id.banPlusQuantity)
                                    txtTotal = addToCartDialog.findViewById(R.id.txtTotal)
                                    units = addToCartDialog.findViewById(R.id.units)
                                    if (suborder.get(position).image.size != 0)
                                        if (!suborder.get(position).image.get(0).toString().contains("http"))
                                            Glide.with(context!!).load(URLs.IMAGES_URL + suborder.get(position).image.get(0)).into(addToCartDialog.findViewById(R.id.imageView3))
                                        else
                                            Glide.with(context!!).load(suborder.get(position).image.get(0)).into(addToCartDialog.findViewById(R.id.imageView3))

                                    plusQuantity?.setOnClickListener {
                                        addToCartplus(
                                                txtTotal!!,
                                                units!!,
                                                suborder!!.get(position).price!!
                                        );
                                    }

                                    minusQuantity?.setOnClickListener {
                                        removeFromCart(
                                                txtTotal!!,
                                                units!!,
//                                        20.0F
                                                suborder!!.get(position).price!!
                                        );
                                    }

                                    addToCartDialog.btnAddToCart?.onClick {

                                        val cartItem = CartItem(
                                                suborder[position].id!!,
                                                suborder[position].id!!,
                                                "" + suborder[position].id,
                                                "" + suborder[position].image,
                                                suborder.get(position).price!!,extraList
                                        )
                                        if (viewModel.cart.value!!.isEmpty()) {
                                            viewModel.addItem(
                                                    suborder[position].brandId!!, cartItem
                                                    , NumberFormat.getInstance().parse(units!!.text.toString()).toInt()
                                                    , suborder[position].brand!!.logo!!,
                                                    suborder[position].brand!!.name!!, activity!!,extraList)
                                        } else {
                                            var shhpid = 0
                                            if (viewModel.cart.value!!.isNotEmpty()) {
                                                viewModel.cart.value!!.forEach { item: Map.Entry<Int, ShopCart> ->
                                                    shhpid = item.value.id
                                                }
                                                if (shhpid != suborder.get(position).id) {
                                                    AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.cartnotmatch))

                                                } else viewModel.addItem(
                                                        suborder.get(position).brandId!!, cartItem
                                                        , NumberFormat.getInstance().parse(units!!.text.toString()).toInt()
                                                        , suborder.get(position).brand!!.logo!!
                                                        , suborder.get(position).brand!!.name!!, activity!!
                                                ,extraList)
                                            }
                                        }
                                        addToCartDialog.dismiss()

                                    }

                                    addToCartDialog.btnClose?.onClick {
                                        addToCartDialog.dismiss()
                                    }

                                }
                            })
                        })
                    var layoutmam = CardSliderLayoutManager(context!!)
                    layoutmam.activeCardCenter

                    binding.prevorders.setLayoutManager(layoutmam);
                    CardSnapHelper().attachToRecyclerView(binding.prevorders);
//                        binding.prevorders.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                                    onActiveCardChange()
//                                }
//                            }
//                        })
                    binding.prevorders.adapter = WishListAdapter
                }
            } else {
                Toast.makeText(context, "" + it.error.toString(), Toast.LENGTH_LONG).show()

                val Adapter = WishListAdapter(5,
                    WishListAdapter.ControlsListeners {

                    })
                var layoutmam = CardSliderLayoutManager(context!!)
                layoutmam.activeCardLeft

                binding.prevorders.setLayoutManager(layoutmam);
                CardSnapHelper().attachToRecyclerView(binding.prevorders);

                binding.prevorders.adapter = Adapter
            }
        })
    }
    val myFormatter = DecimalFormat("############")

    private fun addToCartplus(total: TextView, units:TextView, price: Float){
        var doubletotal = NumberFormat.getInstance().parse(total.text.toString()).toFloat()
        var unitPrice = NumberFormat.getInstance().parse(price.toString()).toDouble()
        doubletotal= doubletotal + price
        total.text = ""+myFormatter.format(doubletotal)
        var unitsadd = Integer.valueOf(units.text.toString())
        unitsadd+=1
        units.setText("$unitsadd")
    }

    private fun removeFromCart(total:TextView, units:TextView, price: Float){
        var unitPrice = NumberFormat.getInstance().parse(price.toString()).toFloat()
        var unitsnum = Integer.valueOf(units.text.toString())
        if (unitsnum!=0){
            var doubletotal = NumberFormat.getInstance().parse(total.text.toString()).toDouble()
            doubletotal= doubletotal - price
            total.text = ""+myFormatter.format(doubletotal)

            unitsnum-=1
            units.setText("$unitsnum")
        }
    }
}
