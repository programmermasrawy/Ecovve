package com.q8intouch.ecovve.ui.cart


import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import com.bumptech.glide.Glide
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseItemViewModel
import com.q8intouch.ecovve.data.model.ShopCart
import com.q8intouch.ecovve.databinding.RowCartBinding
import com.q8intouch.ecovve.util.CartUtils
import com.q8intouch.ecovve.util.URLs


class ShopCartViewModel constructor(val data: ShopCart?) : BaseItemViewModel<RowCartBinding>() {
    override fun getLayoutRes(): Int = R.layout.row_cart
    val total = CartUtils.calculateShopCartTotal(data!!.items.value!!).toString()

    init {

    }

    override fun onViewBound(binding: RowCartBinding) {
        binding.imgShopLogo.setOnClickListener {
            var bundle = bundleOf("amount" to "" + data!!.items.value!!.toList()[0].first.shopId)
            findNavController(binding.root).navigate(R.id.shopInfoFragment, bundle)
        }

        binding.txtShopName.setOnClickListener {
            var bundle = bundleOf("amount" to "" + data!!.items.value!!.toList()[0].first.shopId)
            findNavController(binding.root).navigate(R.id.shopInfoFragment, bundle)
        }

        if (data!!.logo.isNotEmpty())
            if (!data.logo.toString().contains("http"))
            Glide.with(binding.imgShopLogo.context).load(URLs.IMAGES_URL + data.logo).into( binding.imgShopLogo)
        else
            Glide.with(binding.imgShopLogo.context).load(data.logo).into( binding.imgShopLogo)

        binding.txtShopName.text = data.name

//        binding.btnAddItems.setOnClickListener {
//
//            cartRepo.addItem(
//                data!!.id,
//                CartItem(
//                    Random.nextInt(0,1000),
//                    data.items.value!!.toList().get(0).first.shopId,
//                    data.items.value!!.toList().get(0).first.name,
//                    data.items.value!!.toList().get(0).first.image
//                    , data.items.value!!.toList().get(0).first.price
//                ), 1
//            )
//
//        }

    }
}