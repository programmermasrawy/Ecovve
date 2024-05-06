package com.q8intouch.ecovve.ui.cart

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.databinding.FragmentCartBinding
import com.q8intouch.ecovve.network.model.EcovveAllCategoryResponse
import com.q8intouch.ecovve.ui.home.CategoriesAdapter
import com.q8intouch.ecovve.util.AppUtils
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.URLs
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.onClick
import java.util.ArrayList
import kotlin.random.Random

class CartFragment : BaseFragment<CartViewModel, FragmentCartBinding>() {
    override fun getLayoutRes() = R.layout.fragment_cart

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.cart.value != null && viewModel.cart.value!!.toList().size != 0) {
            no_items.visibility = View.GONE
            layout.visibility = View.VISIBLE
            if (!viewModel.cart.value!!.toList().get(0).second.logo.toString().isNullOrEmpty())
                if (!viewModel.cart.value!!.toList().get(0).second.logo.contains("http"))
                    Glide.with(context!!).load(URLs.IMAGES_URL + viewModel.cart.value!!.toList().get(0).second.logo).into(shopLogo)
                else
                    Glide.with(context!!).load(viewModel.cart.value!!.toList().get(0).second.logo).into(shopLogo)

            shopTilte.text = viewModel.cart.value!!.toList().get(0).second.name


            binding.btnContinue.onClick {
                val sharedPreferences: Shared = Shared(context!!)
                if (viewModel.cart.value!!.isNotEmpty()) {
                    if (etNotes.text.isEmpty()) {
                        sharedPreferences.save(URLs.Notes, " ")
                    } else {
                        sharedPreferences.save(URLs.Notes, etNotes.text.toString())
                    }
                    findNavController().navigate(R.id.OrderCheckoutFragment)
                } else {
                    AppUtils.showDailog(activity!!, resources.getString(R.string.cart_empty))
                }
            }

            binding.btnOrderMore.onClick {
                var bundle = bundleOf("amount" to "" + viewModel.cart.value!!.toList()[0].second.id)
                findNavController().navigate(R.id.shopInfoFragment, bundle)
            }
        } else {
            no_items.visibility = View.VISIBLE
            layout.visibility = View.INVISIBLE
        }
    }
}
