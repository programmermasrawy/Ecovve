package com.q8intouch.ecovve.ui.shop_info

//import com.q8intouch.ecovve.R.id.fakeMenuList
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentShopInfoBinding
import com.q8intouch.ecovve.util.URLs
import kotlinx.android.synthetic.main.fragment_shop_info.*
import kotlinx.android.synthetic.main.fragment_shop_info_info.*
import kotlinx.android.synthetic.main.fragment_shop_info_menu.*
import kotlinx.android.synthetic.main.fragment_shop_info_reviews.*
import com.google.android.material.snackbar.Snackbar
import com.q8intouch.ecovve.network.model.EcovveCafeInfo
import com.q8intouch.ecovve.network.model.EcovveCafeInfoBrand
import com.q8intouch.ecovve.ui.offers.OfferItemsFragment
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.extension.errorResponse
import kotlinx.android.synthetic.main.app_bar_home.*
import java.text.DecimalFormat
import java.util.*

class ShopInfoFragment : BaseFragment<ShopInfoViewModel, FragmentShopInfoBinding>() {
    override fun getLayoutRes(): Int {
        return com.q8intouch.ecovve.R.layout.fragment_shop_info
    }

    var data: EcovveCafeInfo.Data? = null
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = arguments!!.getString("amount")
        val titles = ArrayList<String>()
        for (i in 0 until shopInfoTabs.tabCount) {
            val tab = shopInfoTabs.getTabAt(i)
            titles.add(tab!!.text.toString())
        }
        val dialog = LoadingDialog.showDialog(view!!.context)
        viewModel.cafeInfo(id).observe(this, Observer { it ->
            if (it.isSuccess) {
                if (it.resource!!.data!!.is_open!!)
                    binding.layoutCafeInfo.txtShopOpenStatus.text = context!!.resources.getString(com.q8intouch.ecovve.R.string.open)
                else {
                    binding.layoutCafeInfo.txtShopOpenStatus.setTextColor(context!!.resources.getColor(com.q8intouch.ecovve.R.color.danger))
                    binding.layoutCafeInfo.txtShopOpenStatus.text = context!!.resources.getString(com.q8intouch.ecovve.R.string.close)
                }

                binding.layoutCafeInfo.txtShopName.text = it.resource!!.data!!.name
                binding.layoutCafeInfo.txtShopDescription.text = it.resource!!.data!!.brand!!.description
                binding.layoutCafeInfo.txtPayBy.text = ""
                if (it.resource!!.data!!.brand!!.supports_knet == 1) {
                    binding.layoutCafeInfo.txtPayBy.append(context!!.getString(R.string.k_net))
                }
                if (it.resource!!.data!!.brand!!.supports_cc == 1) {
                    binding.layoutCafeInfo.txtPayBy.append("," + context!!.getString(R.string.visa_card))
                }
                binding.layoutCafeInfo.txtNumOfReviews.text = "" + it.resource!!.data!!.reviews_count

                binding.layoutCafeInfo.txtAvgTime.text = "" + it.resource!!.data!!.avg_delivery_time + context!!.resources.getString(R.string.mint)

                binding.layoutCafeInfo.txtMinOrder.text = "" + it.resource!!.data!!.brand!!.minimum_charge + context!!.resources.getString(R.string.kd)
                binding.layoutCafeInfo.txtDelivery.text = "" + it.resource!!.data!!.avg_delivery_fee + context!!.resources.getString(R.string.kd)
                binding.lblShopName.text = "" + it.resource!!.data!!.name
                if (!it.resource!!.data!!.brand!!.logo.toString().contains("http"))
                    Glide.with(context!!).load(URLs.IMAGES_URL + "" + it.resource!!.data!!.brand!!.logo).into(binding.layoutCafeInfo.imgShopLogo)
                else
                    Glide.with(context!!).load("" + it.resource!!.data!!.brand!!.logo).into(binding.layoutCafeInfo.imgShopLogo)

                if (!it.resource!!.data!!.brand!!.cover.toString().contains("http")) {
                    var collapseToolbar = activity!!.findViewById<CollapsingToolbarLayout>(R.id.collapseToolbar)
                    val collapsibleToolbarImage = collapseToolbar.findViewById<ImageView>(R.id.imgToolbar)

                    Glide.with(context!!).load(URLs.IMAGES_URL + "" + it.resource!!.data!!.brand!!.cover).into(collapsibleToolbarImage)

                } else {
                    var collapseToolbar = activity!!.findViewById<CollapsingToolbarLayout>(R.id.collapseToolbar)
                    val collapsibleToolbarImage = collapseToolbar.findViewById<ImageView>(R.id.imgToolbar)

                    Glide.with(context!!).load(it.resource!!.data!!.brand!!.cover).into(collapsibleToolbarImage)
                }
                val myFormatter = DecimalFormat("############")
                binding.layoutCafeInfo.rating.progress = Integer.parseInt("" + myFormatter.format(it.resource!!.data!!.reviews_rating!!))
                binding.layoutCafeInfo.txtNumOfReviews.text = "(" + it.resource!!.data!!.reviews_count + ")"
                data = it.resource!!.data

                viewModel.cafeMenu("" + data!!.brand_id).observe(this, Observer {
                    dialog.dismiss()
                    if (it.isSuccess) {
                        val shopInfoPage = view.findViewById<ViewPager>(R.id.shopInfoPager)
                        val adapter = viewpagerApater(context!!, childFragmentManager, titles, data!!, it.resource!!.data.menus, id)
                        shopInfoPage.adapter = adapter
                        shopInfoTabs.setupWithViewPager(shopInfoPager)
                        shopInfoPage.currentItem = 0
                        shopInfoPage.offscreenPageLimit = 4
                    } else {
                        Log.e("jkdjd", it.errorResponse().message)
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                        Toast.makeText(context, "" + it.error.toString(), Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Log.e("magnam", it.errorResponse().message)
                dialog.dismiss()
            }
        })
    }

    class viewpagerApater(
            val con: Context,
            val fm: FragmentManager,
            val shopInfoTabs: ArrayList<String>,
            val data: EcovveCafeInfo.Data,
            val Menu: EcovveCafeInfoBrand.Data.Menus,
            val id: String?
    ) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    OfferItemsFragment.newInstance("" + id)
                }
                1 -> {
                    InfoTabFragment.newInstance(com.q8intouch.ecovve.R.layout.fragment_shop_info_menu, data, Menu, id)
                }
                2 -> {
                    InfoTabFragment.newInstance(com.q8intouch.ecovve.R.layout.fragment_shop_info_reviews, data, Menu, id)
                }
                else -> {
                    InfoTabFragment.newInstance(com.q8intouch.ecovve.R.layout.fragment_shop_info_info, data, Menu, id)

                }
            }
        }

        override fun getCount(): Int {
            return 4
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return shopInfoTabs[position]
        }

    }

    @SuppressLint("ValidFragment")
    class InfoTabFragment : Fragment() {
        var layout: Int = 0
        var data: EcovveCafeInfo.Data? = null
        var menu: EcovveCafeInfoBrand.Data.Menus? = null

        companion object {
            fun newInstance(
                    layout: Int,
                    datamain: EcovveCafeInfo.Data,
                    Menu: EcovveCafeInfoBrand.Data.Menus,
                    id: String?
            ): InfoTabFragment {
                val frag = InfoTabFragment()
                val bundle = Bundle()
                bundle.putInt("layout", layout)
                bundle.putParcelable("data", datamain!!)
                bundle.putParcelable("item", Menu)
                frag.arguments = bundle
                return frag
            }
        }

        override fun onCreateView(
                inflater: LayoutInflater, container: ViewGroup?,
                savedInstanceState: Bundle?
        ): View? {
            layout = arguments!!.getInt("layout")
            data = arguments!!.getParcelable("data")
            menu = arguments!!.getParcelable("item")
            return if (layout != null)
                inflater.inflate(layout, container, false)
            else inflater.inflate(R.layout.fragment_shop_info_info, container, false)
        }

        @SuppressLint("WrongConstant", "SetTextI18n")
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            Log.e("magnam", "onviewcreted")
            if (data != null) {
                if (layout == com.q8intouch.ecovve.R.layout.fragment_shop_info_info) {
                    if (data!!.is_open!!)
                        txtStatus.text = context!!.resources.getString(com.q8intouch.ecovve.R.string.open)
                    else {
                        txtStatus.setTextColor(context!!.resources.getColor(com.q8intouch.ecovve.R.color.danger))
                        txtStatus.text = context!!.resources.getString(com.q8intouch.ecovve.R.string.close)
                    }
                    txtArea.text = data!!.address
                    txtCuisines.text = data!!.brand!!.extra_information

                    if (data!=null && data!!.working_days!=null) {
                        val reviewAdapter = WorkingDaysAdapter(data!!.working_days!!, context!!, object :
                                WorkingDaysAdapter.ControlsListeners {
                            override fun click(postion: Int) {

                            }
                        })

                    workingDays.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    workingDays.adapter = reviewAdapter
                    }
//                    txtOpenHours.text = ""+data!!.opening + " - " + data!!.closing
                    txtDeliveryFee.text = data!!.tax
                    txtDeliveryTime.text = "" + data!!.avg_delivery_time + "" + getString(R.string.mint)
                    txtMinimumOrder.text = "" + data!!.brand!!.minimum_charge + getString(R.string.kd)
                    txtDeliveryFee.text = "" + data!!.avg_delivery_fee + getString(R.string.kd)

                    txtPaymentOptions.text = ""
                    if (data!!.brand!!.supports_knet == 1) {
                        txtPaymentOptions.append(context!!.getString(R.string.k_net))
                    }

                    if (data!!.brand!!.supports_cc == 1) {
                        txtPaymentOptions.append("," + context!!.getString(R.string.visa_card))
                    }

                } else if (layout == com.q8intouch.ecovve.R.layout.fragment_shop_info_menu) {
                    val menuAdapter = MenuAdapter(menu!!.data, context!!, object :
                            MenuAdapter.ControlsListeners {
                        override fun click(postion: Int) {
                            var bundle = Bundle()
                            bundle.putString("amount", "" + menu!!.data.get(postion).id)
                            bundle.putString("title", "" + menu!!.data.get(postion).name)

                            findNavController().navigate(
                                    R.id.action_shopInfoFragment_to_menuCategoryFragment,
                                    bundle
                            )
                        }
                    })
                    menus.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    menus.adapter = menuAdapter
                } else {
                    packaging.text = "95%"
                    delTime.text = "60%"
                    quality.text = "90%"
                    valueOfMoney.text = "70%"
                    val reviewAdapter = ReviewAdapter(data!!.review!!, context!!, object :
                            ReviewAdapter.ControlsListeners {
                        override fun click(postion: Int) {

                        }
                    })
                    shop_info_review.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    shop_info_review.adapter = reviewAdapter
                }
            }
        }
    }
}
