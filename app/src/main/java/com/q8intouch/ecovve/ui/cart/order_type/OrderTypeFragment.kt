package com.q8intouch.ecovve.ui.cart.order_type

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentOrderTypeBinding
import kotlinx.android.synthetic.main.fragment_order_type.*
import org.jetbrains.anko.onClick
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.q8intouch.ecovve.network.model.EcovveUser
import com.q8intouch.ecovve.util.*
import com.q8intouch.ecovve.util.extension.errorResponse
import org.jetbrains.anko.onItemSelectedListener

class OrderTypeFragment : BaseFragment<OrderTypeViewModel,FragmentOrderTypeBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_order_type
    }
    var addressID = ""
    var deliver = true
    var Cid=""
    var Cname =""
    lateinit var myCalendar: Calendar
    lateinit var cal: Calendar
    companion object {
        fun newInstance() = OrderTypeFragment()
    }
    lateinit var arrayItems: List<EcovveUser.Data.Addresse>
    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLang()
        val bundle = Bundle()
        val sharedPreference: Shared = Shared(context!!)
        arrayItems = ArrayList<EcovveUser.Data.Addresse>() as List<EcovveUser.Data.Addresse>
       var arrayItems1 = ArrayList<EcovveUser.Data.Addresse>()
        var area_id = ""
        var city_name =""

        rbPickUp.setOnClickListener {
            binding.rvDeliveryAddress.visibility = View.GONE
            binding.btnAddAddress.visibility = View.INVISIBLE
            rbDelivery.isChecked = false
            deliver = false
            addressID = ""
            binding.btnSelectOrderTime.visibility = View.VISIBLE
            binding.btnSelectOrderTime2.visibility = View.VISIBLE
        }

        rbDelivery.setOnClickListener {
            binding.rvDeliveryAddress.visibility = View.VISIBLE
            binding.btnAddAddress.visibility = View.VISIBLE
            rbPickUp.isChecked = false
            if (!deliver) {
                deliver = true
                area_id = Cid
                city_name = Cname
            }
            binding.btnSelectOrderTime.visibility = View.GONE
            binding.btnSelectOrderTime2.visibility= View.GONE
        }
       cal = Calendar.getInstance()

        var list_of_dates = ArrayList<String>()
        list_of_dates.add(getString(com.q8intouch.ecovve.R.string.choose_your_time))
        list_of_dates.add(getString(com.q8intouch.ecovve.R.string.one_hour))
        list_of_dates.add(getString(com.q8intouch.ecovve.R.string.two_hour))
        list_of_dates.add(getString(com.q8intouch.ecovve.R.string.three_hours))
        list_of_dates.add(getString(com.q8intouch.ecovve.R.string.four_hours))

        var list_of_dates2 = ArrayList<String>()
        list_of_dates2.add(getString(com.q8intouch.ecovve.R.string.choose_your_outlet))
        val dialog = LoadingDialog.showDialog(context!!)
        viewModel.getOouletArea(""+viewModel.cart.value!!.toList().get(0).second.items.value!!.toList()[0].first.shopId).observe(this,androidx.lifecycle.Observer {
            dialog.dismiss()
            if (it.isSuccess){
                var temp = -1
                if(it.resource!!.data.deliveryArea.isEmpty()){
                    temp = it.resource!!.data.area.id!!
                    area_id = "" + viewModel.cart.value!!.toList().get(0).second.items.value!!.toList()[0].first.shopId
                    Cid = "" + it.resource!!.data.area.id
                      if (URLs.lang == "en") {
                          city_name= "" + it.resource!!.data.area.nameEn
                          Cname= "" + it.resource!!.data.area.nameEn
                    }else {
                          city_name= "" + it.resource!!.data.area.nameAr
                          Cname= "" + it.resource!!.data.area.nameAr
                    }
                }
                else {
                    temp = it.resource!!.data.deliveryArea[0].id!!
                    it.resource!!.data.deliveryArea.forEachIndexed { index, data ->
                        if (URLs.lang == "en")
                            list_of_dates2.add(data.nameEn!!)
                        else
                            list_of_dates2.add(data.nameAr!!)
                    }
                    area_id = "" + it.resource!!.data.deliveryArea[0].id
                    city_name = if (URLs.lang == "en")
                        ""+ it.resource!!.data.deliveryArea[0].nameEn
                    else
                        ""+ it.resource!!.data.deliveryArea[0].nameAr
                }

                val serializedObject = sharedPreference.getValueString("addresses")
                if (serializedObject != null) {
                    val gson = Gson()
                    val type = object : TypeToken<List<EcovveUser.Data.Addresse>>() {
                    }.type
                    arrayItems = gson.fromJson(serializedObject, type)
                }
                arrayItems.forEachIndexed { index, addresse ->
                    if (arrayItems[index].areaId!! == temp){
                        arrayItems1.add(addresse)
                    }
                }
                if (arrayItems1.size!=0) {
                    val promoadapter = AdressAdapter(arrayItems1, context!!, object :
                            AdressAdapter.ControlsListeners {
                        override fun click(postion: Int) {
                            addressID = "" + arrayItems1.get(postion).id
                            area_id = "" + arrayItems1[postion].areaId
                            Log.e("chi",area_id)
                        }

                        override fun remove(postion: Int) {
                            val factory = LayoutInflater.from(activity!!)
                            val addToCartDialogView = factory.inflate(R.layout.dailog_delete_item, null)
                            val deleteDialog = android.app.AlertDialog.Builder(activity!!).create()
                            deleteDialog.setView(addToCartDialogView)
                            deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            deleteDialog.show()

                            deleteDialog.findViewById<AppCompatButton>(R.id.delete).setOnClickListener {
                                //delete
                                deleteDialog.dismiss()
                            }

                            deleteDialog.findViewById<AppCompatButton>(R.id.btnClose).setOnClickListener {
                                deleteDialog.dismiss()
                            }
                        }
                    })
                    binding.rvDeliveryAddress.layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
                    binding.rvDeliveryAddress.adapter = promoadapter

                    binding.rvDeliveryAddress.addOnScrollListener(object :
                            PaginationScrollListener(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)) {
                        override fun loadMoreItems() {
                            Snackbar.make(view, "here pagination", Snackbar.LENGTH_LONG).show()
                        }

                        override fun getTotalPageCount(): Int {
                            return 300
                        }

                        override fun isLastPage(): Boolean {
                            return false
                        }

                        override fun isLoading(): Boolean {
                            return false
                        }
                    })
                }
                binding.txtMenuItem2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }

                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            if (it.resource!!.data.deliveryArea.isNotEmpty()) {
                                if (binding.txtMenuItem2.selectedItemPosition != 0)
                                    area_id = "" + it.resource!!.data.deliveryArea[binding.txtMenuItem2.selectedItemPosition - 1].id
                            } else {
                                if (binding.txtMenuItem2.selectedItemPosition != 0)
                                    area_id = "" + it.resource!!.data.area.id
                            }
                        }
                }
            }
            else Log.e("error_area",it.errorResponse().message.toString())
        })

        val aa = ArrayAdapter(context, com.q8intouch.ecovve.R.layout.simple_spinner_dropdown_item_ecovve, list_of_dates)
//            // Set layout to use when the list of choices appear
            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_delivery)
            // Set Adapter to Spinner
            binding.txtMenuItem.setAdapter(aa)

        val aa2 = ArrayAdapter(context, com.q8intouch.ecovve.R.layout.simple_spinner_dropdown_item_ecovve, list_of_dates2)
//            // Set layout to use when the list of choices appear
        aa2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_delivery)
        // Set Adapter to Spinner
        binding.txtMenuItem2.setAdapter(aa2)

        btnContinue.setOnClickListener {
            if (deliver){
            if (arrayItems1.isNotEmpty()){
                if(addressID != "") {
                    sharedPreference.save(URLs.AREA_ID, "" + area_id)
                    bundle.putString("address", addressID)
                    bundle.putString("time", gettime(binding.txtMenuItem))
                    bundle.putString("area_id", area_id)
                    Log.e("AREA_ID", area_id)
                    findNavController().navigate(com.q8intouch.ecovve.R.id.GiftOrCoupn, bundle)
                }
                else {
                    AppUtils.showDailog(activity!!,resources.getString(R.string.not_choosed_address))
                }
            }
            else {
                val factory = LayoutInflater.from(activity!!)
                val addToCartDialogView = factory.inflate(R.layout.dailog_delete_item, null)
                val deleteDialog = android.app.AlertDialog.Builder(activity!!).create()
                deleteDialog.setView(addToCartDialogView)
                deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                deleteDialog.show()
                deleteDialog.findViewById<AppCompatButton>(R.id.delete).text = resources.getString(R.string.add_new_address)
                deleteDialog.findViewById<TextView>(R.id.prompt).text = resources.getString(com.q8intouch.ecovve.R.string.no_address)
                deleteDialog.findViewById<AppCompatButton>(R.id.delete).setOnClickListener {
                    Log.e("city_id",area_id)
                    Log.e("city_id",city_name)
                    sharedPreference.save("city_id",""+area_id)
                    sharedPreference.save("city_name",""+city_name)
                    findNavController().navigate(com.q8intouch.ecovve.R.id.action_orderTypeFragment_to_addAddressFragment)
                    deleteDialog.dismiss()
                }

                deleteDialog.findViewById<AppCompatButton>(R.id.btnClose).setOnClickListener {
                    deleteDialog.dismiss()
                }
//                AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.no_address))
                  }
            }
            else {
                if (binding.txtMenuItem.selectedItemPosition!=0 &&  binding.txtMenuItem2.selectedItemPosition !=0) {
//                    area_id = "" + it.resource!!.data.deliveryArea[binding.txtMenuItem2.selectedItemPosition - 1].id
                    sharedPreference.save(URLs.AREA_ID,""+area_id)
                    Log.e("AREA_ID",area_id)
                    bundle.putString("address", addressID)
                    bundle.putString("time", gettime(binding.txtMenuItem))
                    bundle.putString("area_id", area_id)
                    findNavController().navigate(com.q8intouch.ecovve.R.id.GiftOrCoupn, bundle)
                }
                else {
                    AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.no_time))
                }
            }
        }

            btnAddAddress.onClick {
                findNavController().navigate(com.q8intouch.ecovve.R.id.action_orderTypeFragment_to_addAddressFragment)
            }
    }

    private fun gettime(time: Spinner): String {
        val sdf = SimpleDateFormat("yyyy-M-d HH:mm:ss", Locale.US)
        val currentDateandTime = sdf.format(Date())
        val date: Long
        val auctionDate: String
        if (time.selectedItemPosition != 0) {
            date = System.currentTimeMillis() + time.selectedItemPosition *  60 * 60 * 1000

            auctionDate = sdf.format(Date(date))
            return auctionDate
        } else {
            return ""
        }
    }
    private fun updateLabelend() {
//        val myFormat = "yyyy-M-d HH:mm:ss" //In which you need put here
//        val sdf = SimpleDateFormat(myFormat, Locale.US)
//
//        binding.txtMenuItem.text = sdf.format(cal.time);
    }

    var locale: Locale? = null
    private fun Language(lang: String) {
        val country = "US"
        if (lang == "en")
            locale = Locale(lang)
        else
            locale = Locale(lang)

        Locale.setDefault(locale)
        val configs = Configuration()
        configs.locale = locale
        context!!.resources.updateConfiguration(configs, context!!.resources.displayMetrics)
    }
    private fun setUpLang() {
        val sharedPreference: Shared = Shared( context!!)
        val lang =  sharedPreference.getValueString("lang")

        if (lang==null){
            Language("en")
        }
        else {
            if (lang.equals("ar")) {
                Language("ar")
                URLs.lang = "ar"
            }
            else {
                Language("en")
                URLs.lang = "en"
            }
        }
    }
}
