package com.q8intouch.ecovve.ui.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.AudioManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.q8intouch.ecovve.util.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.model.ShopCart
import com.q8intouch.ecovve.databinding.FragmentHomeBinding
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.ui.home.slider.SliderAdapter
import com.q8intouch.ecovve.ui.maps.MapsAddressActivity
import com.q8intouch.ecovve.util.AppUtils
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.URLs
import com.ramotion.cardslider.CardSliderLayoutManager
import com.ramotion.cardslider.CardSnapHelper
import com.sinch.android.rtc.PushPair
import com.sinch.android.rtc.Sinch
import com.sinch.android.rtc.SinchClient
import com.sinch.android.rtc.SinchError
import com.sinch.android.rtc.calling.Call
import com.sinch.android.rtc.calling.CallClient
import com.sinch.android.rtc.calling.CallClientListener
import com.sinch.android.rtc.calling.CallListener
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.dailog_add_to_cart.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.onClick
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    val myFormatter = DecimalFormat("############")
    private var plusQuantity: FloatingActionButton? = null
    private var minusQuantity: FloatingActionButton? = null
    private var txtTotal: TextView? = null
    private var units: TextView? = null
    lateinit var arrayItems: List<City>
    var CityId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val android_id = Settings.Secure.getString(activity!!.contentResolver,
                Settings.Secure.ANDROID_ID)
        Log.e("android_id", android_id)
        var sinchClient = Sinch.getSinchClientBuilder().context(activity!!)
                .applicationKey(com.q8intouch.ecovve.util.Constants.SNICH_KEY)
                .applicationSecret(com.q8intouch.ecovve.util.Constants.SNICH_SECRET)
                .environmentHost(com.q8intouch.ecovve.util.Constants.SNICH_HOST_NAME)
                .userId(android_id)
                .build()
        sinchClient.setSupportCalling(true)
        sinchClient.setSupportManagedPush(true)
        sinchClient.setSupportPushNotifications(true)
        sinchClient.setSupportActiveConnectionInBackground(true)
        sinchClient.startListeningOnActiveConnection()
        sinchClient.start()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CityId = ""
        CatID = -1
        if (isInternetAvailable(context!!)) {
            val dialog = LoadingDialog.showDialog(view!!.context)
            initViews(dialog)
        } else {
            val factory = LayoutInflater.from(activity!!)
            val addToCartDialogView = factory.inflate(R.layout.no_internet, null)
            val deleteDialog = android.app.AlertDialog.Builder(activity!!).create()
            deleteDialog.setView(addToCartDialogView)
            deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            deleteDialog.show()

            deleteDialog.findViewById<AppCompatButton>(R.id.cancel).setOnClickListener {
                //delete
                activity!!.recreate()
                deleteDialog.dismiss()
            }

        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        var connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager
                .getActiveNetworkInfo().isConnected();
    }

    private fun initViews(dialog: Dialog) {
        val sharedPreference: Shared = Shared(context!!)
        sharedPreference.removeValue("newcafes")
        sharedPreference.removeValue("mastercase")
        sharedPreference.removeValue("knetcase")
        sharedPreference.removeValue("opencase")
        sharedPreference.removeValue("freedeliverycase")
        sharedPreference.removeValue("offerscase")
        sharedPreference.removeValue("sort")
        sharedPreference.removeValue("goToHome")

        var id = activity!!.intent.extras.getString("amount", "")
        var lat = sharedPreference.getValueString("lat")
        var lon = sharedPreference.getValueString("lon")

        if (lat != null && lon != null) {
            var longtideAndLat = ArrayList<String>()
            longtideAndLat.add("")
            longtideAndLat.add(lat)
            longtideAndLat.add(lon)
            longtideAndLat.add("500")

            sharedPreference.removeValue("lat")
            sharedPreference.removeValue("lon")
            Log.e("mammsmp", activity!!.intent.extras.getString("amount", ""))

            longtideAndLat.add("")
            var bundle = bundleOf("longtideAndLat" to longtideAndLat)
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment, bundle)
            dialog.dismiss()
        } else {
            binding.btnSearchCafe.onClick { it ->
                if (CatID == -1 && CityId == "") {
                    var longtideAndLat = ArrayList<String>()
                    longtideAndLat.add("")
                    longtideAndLat.add("")
                    dialog.dismiss()
                    longtideAndLat.add("")
                    longtideAndLat.add("")
                    longtideAndLat.add("")
                    var bundle = bundleOf("longtideAndLat" to longtideAndLat)
                    findNavController().navigate(R.id.action_homeFragment_to_searchFragment, bundle)
                } else if (CatID == -1 && CityId != "") {
                    var longtideAndLat = ArrayList<String>()
                    longtideAndLat.add("")
                    longtideAndLat.add("")
                    longtideAndLat.add("")
                    longtideAndLat.add("")
                    longtideAndLat.add(CityId)

                    var bundle = bundleOf("longtideAndLat" to longtideAndLat)
                    findNavController().navigate(R.id.action_homeFragment_to_searchFragment, bundle)
                    dialog.dismiss()
                } else if (CatID != -1 && CityId == "") {
                    var longtideAndLat = ArrayList<String>()
                    longtideAndLat.add("" + Category_ID)
                    longtideAndLat.add("")
                    longtideAndLat.add("")
                    longtideAndLat.add("")
                    longtideAndLat.add("")

                    var bundle = bundleOf("longtideAndLat" to longtideAndLat)
                    dialog.dismiss()
                    findNavController().navigate(R.id.action_homeFragment_to_searchFragment, bundle)
                } else {
                    var longtideAndLat = ArrayList<String>()
                    longtideAndLat.add("" + Category_ID)
                    longtideAndLat.add("")
                    longtideAndLat.add("")
                    longtideAndLat.add("")
                    longtideAndLat.add(CityId)

                    var bundle = bundleOf("longtideAndLat" to longtideAndLat)
                    dialog.dismiss()
                    findNavController().navigate(R.id.action_homeFragment_to_searchFragment, bundle)
                }
            }

            mapcity.onClick {
                val intent = Intent(activity, MapsAddressActivity::class.java)
                intent.putExtra("amount", "" + id)
                startActivity(intent)
            }
            binding.alloffers.onClick {
                findNavController().navigate(R.id.action_homeFragment_to_offersFragment)
            }

        }
        area(dialog)
    }

    lateinit var choiceAdapter: ChoicesHabitsAdapter
    var extraList = ArrayList<String>()
    var extraListMost = ArrayList<String>()
    var extraModel = ArrayList<ExtraModel>()
    var extraModelMost = ArrayList<ExtraModelItems>()
    var adapter: ExtraHabitsAdapter? = null
    private fun userHabits(dialog: Dialog) {
        if (view != null) {
            var id = activity!!.intent.extras.getString("amount", "")
            viewModel.userHabits(id).observe(this, Observer {
                if (it.isSuccess) {
                    val suborder = it.resource!!.data

                    if (suborder.size == 0) {
                        binding.prevorders.visibility = View.GONE
                        binding.lblPreviousOrders.visibility = View.GONE
                    } else {
                        extraList = ArrayList()
                        extraModel = ArrayList()
                        var data = it.resource!!.data
                        val sliderAdapter = SliderAdapter(it.resource!!.data, it.resource!!.data.size, context!!,
                                SliderAdapter.ControlsListeners {
                                    extraList = ArrayList()
                                    extraModel = ArrayList()
                                    var position = it
                                    if (data[position].extras!!.size != 0) {
                                        adapter = ExtraHabitsAdapter(data[position].extras, context!!,
                                                object : ExtraHabitsAdapter.ControlsListeners {
                                                    @SuppressLint("WrongConstant")
                                                    override fun click(id: Int, choices: RecyclerView) {
                                                        var list = ArrayList<EcovveAllHabits.Data.Choice>()
                                                        data[position].choices!!.forEachIndexed { index, choice ->
                                                            if (choice.extra_id!!.equals(id)) {
                                                                list.add(choice)
                                                            }
//
                                                        }
                                                        val Extra = ExtraModel(data[position].id!!, list, data[position].extras!![id].is_required!!)
                                                        extraModel.add(Extra)
                                                        choiceAdapter = ChoicesHabitsAdapter(list, context!!,
                                                                object : ChoicesHabitsAdapter.ControlsListeners {
                                                                    override fun click(id: Int) {
                                                                        extraList.add("" + id)
                                                                    }

                                                                    override fun remove(id: Int) {
                                                                        if (extraList.size != 0) {
                                                                            extraList.forEach { s ->
                                                                                if (s == "" + id)
                                                                                    extraList.remove(s)
                                                                            }
                                                                        }
                                                                    }
                                                                })
                                                        choices.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
//                                                                    choices.adapter = choiceAdapter
//                                                                } else {
//                                                                    Log.e("error", it.error!!.localizedMessage)
//                                                                }
//                                                            })
                                                    }
                                                })
                                    }
                                    showItemDialog(suborder, position)
//                                        } else {
//                                            Log.e("error", it.error!!.localizedMessage)
//                                        }
//                                    })
                                })
                        var layoutmam = CardSliderLayoutManager(context!!)
                        layoutmam.activeCardCenter

                        binding.prevorders.setLayoutManager(layoutmam);
                        binding.prevorders.setOnFlingListener(null);
                        CardSnapHelper().attachToRecyclerView(binding.prevorders);
//                        binding.prevorders.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                                    onActiveCardChange()
//                                }
//                            }
//                        })
                        binding.prevorders.adapter = sliderAdapter
                    }
                    dialog.dismiss()
                    user()
                } else {
                    user()
                    dialog.dismiss()
                    Toast.makeText(context, "" + it.error.toString(), Toast.LENGTH_LONG).show()

                    val sliderAdapter = SliderAdapter(5,
                            SliderAdapter.ControlsListeners {

                            })
                    var layoutmam = CardSliderLayoutManager(context!!)
                    layoutmam.activeCardLeft

                    binding.prevorders.setLayoutManager(layoutmam);
                    CardSnapHelper().attachToRecyclerView(binding.prevorders);

                    binding.prevorders.adapter = sliderAdapter
//                    getinfo(dialog)
                }
            })
        }
    }

    private fun getinfo(dialog: Dialog) {
        if (view != null) {

            viewModel.getAllBanners().observe(this, Observer {
                userHabits(dialog)
                if (it.isSuccess) {
                    val suborder = it.resource!!.data

                    if (suborder!!.size == 0) {
                        binding.prevorders.visibility = View.GONE
                        binding.lblPreviousOrders.visibility = View.GONE
                    } else {
                        extraListMost = ArrayList()
                        extraModelMost = ArrayList()
                        var data = it.resource!!.data
                        val promoadapter = PromotionAdapter(it.resource!!.data!!, context!!, object :
                                PromotionAdapter.ControlsListeners {
                            override fun click(postion: Int) {
                                var bundle = bundleOf("amount" to "" + data!!.get(postion)!!.id)
                                findNavController().navigate(R.id.shopInfoFragment, bundle)
                            }
                        })

                        val layoutmanager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                        binding.promotions.layoutManager = layoutmanager

                        binding.promotions.adapter = promoadapter
                        binding.promotions.scrollToPosition(0)
                        timer = Timer()
                        layoutmanager.scrollToPosition(0)
                        timer.schedule(object : TimerTask() {
                            override fun run() {
                                if (layoutmanager.findFirstVisibleItemPosition() < 0) {
                                    binding.promotions.smoothScrollToPosition(layoutmanager.findFirstVisibleItemPosition() - 1);
                                } else {
                                    binding.promotions.smoothScrollToPosition(layoutmanager.findLastVisibleItemPosition() + 1);
                                }
                            }
                        }, 0, 3000);

                    }
                } else {

                }
            })

        }
    }

    var timer = Timer()
    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    private fun user() {
        if (view != null) {
            var id = activity!!.getIntent().extras.getString("amount", "")
            com.q8intouch.ecovve.util.Constants.USER_ID = Integer.valueOf(id)
            val sharedPreference: Shared = Shared(context!!)
            viewModel.fetchUserData(id).observe(this, Observer {
                if (it.isSuccess) {
                    val navView = activity!!.findViewById<NavigationView>(R.id.nav_view)
                    navView.getHeaderView(0).findViewById<TextView>(R.id.txtUserFullName).text = it.resource!!.data!!.name
                    navView.getHeaderView(0).findViewById<TextView>(R.id.txtUserEmail).text = it.resource!!.data!!.email
                    if (it.resource!!.data != null) {
                        val profileImage = navView.getHeaderView(0).findViewById<CircleImageView>(R.id.imgProfilePic)
                        com.q8intouch.ecovve.util.Constants.User = it.resource!!.data!!
                        com.q8intouch.ecovve.util.Constants.POINT = ""+it.resource!!.data!!.points
                        if (it.resource!!.data.avatar != null)
                            if (!it.resource!!.data.avatar!!.contains("http"))
                                Glide.with(context!!).load(URLs.IMAGES_URL + it.resource!!.data!!.avatar!!).into(profileImage)
                            else
                                Glide.with(context!!).load(it.resource!!.data!!.avatar!!).into(profileImage)


                        sharedPreference.setList("addresses", it.resource!!.data!!.addresses!!)
                        if (it.resource!!.data.avatar != null)
                            sharedPreference.save("photo", it.resource!!.data!!.avatar!!)
                        else
                            sharedPreference.save("photo", "")

                        sharedPreference.save("name", it.resource!!.data!!.name!!)
                        if (!it.resource!!.data!!.addresses.isNullOrEmpty())
                            if (!it.resource!!.data!!.addresses!![0].name.isNullOrEmpty())
                                sharedPreference.save("user_address", it.resource!!.data.addresses!!.get(0).name!!)
                            else
                                sharedPreference.save("user_address", "")

//                        sharedPreference.save("user_friends", "" + it.resource!!.data.f!!.data.size)

//                        sharedPreference.setList("userFriends", it.resource!!.data!!.friends!!.data!!)
                        sharedPreference.setList("userNotifications", it.resource!!.data.notifications!!.data)
                        sharedPreference.setList("reviews", it.resource!!.data.reviews!!.data)
                    }
                } else {
                    Snackbar.make(view!!, it.error!!.localizedMessage, Snackbar.LENGTH_LONG).show()
                    Log.v("infoError", it.error.toString())
                    Log.e("KeyHash:", it.error!!.localizedMessage)
                }
            })
        }
    }

    lateinit var allArea: List<EcovveAllAreaOutlet.Data>
    @SuppressLint("WrongConstant")
    private fun area(dialog: Dialog) {
        if (view != null) {
            viewModel.allAreaArea().observe(this, Observer {
                if (it.isSuccess) {
                    if (it.resource!!.data!!.isNotEmpty()) {

                        val sharedPreference: Shared = Shared(context!!)
                        sharedPreference.setList("dataMap", it.resource!!.data!!)
                        var dataArea = it.resource!!.data
                        binding.city.onClick {
                            val factory = LayoutInflater.from(activity!!)
                            val addToCartDialogView = factory.inflate(R.layout.select_category_item, null)
                            val deleteDialog = android.app.AlertDialog.Builder(activity!!).create()
                            deleteDialog.setView(addToCartDialogView)
                            deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            deleteDialog.window!!.setBackgroundDrawableResource(R.drawable.tab_layout_ui);
                            deleteDialog.window!!.setGravity(Gravity.BOTTOM);
                            deleteDialog.window!!.attributes.windowAnimations = R.style.DialogAnimation2; //style id

                            deleteDialog.show()
                            deleteDialog.findViewById<TextView>(R.id.caty_name).text = resources.getString(R.string.select_your_area)
                            deleteDialog.findViewById<TextView>(R.id.reset).visibility = View.VISIBLE

                            deleteDialog.findViewById<TextView>(R.id.reset).onClick {
                                city.text = resources.getString(R.string.select_your_area)
                                CityId = ""
                                deleteDialog.dismiss()
                            }

                            var lang = URLs.lang
                            val promoadapter = CitiesAdapter(dataArea, lang, context!!, object :
                                    CitiesAdapter.ControlsListeners {
                                override fun click(id: String, name: String, position: Int) {
                                    city.text = name
                                    CityId = "" + id
                                    deleteDialog.dismiss()
                                }
                            })
                            val layoutmanager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
                            deleteDialog.findViewById<RecyclerView>(R.id.sub_caty).layoutManager = layoutmanager
                            deleteDialog.findViewById<RecyclerView>(R.id.sub_caty).adapter = promoadapter

                            deleteDialog.findViewById<ImageView>(R.id.cancel).setOnClickListener {
                                //delete
                                deleteDialog.dismiss()
                            }
                        }


                        mapcity.onClick {
                            val intent = Intent(activity, MapsAddressActivity::class.java)
                            intent.putExtra("amount", "" + activity!!.getIntent().extras.getString("amount", ""))
                            startActivity(intent)
                        }
                    }
                    categories(dialog)
                } else {
                    categories(dialog)
                    Log.v("areaError", it.error.toString())
                }
            })
        }
    }

    var isNotMatch = ArrayList<String>()
    var isNotMatchMost = ArrayList<String>()
    @SuppressLint("WrongConstant")
    private fun showItemDialog(suborder: List<EcovveAllHabits.Data>, position: Int) {
        val factory = LayoutInflater.from(context!!)
        val addToCartDialogView = factory.inflate(R.layout.dailog_add_to_cart, null)
        val addToCartDialog = android.app.AlertDialog.Builder(context!!).create()
        addToCartDialog.setView(addToCartDialogView)
        addToCartDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addToCartDialog.show()
        addToCartDialog.findViewById<RecyclerView>(R.id.extraList).layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        if (adapter != null)
            addToCartDialog.findViewById<RecyclerView>(R.id.extraList).adapter = adapter
        addToCartDialog.findViewById<TextView>(R.id.txtItemPrice).text = "   " + suborder.get(position).price + "       "
        addToCartDialog.findViewById<TextView>(R.id.txtItemName).setText("" + suborder.get(position).name)
        minusQuantity = addToCartDialog.findViewById(R.id.banMinusQuantity)
        plusQuantity = addToCartDialog.findViewById(R.id.banPlusQuantity)
        txtTotal = addToCartDialog.findViewById(R.id.txtTotal)
        txtTotal!!.text = "" + suborder.get(position).price

        if (suborder.get(position).image.size != 0)
            if (!suborder.get(position).image.get(0).toString().contains("http"))
                Glide.with(context!!).load(URLs.IMAGES_URL + suborder.get(position).image.get(0)).into(addToCartDialog.findViewById(R.id.imageView3))
            else
                Glide.with(context!!).load(suborder.get(position).image.get(0)).into(addToCartDialog.findViewById(R.id.imageView3))

        units = addToCartDialog.findViewById(R.id.units)

        plusQuantity?.setOnClickListener {
            addToCartplus(
                    txtTotal!!,
                    units!!,
                    suborder.get(position).price!!
            );
        }

        minusQuantity?.setOnClickListener {
            removeFromCart(
                    txtTotal!!,
                    units!!,
                    suborder.get(position).price!!
            );
        }

        isNotMatch = ArrayList<String>()
        addToCartDialog.findViewById<AppCompatButton>(R.id.btnAddToCart).onClick {
            isNotMatch = ArrayList()
            extraModel.forEachIndexed { index, extraModel ->
                if (extraModel.is_required == 1) {
                    var isMatch = 0
                    extraModel.choices!!.forEach { onbjext ->
                        extraList.forEach { s2 ->
                            if (s2 == "" + onbjext!!.id) {
                                ++isMatch
                            }
                        }
                    }
                    if (isMatch == 0)
                        isNotMatch.add("1")
                }
            }
            if (isNotMatch.size != 0) {
                AppUtils.showDailog(activity!!, resources.getString(R.string.select_required_from_choices))
            } else {

                val cartItem = CartItem(
                        suborder.get(position).id!!,
                        suborder.get(position).brand.id!!,
                        "" + suborder.get(position).name,
                        "" + suborder.get(position).image.get(0).toString(),
                        suborder.get(position).price!!
                        , extraList
                )
                if (viewModel.cart.value!!.isEmpty()) {
                    var logo_ = ""
                    if (suborder.get(position).brand.logo != null) {
                        logo_ = suborder.get(position).brand.logo!!
                    }
                    viewModel.addItem(
                            suborder.get(position).brand.id!!, cartItem
                            , NumberFormat.getInstance().parse(units!!.text.toString()).toInt()
                            , logo_, suborder.get(position).brand.name!!, activity!!, extraList)
                } else {
                    var logo_ = ""
                    if (suborder.get(position).brand.logo != null) {
                        logo_ = suborder[position].brand.logo!!
                    }
                    var shhpid = 0
                    if (viewModel.cart.value!!.isNotEmpty()) {
                        viewModel.cart.value!!.forEach { item: Map.Entry<Int, ShopCart> ->
                            shhpid = item.value.items.value!!.toList().first().first.shopId
                        }
                        if (shhpid != suborder.get(position).brand.id) {
                            AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.cartnotmatch))
                        } else {
                            viewModel.addItem(
                                    suborder.get(position).brandId!!, cartItem
                                    , NumberFormat.getInstance().parse(units!!.text.toString()).toInt()
                                    , "" + logo_, suborder.get(position).brand.name!!, activity!!, extraList)
                        }
                    }
                }
                addToCartDialog.dismiss()

            }
        }

        addToCartDialog.btnClose?.onClick {
            addToCartDialog.dismiss()
        }
    }

    var Category_ID = ""
    var list_of_categories = ArrayList<String>()
    var CatID = -1
    lateinit var categories: List<EcovveAllCategoryResponse.Data>
    private fun categories(dialog: Dialog) {
        if (view != null) {
            viewModel.allCategories().observe(this, Observer {
                if (it.isSuccess) {
//                    dialog.dismiss()
                    val data = it.resource!!.data
                    if (it.resource!!.data.isNotEmpty()) {
                        categories = it.resource!!.data
                        list_of_categories.add(resources.getString(R.string.select_your_category))
//                        list_of_categories.add(resources.getString(R.string.all_cafes))
                        it.resource!!.data.forEach { item: EcovveAllCategoryResponse.Data ->
                            list_of_categories.add(item.name!!)
                        }
                        val sharedPreference: Shared = Shared(context!!)
                        sharedPreference.setList("categories", it.resource!!.data)

                        binding.category.onClick {
                            val factory = LayoutInflater.from(activity!!)
                            val addToCartDialogView = factory.inflate(R.layout.select_category_item, null)
                            val deleteDialog = android.app.AlertDialog.Builder(activity!!).create()
                            deleteDialog.setView(addToCartDialogView)
                            deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            deleteDialog.window!!.setBackgroundDrawableResource(R.drawable.tab_layout_ui);
                            deleteDialog.window!!.setGravity(Gravity.BOTTOM);
                            deleteDialog.window!!.attributes.windowAnimations = R.style.DialogAnimation2; //style id

                            deleteDialog.show()
                            var categoryData = ArrayList<EcovveAllCategoryResponse.Data>();
                            var item = EcovveAllCategoryResponse.Data(0,
                                    resources.getString(R.string.all_cafes),
                                    "", "", "", "")
//                          categoryData.add(item)

                            categoryData.addAll(data)
                            deleteDialog.findViewById<TextView>(R.id.reset).visibility = View.VISIBLE
                            deleteDialog.findViewById<TextView>(R.id.reset).onClick {
                                binding.category.text = resources.getString(R.string.select_category)
                                Category_ID = "";
                                CatID = -1
                                deleteDialog.dismiss()
                            }
                            val promoadapter = CategoriesAdapter(categoryData, context!!, object :
                                    CategoriesAdapter.ControlsListeners {
                                override fun click(id: String, name: String, position: Int) {
                                    Category_ID = "" + id
                                    deleteDialog.dismiss()
                                    binding.category.text = name
                                    CatID = position
                                }
                            })
                            val layoutmanager = GridLayoutManager(context!!, 2)
                            deleteDialog.findViewById<RecyclerView>(R.id.sub_caty).layoutManager = layoutmanager
                            deleteDialog.findViewById<RecyclerView>(R.id.sub_caty).adapter = promoadapter

                            deleteDialog.findViewById<ImageView>(R.id.cancel).setOnClickListener {
                                //delete
                                deleteDialog.dismiss()
                            }
                        }
                    }
                    getinfo(dialog)
                } else {
                    getinfo(dialog)
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addToCartplus(total: TextView, units: TextView, price: Float) {
        var doubletotal = NumberFormat.getInstance().parse(total.text.toString()).toFloat()
        var unitPrice = NumberFormat.getInstance().parse(price.toString()).toDouble()
        doubletotal = doubletotal + price
        total.text = "" + myFormatter.format(doubletotal)
        var unitsadd = Integer.valueOf(units.text.toString())
        unitsadd += 1
        units.setText("$unitsadd")
    }

    private fun removeFromCart(total: TextView, units: TextView, price: Float) {
        var unitPrice = NumberFormat.getInstance().parse(price.toString()).toFloat()
        var unitsnum = Integer.valueOf(units.text.toString())
        if (unitsnum != 0) {
            var doubletotal = NumberFormat.getInstance().parse(total.text.toString()).toDouble()
            doubletotal = doubletotal - price
            total.setText("" + myFormatter.format(doubletotal))
            unitsnum -= 1
            units.setText("$unitsnum")
        }
    }


    @SuppressLint("WrongConstant")
    private fun showDialogAddMostSold(resource: List<EcovveGetMostSold.Data?>
                                      , brand_id: Int?, postion: Int, adapter: ExtraAdapter) {
        val factory = LayoutInflater.from(context!!)
        val addToCartDialogView = factory.inflate(R.layout.dailog_add_to_cart, null)
        val addToCartDialog = android.app.AlertDialog.Builder(context!!).create()
        addToCartDialog.setView(addToCartDialogView)
        addToCartDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addToCartDialog.show()
        addToCartDialog.findViewById<RecyclerView>(R.id.extraList).layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        addToCartDialog.findViewById<RecyclerView>(R.id.extraList).adapter = adapter
        addToCartDialog.txtItemPrice.text = "   " + resource!!.get(postion)!!.price + "       "
        addToCartDialog.txtItemName.setText("" + resource!!.get(postion)!!.name)
        minusQuantity = addToCartDialog.findViewById(R.id.banMinusQuantity)
        plusQuantity = addToCartDialog.findViewById(R.id.banPlusQuantity)
        txtTotal = addToCartDialog.findViewById(R.id.txtTotal)
        txtTotal!!.text = "" + resource!!.get(postion)!!.price
        units = addToCartDialog.findViewById(R.id.units)

        plusQuantity?.setOnClickListener {
            addToCartplus(txtTotal!!, units!!, resource.get(postion)!!.price!!);
        }

        addToCartDialog.btnClose?.onClick {
            addToCartDialog.dismiss()
        }

        minusQuantity?.setOnClickListener {
            removeFromCart(txtTotal!!, units!!, resource.get(postion)!!.price!!);
        }

        isNotMatchMost = ArrayList()
        addToCartDialog.findViewById<AppCompatButton>(R.id.btnAddToCart).onClick {
            isNotMatchMost = ArrayList()
            extraModelMost.forEachIndexed { index, extraModel ->
                if (extraModel.is_required == 1) {
                    var isMatch = 0
                    extraModel.choices!!.forEach { onbjext ->
                        extraListMost.forEach { s2 ->
                            if (s2 == "" + onbjext!!.id) {
                                ++isMatch
                            }
                        }
                    }
                    if (isMatch == 0)
                        isNotMatchMost.add("1")
                }
            }
            if (isNotMatchMost.size != 0) {
                AppUtils.showDailog(activity!!, resources.getString(R.string.select_required_from_choices))
            } else {
                var cartItem = CartItem(
                        resource.get(postion)!!.id!!,
                        brand_id!!,
                        resource.get(postion)!!.name!!,
                        resource!![postion]!!.image!![0]!!,
                        resource.get(postion)!!.price!!, extraList)

//                            viewModel.addItem(resource.get(postion).brandId,cartItem!!
//                                ,NumberFormat.getInstance().parse(units!!.text.toString()).toInt())

                if (viewModel.cart.value!!.isEmpty()) {
                    viewModel.addItem(
                            brand_id, cartItem
                            , NumberFormat.getInstance().parse(units!!.text.toString()).toInt()
                            , "", ""
                            , activity!!, extraList)
                } else {
                    var shhpid = 0
                    if (viewModel.cart.value!!.isNotEmpty()) {
                        viewModel.cart.value!!.forEach { item: Map.Entry<Int, ShopCart> ->
                            shhpid = item.value.id
                        }
                        if (shhpid != resource!!.get(postion)!!.brand_id!!) {
                            AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.cartnotmatch))

                        } else viewModel.addItem(
                                brand_id, cartItem
                                , NumberFormat.getInstance().parse(units!!.text.toString()).toInt()
                                , "", "", activity!!, extraList)
                    }
                }
                addToCartDialog.dismiss()
            }
        }
    }

}

