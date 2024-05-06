package com.q8intouch.ecovve.ui.order

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View

import com.q8intouch.ecovve.R
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentMyOrdersBinding
import com.q8intouch.ecovve.network.model.EcovveUserOrders
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.extension.errorResponse
import kotlinx.android.synthetic.main.list_orders.*


class MyOrdersFragment : BaseFragment<MyOrdersViewModel,FragmentMyOrdersBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_my_orders
    }

    companion object {
        fun newInstance() = MyOrdersFragment()
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var id = activity!!.intent.extras.getString("amount")
        if (id != null) {
            binding.shopInfoPager.adapter = viewpagerApater(
                context!!,
                childFragmentManager!!
                , id
            )
            binding.tabLayout.setupWithViewPager(binding.shopInfoPager)
            binding.shopInfoPager.currentItem = 0
        }
    }

    class viewpagerApater(
        val context: Context,
        val fm: FragmentManager,
       val id: String?
    ) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            if (position == 0) {
                return InfoTabFragment(id!!,true)
            } else {
                return InfoTabFragment(id!!,false)
            }
        }

        override fun getCount(): Int {
            return 2
        }
        override fun getPageTitle(position: Int): CharSequence? {
            if (position == 0)
                return context.resources.getString(R.string.ongoing_orders)
            else
                return context.resources.getString(R.string.past_orders)
        }

    }
}

    @SuppressLint("ValidFragment")
    class InfoTabFragment constructor(
            val id : String,
            private val onGoing : Boolean
    ) : BaseFragment<MyOrdersViewModel,FragmentMyOrdersBinding>() {

        override fun getLayoutRes(): Int {
            return R.layout.list_orders
        }
        lateinit var adapter :MyOrdersAdapter
        var layout: Int = 0
        @SuppressLint("WrongConstant")
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val dialog = LoadingDialog.showDialog(view!!.context)
            if (onGoing) {
                viewModel.userOrders(id,"not_delivered").observe(this, Observer {
                    dialog.dismiss()
                    if (it.isSuccess) {
                        if (it.resource!!.data.isNotEmpty()) {
                            var data = it.resource!!.data as ArrayList<EcovveUserOrders.Data>
                             adapter = MyOrdersAdapter(data, context!!, object :
                                MyOrdersAdapter.ControlsListeners {
                                override fun remove(postion: Int) {
                                  viewModel.cancelOrder(""+data[postion].id).observe(this@InfoTabFragment, Observer {
                                    if (it.isSuccess){
                                        data.forEachIndexed { index, item ->
                                            if (postion == index) {
                                                data.remove(item)
                                                adapter.notifyDataSetChanged()
                                            }
                                        }
                                    }
                                  })
                                }

                                override fun click(postion: Int) {
                                    var bundle = bundleOf("amount" to data[postion])
                                    findNavController().navigate(
                                        R.id.orderDetailsFragment,
                                        bundle
                                    )
                                }
                            })
                            rvOrders.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                            rvOrders.adapter = adapter
                        }
                    }
                    else{
                        Log.e("error_my_orders",it.errorResponse().message)
                    }
                })

            } else {
                viewModel.userOrders(id,"delivered").observe(this, Observer {
                    dialog.dismiss()
                    if (it.isSuccess) {
                        if (it.resource!!.data!!.isNotEmpty()) {
                            val adapter = MyOrdersAdapter(it.resource!!.data!!, context!!, object :
                                MyOrdersAdapter.ControlsListeners {
                                override fun remove(postion: Int) {
                                    var bundle = bundleOf("amount" to it.resource!!.data!!.get(postion)!!.id)
                                    findNavController().navigate(R.id.action_myOrdersFragment_to_orderDetailsFragment, bundle)
                                }
                                override fun click(postion: Int) {
                                    var bundle = bundleOf("amount" to it.resource!!.data[postion])
                                    findNavController().navigate(R.id.orderDetailsFragment, bundle)
                                }
                            })
                            rvOrders.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                            rvOrders.adapter = adapter
                        }
                    } else {
                        Log.e("error_my_orders",it.errorResponse().message)
                    }
                })

            }

        }
    }


