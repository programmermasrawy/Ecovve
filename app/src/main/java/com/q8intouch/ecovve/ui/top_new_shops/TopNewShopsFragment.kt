package com.q8intouch.ecovve.ui.top_new_shops

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.malinskiy.superrecyclerview.SuperRecyclerView

//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentTopNewShopsBinding
import com.q8intouch.ecovve.network.model.EcovveNewBrand
import com.q8intouch.ecovve.network.model.EcovveTopCafes
import com.q8intouch.ecovve.util.extension.errorResponse
import kotlinx.android.synthetic.main.search_view_result.*

class TopNewShopsFragment : BaseFragment<TopNewShopsViewModel, FragmentTopNewShopsBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_top_new_shops
    }

    lateinit var layoutMsanager: GridLayoutManager
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        page = 0
        data = ArrayList<EcovveTopCafes.Data>()
        loadtop()
        layoutMsanager = GridLayoutManager(context, 2)
        layoutMsanager.isSmoothScrollbarEnabled = true
        binding.rvTopNewShops.setDrawingCacheEnabled(true);
        binding.rvTopNewShops.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    var page = 0
    var data = ArrayList<EcovveTopCafes.Data>()
    lateinit var promoadapter: TopShopAdapter
    var outletData = ArrayList<EcovveTopCafes.Data.Outlets?>()
    lateinit var adapter: BrandOutletsAdapter
    private fun loadtop() {

        viewModel.topCafes(++page).observe(this, Observer {
            if (it.isSuccess) {
                load_more.visibility = View.GONE
                if (it.resource!!.data.isNotEmpty()) {
                    if (page == 1)
                        data.addAll(it.resource!!.data)
                    else {
                        data.addAll(it.resource!!.data)
                        promoadapter.notifyDataSetChanged()
                    }
                    if (page == 1) {
                        if (data.size != 0) {
                            promoadapter = TopShopAdapter(data, context!!, object :
                                    TopShopAdapter.ControlsListeners {
                                @SuppressLint("WrongConstant")
                                override fun click(postion: Int) {
                                    outletData = ArrayList<EcovveTopCafes.Data.Outlets?>()
                                   if (data[postion].outlets!=null && data[postion].outlets!!.size!=0){
                                       data[postion].outlets!!.forEachIndexed { index, data ->
                                               outletData.add(data)
                                       }

                                       val factory = LayoutInflater.from(activity!!)
                                       val addToCartDialogView = factory.inflate(R.layout.select_brand_outlet, null)
                                       val deleteDialog = android.app.AlertDialog.Builder(activity!!).create()
                                       deleteDialog.setView(addToCartDialogView)
                                       deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                                       deleteDialog.window!!.setBackgroundDrawableResource(R.drawable.tab_layout_ui);
                                       deleteDialog.window!!.setGravity(Gravity.BOTTOM);
                                       deleteDialog.window!!.attributes.windowAnimations = R.style.DialogAnimation2; //style id

                                       deleteDialog.show()
                                       deleteDialog.findViewById<TextView>(R.id.caty_name).text = resources.getString(R.string.select_outlet)

                                       adapter = BrandOutletsAdapter(outletData, context!!, object :
                                               BrandOutletsAdapter.ControlsListeners {
                                           override fun click(position: Int) {
                                               var bundle = bundleOf("amount" to "" + outletData[position]!!.id)
                                               findNavController().navigate(R.id.shopInfoFragment, bundle)
                                               deleteDialog.dismiss()
                                           }
                                       })
                                       val layoutmanager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
                                       val list = deleteDialog.findViewById<SuperRecyclerView>(R.id.sub_caty)
                                       list.setLayoutManager(layoutmanager)
                                       list.adapter = adapter

                                       deleteDialog.findViewById<ImageView>(R.id.cancel).setOnClickListener {
                                           //delete
                                           deleteDialog.dismiss()
                                       }
                                   }
                                    else {
                                       var bundle = bundleOf("amount" to "" + data[postion]!!.id)
                                       findNavController().navigate(R.id.shopInfoFragment, bundle)
                                   }
                                }
                            })

                            binding.rvTopNewShops.setLayoutManager(layoutMsanager)
                            binding.rvTopNewShops.adapter = promoadapter
                        }
                    }
                } else {
                    binding.rvTopNewShops.visibility = View.GONE
                    Snackbar.make(view!!, getString(R.string.empty_list), Snackbar.LENGTH_LONG).show()
                }
//                binding.rvTopNewShops.setupMoreListener(OnMoreListener { numberOfItems, numberBeforeMore, currentItemPos ->
//                    // Fetch more from Api or DB
//                    loadtop()
//                }, 10)
//                })
            } else {
                load_more.visibility = View.GONE
                Log.e("makjdk", it.errorResponse().message)
//                Toast.makeText(context, activity!!.resources.getString(R.string.error), Toast.LENGTH_LONG).show()
            }
        })
//        binding.rvTopNewShops.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                val visibleItemCount = layoutMsanager.getChildCount()
//                val totalItemCount = layoutMsanager.getItemCount()
//                val firstVisibleItemPosition = layoutMsanager.findFirstVisibleItemPosition()
//
////                        if (!isLoading() && !isLastPage()) {
//                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
//                    loadtop()
//                    load_more.visibility = View.VISIBLE
//
////                            }
//                }
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })
    }
}
