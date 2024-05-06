package com.q8intouch.ecovve.ui.search

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.q8intouch.ecovve.base.BaseFragment

import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.search_view_result.*
import com.q8intouch.ecovve.network.model.EcovveAllCategoryResponse
import com.q8intouch.ecovve.network.model.EcovveLatLonSearch
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.NavOptions
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.databinding.FragmentSearchBinding
import com.q8intouch.ecovve.databinding.FragmentShopInfoBinding
import com.q8intouch.ecovve.ui.shop_info.ShopInfoFragment
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.Shared


class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_search
    }


    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
//        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }


    var Clist = ArrayList<EcovveLatLonSearch.Data.Categories.DataCategory>()
    lateinit var longtideAndLat: ArrayList<String>
    lateinit var arrayItems: List<EcovveAllCategoryResponse.Data>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val sharedPreference: Shared = Shared(context!!)
        sharedPreference.removeValue("searchInfoFragment")
        sharedPreference.save("goToHome", "goToHome")

        val shopInfoPager = view!!.findViewById<ViewPager>(R.id.shopInfoPager)
        val shopInfoTabs = view!!.findViewById<TabLayout>(R.id.shopInfoTabs)
        shopInfoTabs.removeAllTabs()
        shopInfoPager.removeAllViews()
        shopInfoPager.adapter = null
        shopInfoTabs.setupWithViewPager(null)
        Clist = ArrayList<EcovveLatLonSearch.Data.Categories.DataCategory>()
        val dialog = LoadingDialog.showDialog(view!!.context)
        longtideAndLat = arguments!!.getStringArrayList("longtideAndLat")
        val o = EcovveLatLonSearch.Data.Categories.DataCategory(0, context!!.resources.getString(R.string.all_categories))
        Clist.add(o)

        if (longtideAndLat.isNotEmpty()) {
            if (longtideAndLat.get(0) == "" && longtideAndLat[4].equals("") && longtideAndLat[1] == "") {

                viewModel.searchLatLon(context!!, "", 1).observe(this, Observer {
                    dialog.dismiss()
                    if (it.isSuccess) {
                        if (it.resource!!.data.categories.data.isNotEmpty()) {
                            Clist.addAll(it.resource!!.data.categories.data)
                            shopInfoPager.adapter = viewpagerApater2(
                                    context!!,
                                    childFragmentManager,
                                    it.resource!!.data.outlets.data,
                                    it.resource!!.data.categories,
                                    Clist, longtideAndLat as java.util.ArrayList<String>, shopInfoTabs
                            )
                            shopInfoTabs.setupWithViewPager(shopInfoPager)
//                            shopInfoPager.offscreenPageLimit = it.resource!!.data.categories.data.size
                            shopInfoPager.currentItem = 0
                        } else {
                            no_items.text = resources.getString(R.string.no_cafes)
                            shopInfoTabs.visibility = View.GONE
                        }
                    } else {
                        Toast.makeText(context, "" + it.error.toString(), Toast.LENGTH_LONG).show()
                    }
                })
            } else if (longtideAndLat[0] != "" && longtideAndLat[1] == "" && longtideAndLat[4] == "") {
                viewModel.searchLatLon(longtideAndLat.get(0), context!!, 1).observe(this, Observer {
                    dialog.dismiss()
                    if (it.isSuccess) {

                        if (it.resource!!.data.categories.data.isNotEmpty()) {
                            Clist.addAll(it.resource!!.data.categories.data)
                            shopInfoPager.adapter = viewpagerApater2(
                                    context!!,
                                    childFragmentManager,
                                    it.resource!!.data.outlets.data,
                                    it.resource!!.data.categories,
                                    Clist, longtideAndLat as java.util.ArrayList<String>, shopInfoTabs
                            )
                            shopInfoTabs.setupWithViewPager(shopInfoPager)
                            shopInfoPager.currentItem = 0
//                            shopInfoPager.offscreenPageLimit = it.resource!!.data.categories.data.size
                        } else {
                            no_items.text = resources.getString(R.string.no_cafes)
                            shopInfoTabs.visibility = View.GONE
                        }
                    }
                })
            } else if (longtideAndLat.get(0) == "" && longtideAndLat.get(4) != "") {
                viewModel.searchLatLon(longtideAndLat.get(4), context!!, 1, "").observe(this, Observer {
                    dialog.dismiss()
                    if (it.isSuccess) {

                        if (it.resource!!.data.categories.data.isNotEmpty()) {
                            Clist.addAll(it.resource!!.data.categories.data)
                            shopInfoPager.adapter = viewpagerApater2(
                                    context!!,
                                    childFragmentManager,
                                    it.resource!!.data.outlets.data,
                                    it.resource!!.data.categories,
                                    Clist, longtideAndLat as java.util.ArrayList<String>, shopInfoTabs
                            )
                            shopInfoTabs.setupWithViewPager(shopInfoPager)
                            shopInfoPager.currentItem = 0
//                            shopInfoPager.offscreenPageLimit = it.resource!!.data.categories.data.size
                        } else {
                            no_items.text = resources.getString(R.string.no_cafes)
                            shopInfoTabs.visibility = View.GONE
                        }
                    }
                })
            } else if (longtideAndLat.get(1) != "" && longtideAndLat.get(0) == "") {
                viewModel.searchLatLon(longtideAndLat.get(1), longtideAndLat.get(2), longtideAndLat.get(3), context!!, 1)
                        .observe(this, Observer {
                            dialog.dismiss()
                            if (it.isSuccess) {
                                if (it.resource!!.data.categories.data.isNotEmpty()) {
                                    Clist.addAll(it.resource!!.data.categories.data)
                                    shopInfoPager.adapter = viewpagerApater2(
                                            context!!,
                                            childFragmentManager,
                                            it.resource!!.data.outlets.data,
                                            it.resource!!.data.categories,
                                            Clist, longtideAndLat as java.util.ArrayList<String>, shopInfoTabs
                                    )
                                    shopInfoTabs.setupWithViewPager(shopInfoPager)
                                    shopInfoPager.currentItem = 0
                                    shopInfoPager.offscreenPageLimit = it.resource!!.data.categories.data.size
                                } else {
                                    no_items.text = resources.getString(R.string.no_cafes)
                                    shopInfoTabs.visibility = View.GONE
                                }
                            }
                        })
            } else {
                viewModel.searchLatLon(
                        longtideAndLat[0],
                        longtideAndLat[4],
                        context!!
                        , 1
                ).observe(this, Observer {
                    dialog.dismiss()
                    if (it.isSuccess) {
                        if (it.resource!!.data.categories.data.isNotEmpty()) {
                            Clist.addAll(it.resource!!.data.categories.data)
                            shopInfoPager.adapter = viewpagerApater2(
                                    context!!,
                                    childFragmentManager,
                                    it.resource!!.data.outlets.data,
                                    it.resource!!.data.categories
                                    , Clist, longtideAndLat as java.util.ArrayList<String>
                                    , shopInfoTabs
                            )
                            shopInfoTabs.setupWithViewPager(shopInfoPager)
                            shopInfoPager.currentItem = 0
//                            shopInfoPager.offscreenPageLimit = it.resource!!.data.categories.data.size
                        } else {
                            no_items.text = resources.getString(R.string.no_cafes)
                            shopInfoTabs.visibility = View.GONE
                        }
                    }
                })
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.filter, menu)
        super.onCreateOptionsMenu(menu!!, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        initViews()
        super.onResume()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.menuFilter -> {
                var bundle = org.jetbrains.anko.bundleOf("longtideAndLat" to longtideAndLat)
//                findNavController().navigate(R.id.action_homeFragment_to_searchFragment, bundle)
//                var intent = Intent(activity!!,FilterActivity::class.java)
//                intent.putStringArrayListExtra("longtideAndLat",longtideAndLat)
//                startActivity(intent)
                findNavController().navigate(R.id.filterFragment, bundle)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    class viewpagerApater2(
            val context: Context,
            val fma: FragmentManager,
            val data: List<EcovveLatLonSearch.Data.Outlets.DataOutlet>,
            val categoryItems: EcovveLatLonSearch.Data.Categories?,
            val clist: ArrayList<EcovveLatLonSearch.Data.Categories.DataCategory>?,
            val conditions: ArrayList<String>?,
            val shopInfoTabs1: TabLayout
    ) : FragmentStatePagerAdapter(fma) {
        override fun getItem(position: Int): Fragment {
            if (position == 0)
                return InfoFragment2(data, categoryItems, conditions!!, shopInfoTabs1, -1)
            else if (position == clist!!.size) {
                return InfoFragment2(data, categoryItems, conditions!!, shopInfoTabs1, clist.get(position - 1).id!!)
            } else {
                return InfoFragment2(data, categoryItems, conditions!!, shopInfoTabs1, clist.get(position - 1).id!!)
            }
        }

        override fun getCount(): Int {
            return clist!!.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            if (clist!!.get(position).name != null)
                return clist!!.get(position).name
            else
                return context.getString(R.string.category)
        }
    }

    @SuppressLint("ValidFragment")
    class InfoFragment2(
            var result: List<EcovveLatLonSearch.Data.Outlets.DataOutlet>,
            var data: EcovveLatLonSearch.Data.Categories?,
            var conditions: ArrayList<String>,
            var tabLayout: TabLayout,
            val position: Int
    ) : BaseFragment<ItemsViewModel, FragmentShopInfoBinding>() {
        override fun getLayoutRes(): Int {
            return R.layout.search_view_result
        }

        var page = 0
        @SuppressLint("WrongConstant")
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            page = 0

            val layoutMsanager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
            )
            loadSearch(layoutMsanager)

        }

        lateinit var menuAdapter: ResultDetailsAdapter
        var searchResult = ArrayList<EcovveLatLonSearch.Data.Outlets.DataOutlet>()

        @SuppressLint("WrongConstant", "SetTextI18n")
        private fun loadSearch(layoutMsanager: LinearLayoutManager) {
            load_more.visibility = View.GONE
            if (position == -1) {
                items.adapter = null
                menuAdapter = ResultDetailsAdapter(result, context!!, object :
                        ResultDetailsAdapter.ControlsListeners {
                    override fun click(postion: Int) {
//                        var bundle = bundleOf("amount" to "" + result.get(postion)!!.id)
                        var bundle = Bundle()
                        bundle.putString("amount", "" + "" + result.get(postion)!!.id)
                        bundle.putString("title", "" + "" + result.get(postion)!!.name)

//                        val fragmentShop = ShopInfoFragment()
//                        fragmentShop.arguments = bundle
//                        childFragmentManager.beginTransaction().add(R.id.nav_home,
//                                fragmentShop, ShopInfoFragment::class.java.simpleName).commit()

                        findNavController().navigate(R.id.shopInfoFragment, bundle,
                                NavOptions.Builder()
                                        .setPopUpTo(R.id.homeAsUp, true).build())
                    }
                })
                items.setLayoutManager(layoutMsanager)
                items.adapter = menuAdapter
            } else {
                if (conditions.isNotEmpty()) {
                    if (conditions.get(0).equals("") && conditions.get(1).equals("")
                            && conditions.get(2).equals("") && conditions.get(3).equals("") && conditions.get(4).equals("")
                    ) {

                        viewModel.searchLatLon("" + position, context!!, ++page)
                                .observe(this, Observer {
                                    if (it.isSuccess) {
                                        load_more.visibility = View.GONE

                                        if (page == 1) {
                                            searchResult = ArrayList<EcovveLatLonSearch.Data.Outlets.DataOutlet>()
                                            searchResult.addAll(it.resource!!.data.outlets.data)
                                        } else {
                                            searchResult.addAll(it.resource!!.data.outlets.data)
                                            menuAdapter.notifyDataSetChanged()
                                        }
                                        txtNumOfCafe.text = "" + searchResult.size + " " + resources.getString(R.string.cafe_found)
                                        if (page == 1) {
                                            menuAdapter = ResultDetailsAdapter(
                                                    searchResult,
                                                    context!!,
                                                    object :
                                                            ResultDetailsAdapter.ControlsListeners {
                                                        override fun click(postion: Int) {
                                                            var bundle = bundleOf(
                                                                    "amount" to "" + it.resource!!.data.outlets.data!!.get(postion)!!.id
                                                            )

                                                            findNavController().navigate(
                                                                    R.id.action_searchFragment_to_shopInfoFragment,
                                                                    bundle
                                                            )
                                                        }

                                                    })
                                            items.setLayoutManager(layoutMsanager)
                                            items.adapter = menuAdapter
                                        }
                                    } else {
                                        load_more.visibility = View.GONE
                                    }
                                })
                    }
                } else
                    if (conditions.get(0).isNotEmpty() && conditions.get(1) == "") {
                        viewModel.searchLatLon("" + position, context!!, ++page)
                                .observe(this, Observer {
                                    if (it.isSuccess) {
                                        load_more.visibility = View.GONE
                                        if (page == 1) {
                                            searchResult = ArrayList<EcovveLatLonSearch.Data.Outlets.DataOutlet>()
                                            searchResult.addAll(it.resource!!.data.outlets.data)
                                        } else {
                                            searchResult.addAll(it.resource!!.data.outlets.data)
                                            menuAdapter.notifyDataSetChanged()
                                        }
                                        txtNumOfCafe.text = "" + searchResult.size + " " + resources.getString(R.string.cafe_found)

                                        if (page == 1) {
                                            menuAdapter = ResultDetailsAdapter(
                                                    searchResult,
                                                    context!!,
                                                    object :
                                                            ResultDetailsAdapter.ControlsListeners {
                                                        override fun click(postion: Int) {
                                                            var bundle = bundleOf(
                                                                    "amount" to "" + it.resource!!.data.outlets.data!!.get(postion)!!.id
                                                            )

                                                            findNavController().navigate(
                                                                    R.id.action_searchFragment_to_shopInfoFragment,
                                                                    bundle
                                                            )
                                                        }

                                                    })
                                            items.setLayoutManager(layoutMsanager)
                                            items.adapter = menuAdapter
                                        }
                                    } else {
                                        load_more.visibility = View.GONE
                                    }
                                })
                    } else if (conditions.get(1).isNotEmpty() && conditions.get(0) == "") {
                        viewModel.searchLatLon(conditions.get(1), conditions.get(2), conditions.get(3), context!!, ++page)
                                .observe(this, Observer {
                                    if (it.isSuccess) {
                                        load_more.visibility = View.GONE
                                        if (page == 1) {
                                            searchResult = ArrayList<EcovveLatLonSearch.Data.Outlets.DataOutlet>()
                                            searchResult.addAll(it.resource!!.data.outlets.data)
                                        } else {
                                            searchResult.addAll(it.resource!!.data.outlets.data)
                                            menuAdapter.notifyDataSetChanged()
                                        }
                                        txtNumOfCafe.text = "" + searchResult.size + " " + resources.getString(R.string.cafe_found)

                                        if (page == 1) {
                                            menuAdapter = ResultDetailsAdapter(
                                                    searchResult,
                                                    context!!,
                                                    object :
                                                            ResultDetailsAdapter.ControlsListeners {
                                                        override fun click(postion: Int) {
                                                            var bundle = bundleOf(
                                                                    "amount" to "" + it.resource!!.data.outlets.data!!.get(postion)!!.id
                                                            )

                                                            findNavController().navigate(
                                                                    R.id.action_searchFragment_to_shopInfoFragment,
                                                                    bundle
                                                            )
                                                        }

                                                    })
                                            items.setLayoutManager(layoutMsanager)
                                            items.adapter = menuAdapter
                                        }
                                    } else {
                                        load_more.visibility = View.GONE
                                    }
                                })
                    } else if (conditions.get(4).isNotEmpty() && conditions.get(0) == "") {
                        viewModel.searchLatLon("" + conditions.get(4), context!!, ++page, "")
                                .observe(this, Observer {
                                    if (it.isSuccess) {
                                        load_more.visibility = View.GONE
                                        if (page == 1) {
                                            searchResult = ArrayList<EcovveLatLonSearch.Data.Outlets.DataOutlet>()
                                            searchResult.addAll(it.resource!!.data.outlets.data)
                                        } else {
                                            searchResult.addAll(it.resource!!.data.outlets.data)
                                            menuAdapter.notifyDataSetChanged()
                                        }

                                        txtNumOfCafe.setText("" + searchResult.size + " " + resources.getString(R.string.cafe_found))
                                        if (page == 1) {
                                            menuAdapter = ResultDetailsAdapter(
                                                    searchResult,
                                                    context!!,
                                                    object :
                                                            ResultDetailsAdapter.ControlsListeners {
                                                        override fun click(postion: Int) {
                                                            var bundle = bundleOf(
                                                                    "amount" to "" + it.resource!!.data.outlets.data!!.get(postion)!!.id
                                                            )

                                                            findNavController().navigate(
                                                                    R.id.action_searchFragment_to_shopInfoFragment,
                                                                    bundle
                                                            )
                                                        }

                                                    })
                                            items.setLayoutManager(layoutMsanager)
                                            items.adapter = menuAdapter
                                        }
                                    } else load_more.visibility = View.GONE
                                })
                    } else if (conditions.get(4).isNotEmpty() && conditions.get(0).isNotEmpty()) {
                        viewModel.searchLatLon("" + conditions.get(0), "" + conditions.get(4), context!!, ++page)
                                .observe(this, Observer {
                                    if (it.isSuccess) {
                                        load_more.visibility = View.GONE
                                        if (page == 1) {
                                            searchResult = ArrayList<EcovveLatLonSearch.Data.Outlets.DataOutlet>()
                                            searchResult.addAll(it.resource!!.data.outlets.data)
                                        } else {
                                            searchResult.addAll(it.resource!!.data.outlets.data)
                                            menuAdapter.notifyDataSetChanged()
                                        }

                                        txtNumOfCafe.setText("" + searchResult.size + " " + resources.getString(R.string.cafe_found))
                                        if (page == 1) {
                                            menuAdapter = ResultDetailsAdapter(
                                                    searchResult,
                                                    context!!,
                                                    object :
                                                            ResultDetailsAdapter.ControlsListeners {
                                                        override fun click(postion: Int) {
                                                            var bundle = bundleOf(
                                                                    "amount" to "" + it.resource!!.data.outlets.data!!.get(postion)!!.id
                                                            )

                                                            findNavController().navigate(
                                                                    R.id.action_searchFragment_to_shopInfoFragment,
                                                                    bundle
                                                            )
                                                        }

                                                    })
                                            items.setLayoutManager(layoutMsanager)
                                            items.adapter = menuAdapter
                                        }
                                    } else load_more.visibility = View.GONE
                                })
                    }

            }
//            items.isNestedScrollingEnabled = true;
//            items.setHasFixedSize(false);
//            items.setupMoreListener(OnMoreListener { numberOfItems, numberBeforeMore, currentItemPos ->
//                loadSearch()
//            }, 10)
//            layoutMsanager.isSmoothScrollbarEnabled = true
            items.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val visibleItemCount = layoutMsanager.getChildCount()
                    val totalItemCount = layoutMsanager.getItemCount()
                    val firstVisibleItemPosition = layoutMsanager.findFirstVisibleItemPosition()

//                    if (visibleItemCount==0){
//                        tabLayout.visibility = View.VISIBLE
//                    }
//                    if (visibleItemCount>2){
//                        tabLayout.visibility = View.GONE
//                    }
//
//                    if (firstVisibleItemPosition < 3){
//                        tabLayout.visibility = View.VISIBLE
//                    }
//                    if (firstVisibleItemPosition>2){
//                        tabLayout.visibility = View.GONE
//                    }
//                        if (!isLoading() && !isLastPage()) {
                    if (firstVisibleItemPosition == totalItemCount - 1 && firstVisibleItemPosition >= 0) {
                        loadSearch(layoutMsanager)
                        load_more.visibility = View.VISIBLE
//                            }
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)


                }
            })


        }
    }
}
