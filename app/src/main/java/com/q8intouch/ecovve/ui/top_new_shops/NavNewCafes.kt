package com.q8intouch.ecovve.ui.top_new_shops

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.q8intouch.ecovve.base.BaseFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.malinskiy.superrecyclerview.SuperRecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.databinding.NavNewCafesFragmentBinding
import com.q8intouch.ecovve.network.model.EcovveNewBrand
import kotlinx.android.synthetic.main.nav_new_cafes_fragment.*

class NavNewCafes : BaseFragment<NavNewCafesViewModel, NavNewCafesFragmentBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.nav_new_cafes_fragment
    }

    var data = ArrayList<EcovveNewBrand.Data>()
    lateinit var promoadapter: NewShopAdapter
    var page = 0
    var outlet_page = 0
    var outletData = ArrayList<EcovveNewBrand.Data.Outlets?>()
    lateinit var adapter: BrandOutletsLatestAdapter

    lateinit var layoutMsanager: GridLayoutManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        page = 0
        data = ArrayList<EcovveNewBrand.Data>()
        loadnew()
        layoutMsanager = GridLayoutManager(context, 2)
        layoutMsanager.isSmoothScrollbarEnabled = true
        rvTopNewShops.setDrawingCacheEnabled(true);
        rvTopNewShops.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    private fun loadnew() {
        viewModel.latestCafes(++page).observe(this, Observer {
            if (it.isSuccess) {
//                load_more.visibility = View.GONE
                if (page == 1)
                    data.addAll(it.resource!!.data)
                else {
                    data.addAll(it.resource!!.data)
                    promoadapter.notifyDataSetChanged()
                }
                if (page == 1) {
                    if (data.size != 0) {
                        promoadapter = NewShopAdapter(it.resource!!.data, context!!, object :
                                NewShopAdapter.ControlsListeners {
                            @SuppressLint("WrongConstant")
                            override fun click(postion: Int) {
                                outletData = ArrayList<EcovveNewBrand.Data.Outlets?>()
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

                                    adapter = BrandOutletsLatestAdapter(outletData, context!!, object :
                                            BrandOutletsLatestAdapter.ControlsListeners {
                                        override fun click(position: Int) {
                                            var bundle = bundleOf("amount" to "" + outletData[position]!!.id)
                                            findNavController().navigate(R.id.shopInfoFragment, bundle)
                                            deleteDialog.dismiss()
                                        }
                                    })

                                    deleteDialog.findViewById<ImageView>(R.id.cancel).setOnClickListener {
                                        //delete
                                        deleteDialog.dismiss()
                                    }
                                    val layoutmanager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
                                    val list = deleteDialog.findViewById<SuperRecyclerView>(R.id.sub_caty)
                                    list.setLayoutManager(layoutmanager)
                                    list.adapter = adapter


                                }
                                else {
                                    var bundle = bundleOf("amount" to "" + data[postion]!!.id)
                                    findNavController().navigate(R.id.shopInfoFragment, bundle)
                                }
                            }
                        })
                        rvTopNewShops.setLayoutManager(GridLayoutManager(context, 2))
                        rvTopNewShops.adapter = promoadapter
                    } else {
                        binding.rvTopNewShops.visibility = View.GONE
//                        binding.rvTopNewShops.isLoadingMore = false
//
//                        Snackbar.make(view!!,getString(R.string.empty_list),Snackbar.LENGTH_LONG).show()
                        no_items.text = activity!!.resources.getString(R.string.no_cafes)
                        binding.rvTopNewShops.visibility = View.GONE
                    }
                }
            } else {
//                load_more.visibility = View.GONE
                Toast.makeText(context, activity!!.resources.getString(R.string.error), Toast.LENGTH_LONG).show()
            }
        })

//        rvTopNewShops.setOnMoreListener { overallItemsCount, itemsBeforeMore, maxLastVisiblePosition ->
//            loadnew()
//        }
//        rvTopNewShops.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                val visibleItemCount = layoutMsanager.getChildCount()
//                val totalItemCount = layoutMsanager.getItemCount()
//                val firstVisibleItemPosition = layoutMsanager.findFirstVisibleItemPosition()
//
////                        if (!isLoading() && !isLastPage()) {
//                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
//                    loadnew()
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
