package com.q8intouch.ecovve.ui.program4

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.malinskiy.superrecyclerview.OnMoreListener

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.databinding.FragmentBaseBinding
import com.q8intouch.ecovve.databinding.FragmentNavInfoBinding
import com.q8intouch.ecovve.ui.cart.order_type.AdressAdapter
import com.q8intouch.ecovve.util.LoadingDialog
import kotlinx.android.synthetic.main.fragment_nav_info.*

class navInfo : BaseFragment<Program4UViewModel,FragmentNavInfoBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_nav_info
    }
    lateinit var  dialog : Dialog
    var page = 0
    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = LoadingDialog.showDialog(context!!)
        page = 0
        LoadFaq()
        faqList.setupMoreListener(OnMoreListener { numberOfItems, numberBeforeMore, currentItemPos ->
            LoadFaq()
        }, 4)
    }

    @SuppressLint("WrongConstant")
    private fun LoadFaq(){
        viewModel.allFaqs(++page).observe(this, Observer {
            dialog.dismiss()
            if (it.isSuccess){
                val faqsAdapter = FaqsAdapter(it.resource!!.data!!, context!!, object :
                        FaqsAdapter.ControlsListeners {
                    override fun click(postion: CartItem, shpId: Int) {

                    }
                })
                faqList.setLayoutManager(LinearLayoutManager(context!!,LinearLayoutManager.VERTICAL,false))
                faqList.adapter = faqsAdapter

            }
            else {

            }
        })
    }


}
