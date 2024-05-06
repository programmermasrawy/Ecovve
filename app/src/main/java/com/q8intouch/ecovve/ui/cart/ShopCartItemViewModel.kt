package com.q8intouch.ecovve.ui.cart

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseItemViewModel
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.databinding.DailogEditCartItemBinding
import com.q8intouch.ecovve.databinding.RowCartItemBinding
import com.q8intouch.ecovve.util.CartUtils
import com.q8intouch.ecovve.util.URLs
import com.q8intouch.ecovve.util.extension.getParentActivity

class ShopCartItemViewModel constructor(val data: CartItem?, private val initialQuantity: Int = 1) :
    BaseItemViewModel<RowCartItemBinding>() {
    override fun getLayoutRes(): Int = R.layout.row_cart_item
    val quantity = MutableLiveData<Int>()
    val total :MutableLiveData<Float> = MutableLiveData()
    val kd  = R.string.kd
    var title = R.string.cart

    // val total = CartUtils.calculateShopCartItemTotal(data!!,quantity).toString()

    override fun onViewBound(binding: RowCartItemBinding) {
        quantity.value = initialQuantity
        total.value = CartUtils.calculateShopCartItemTotal(data!!,quantity.value!!)
        super.onViewBound(binding)

        if (data.image.isNotEmpty()) {
            if (!data.image.toString().contains("http"))
                Glide.with(binding.imageView14.context).load(URLs.IMAGES_URL + data.image).into(binding.imageView14)
            else
                Glide.with(binding.imageView14.context).load(data.image).into(binding.imageView14)
        }

        binding.Total.text =  total.value!!.toString()
        binding.txtPrice.text =""+ data.price
        binding.quen.text =""+ quantity.value!!.toString()

        binding.btnDelete.setOnClickListener {
            val factory = LayoutInflater.from(binding.btnDelete.context!!)
            val addToCartDialogView = factory.inflate(R.layout.dailog_delete_item, null)
            val deleteDialog = android.app.AlertDialog.Builder(binding.btnDelete.context!!).create()
            deleteDialog.setView(addToCartDialogView)
            deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            deleteDialog.show()

            deleteDialog.findViewById<AppCompatButton>(R.id.delete).setOnClickListener {
                cartRepo.removeItem(data!!,binding.btnDelete.getParentActivity()!!)
                deleteDialog.dismiss()
            }

            deleteDialog.findViewById<AppCompatButton>(R.id.btnClose).setOnClickListener {
                deleteDialog.dismiss()
            }
        }
//        binding.spanEditItem.setOnClickListener {
//            val inflater = LayoutInflater.from(it.context!!)
//            val editItemDialogBinding = DataBindingUtil
//                .inflate<DailogEditCartItemBinding>(
//                    inflater, R.layout.dailog_edit_cart_item,
//                    null, false
//                )
//
//            val addToCartDialog = AlertDialog.Builder(it.context!!).create()
//                .apply {
//                    setView(editItemDialogBinding.root)
//                    window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//                    editItemDialogBinding.viewModel = this@ShopCartItemViewModel
//                    editItemDialogBinding.txtItemName.text = data.name
//                    editItemDialogBinding.txtItemPrice.text ="   " + data.price + "       "
                    binding.banPlusQuantity.setOnClickListener {
                        quantity.apply { value = value!! + 1 }
                        total.value = CartUtils.calculateShopCartItemTotal(data,quantity.value!!)
                        binding.Total.text =  total.value!!.toString()
                        binding.txtPrice.text =""+ data.price
                        binding.quen.text =""+ quantity.value!!.toString()
                    }
                    binding.banMinusQuantity.setOnClickListener {
                        quantity.apply { value = if (value!! > 1) value!! - 1 else 1 }
                        total.value = CartUtils.calculateShopCartItemTotal(data,quantity.value!!)
                        binding.Total.text =  total.value!!.toString()
                        binding.txtPrice.text =""+ data.price
                        binding.quen.text =""+ quantity.value!!.toString()
                    }
//
//                    editItemDialogBinding.btnSaveCartItem.setOnClickListener {
//                       var extra = ArrayList<String>()
//                        cartRepo.addItem(data!!.shopId, data, quantity.value!!, data.image, data.name, extra,editItemDialogBinding.btnSaveCartItem.getParentActivity()!!)
//
//                        dismiss()
//                    }
//                    show()
//                }
//
//        }
    }
}

