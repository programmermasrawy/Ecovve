package com.q8intouch.ecovve.ui.gift

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.network.model.GiftCardsListResponse
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.extension.errorResponse
import kotlinx.android.synthetic.main.gift_card_fragment.*

class GiftCardFragment : BaseFragment<GiftCardViewModel, com.q8intouch.ecovve.databinding.GiftCardFragmentBinding>() {

    companion object {
        fun newInstance() = GiftCardFragment()
    }
    lateinit var dialog : Dialog
    override fun getLayoutRes() = R.layout.gift_card_fragment
    var page = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         dialog = LoadingDialog.showDialog(view.context)
        val layoutMsanager = GridLayoutManager(view.context, 2)
        giftCardsRecyclerView.layoutManager = layoutMsanager
        page = 0
        loadCards()
        giftCardsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val visibleItemCount = layoutMsanager.getChildCount()
                val totalItemCount = layoutMsanager.getItemCount()
                val firstVisibleItemPosition = layoutMsanager.findFirstVisibleItemPosition()

                if (visibleItemCount == totalItemCount-2 ) {
                    load_more.visibility = View.VISIBLE
                    loadCards()
//                            }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


            }
        })

    }
    var data = ArrayList<GiftCardsListResponse.DataEntity>()
    lateinit var adapter : CardsAdapter
    private fun loadCards(){
        viewModel.giftCards(++page).observe(this, Observer {
            dialog.dismiss()
            if (it.isSuccess) {
                if (page == 1) {
                    if (it.resource!!.data.isNotEmpty()) {
                        data.addAll(it.resource!!.data)
                        adapter = CardsAdapter(data, context!!, object : CardsAdapter.ControlsListeners {
                            override fun click(postion: Int) {
                                val data = bundleOf("data" to it.resource!!.data.get(postion))
                                findNavController().navigate(R.id.buyGiftCardFragment, data)
                            }
                        })
                        giftCardsRecyclerView.adapter = adapter
                    }
                    else {
                        Snackbar.make(view!!,resources.getString(R.string.empty_list),Snackbar.LENGTH_LONG).show()
                        load_more.visibility = View.GONE
                    }
                }
            else {
                    data.addAll(it.resource!!.data)
                    load_more.visibility = View.GONE
                    adapter.notifyDataSetChanged()
            }
            }
            else {
                Log.e("mohammed",it.errorResponse().message)
            }
        })
    }
}