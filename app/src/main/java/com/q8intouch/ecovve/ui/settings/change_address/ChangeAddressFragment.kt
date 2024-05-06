package com.q8intouch.ecovve.ui.settings.change_address

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentChangeEmailBinding
import com.q8intouch.ecovve.network.model.EcovveUser
import com.q8intouch.ecovve.ui.cart.order_type.AdressAdapter
import com.q8intouch.ecovve.util.Shared
import kotlinx.android.synthetic.main.change_address_fragment.*
import org.jetbrains.anko.onClick

class ChangeAddressFragment : BaseFragment<ChangeAddressViewModel,FragmentChangeEmailBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.change_address_fragment
    }
    var addressID = ""
    lateinit var arrayItems: List<EcovveUser.Data.Addresse>
    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreference: Shared = Shared(context!!)
        arrayItems = ArrayList<EcovveUser.Data.Addresse>()
        val serializedObject = sharedPreference.getValueString("addresses")
        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<EcovveUser.Data.Addresse>>() {
            }.type
            arrayItems = gson.fromJson(serializedObject, type)
        }
        if (arrayItems.size!=0){
            val promoadapter = AdressAdapter(arrayItems, context!!, object :
                AdressAdapter.ControlsListeners {
                override fun click(postion: Int) {
                    addressID = ""+ arrayItems.get(postion).id
                }
                override fun remove(postion: Int) {
                    addressID = ""+ arrayItems.get(postion).id
                }
            })
            rvDeliveryAddress.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            rvDeliveryAddress.adapter = promoadapter


            btnDone.onClick {
                findNavController().navigate(R.id.action_orderTypeFragment_to_addAddressFragment)
            }
        }

    }

}
