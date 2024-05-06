package com.q8intouch.ecovve.ui.gift.buy_gift

import android.os.Bundle
import androidx.navigation.fragment.findNavController

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.network.model.GiftCardsListResponse
import com.q8intouch.ecovve.ui.gift.BuyGiftCardViewModel
import kotlinx.android.synthetic.main.buy_gift_card_fragment.*
import org.jetbrains.anko.onClick

class BuyGiftCard : BaseFragment<BuyGiftCardViewModel,com.q8intouch.ecovve.databinding.BuyGiftCardFragmentBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.buy_gift_card_fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val data  = arguments!!.getParcelable("data") as GiftCardsListResponse.DataEntity
        btnSubmit.onClick {
            when {
                etName.text.toString().isEmpty() -> {
                    etName.error = getString(R.string.invaidorempty)
                }
                etEmail.text.toString().isEmpty() -> {
                    etEmail.error = getString(R.string.invaidorempty)
                }
                else -> {
                    val bundle = Bundle()
                    bundle.putString("name", etName.text.toString())
                    bundle.putString("email",etEmail.text.toString())
                    bundle.putString("message",etMessage.text.toString())
                    bundle.putParcelable("data",data)
                    findNavController().navigate(R.id.SelectGiftCardFragment,bundle)
                }
            }
        }
    }
}