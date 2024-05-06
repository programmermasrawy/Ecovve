package com.q8intouch.ecovve.ui.cart

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.network.model.GiftCardsListResponse
import com.q8intouch.ecovve.util.LoadingDialog
import kotlinx.android.synthetic.main.gift_card_order_fragment.*
import org.jetbrains.anko.collections.forEachByIndex

class GiftCardOrderFragment : BaseFragment<GiftCardOrderViewModel, com.q8intouch.ecovve.databinding.GiftCardFragmentBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    companion object {
        fun newInstance() = GiftCardOrderFragment()
    }

    var gift = ""
    override fun getLayoutRes() = R.layout.gift_card_order_fragment
    var page = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        page =0
        loadCards()
        val layoutMsanager = GridLayoutManager(view.context, 2)
        giftCardsRecyclerView.layoutManager = layoutMsanager


        submit.setOnClickListener {

            val bundle = Bundle()
            bundle.putStringArrayList("gift", list_gifts)
            bundle.putString("coupon","")
            findNavController().navigate(com.q8intouch.ecovve.R.id.OrderCheckoutFragment, bundle)

        }
    }

    var data = ArrayList<GiftCardsListResponse.DataEntity>()
    lateinit var adapter: UserCardsAdapter
    var list_gifts = ArrayList<String>()
    private fun loadCards() {
        var id = activity!!.intent.extras.getString("amount")
        val dialog = LoadingDialog.showDialog(view!!.context)
        viewModel.fetchUserData(id).observe(this, Observer {
            dialog.dismiss()
            if (it.isSuccess) {
                adapter = UserCardsAdapter(it.resource!!.data.receivedGiftCards!!.data, context!!, object : UserCardsAdapter.ControlsListeners {
                    override fun click(postion: Int, s: String) {
                        if (s.equals("add")) {
                            list_gifts.add("" + it.resource!!.data.receivedGiftCards!!.data[postion].id)
                        } else {
                            list_gifts.forEachIndexed { index, item ->
                                if (item.equals(it.resource!!.data.receivedGiftCards!!.data[postion].id))
                                    list_gifts.remove(item)
                            }
                        }
//                        val data = bundleOf("data" to it.resource!!.data.receivedGiftCards!!.data.get(postion))
//                        findNavController().navigate(R.id.buyGiftCardFragment,data)
//                          gift = ""+ it.resource!!.data.receivedGiftCards!!.data[postion].id
                    }
                })
            }
            giftCardsRecyclerView.adapter = adapter
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.skip -> {
                val bundle = Bundle()
                bundle.putStringArrayList("gift", list_gifts)
                findNavController().navigate(com.q8intouch.ecovve.R.id.OrderCheckoutFragment, bundle)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.skip, menu)
        super.onCreateOptionsMenu(menu!!, inflater)
    }
}