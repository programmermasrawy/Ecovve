package com.q8intouch.ecovve.ui.offers

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentOffersBinding
import com.malinskiy.superrecyclerview.OnMoreListener
import com.q8intouch.ecovve.network.model.EcovveAllPromotion
import kotlinx.android.synthetic.main.fragment_offers.*

class OffersFragment : BaseFragment<OffersViewModel, FragmentOffersBinding>()  {
    override fun getLayoutRes(): Int = R.layout.fragment_offers
    var page = 0

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        page=0
        loadOffers()
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible){
            page = 0
            loadOffers()
        }
    }

    var data = ArrayList<EcovveAllPromotion.Data>()
    lateinit var promoadapter : AllOffersAdapter
    @SuppressLint("WrongConstant")
    private fun loadOffers(){
        viewModel.allPromotions(++page).observe(this, Observer {
            if (it.isSuccess) {
                if (page == 1)
                data.addAll(it.resource!!.data)
                else {
                    data.addAll(it.resource!!.data)
                    promoadapter.notifyDataSetChanged()
                }
                if (page == 1) {
                    if (data.size != 0) {
                        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                        promoadapter = AllOffersAdapter(data, context!!, object :
                            AllOffersAdapter.ControlsListeners {
                            override fun click(postion: Int) {
                                var bundle = bundleOf("amount" to "" + postion)
                                findNavController().navigate(R.id.shopInfoFragment, bundle)
                            }
                        })
                        binding.offers.setLayoutManager(mLayoutManager)
                        binding.offers.adapter = promoadapter

                    } else {
                       no_items.text = activity!!.resources.getString(R.string.empty_list)
                        binding.offers.visibility = View.GONE

                    }
                }
                // when there is only 10 items to see in the recycler, this is triggered
                binding.offers.setupMoreListener(OnMoreListener { numberOfItems, numberBeforeMore, currentItemPos ->
                    // Fetch more from Api or DB
                    loadOffers()
                }, 2)
            }
            else
                Toast.makeText(context,activity!!.resources.getString(R.string.error), Toast.LENGTH_LONG).show()
        })
    }


}
