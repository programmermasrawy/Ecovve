package com.q8intouch.ecovve.ui.cart.order_complete


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.github.leonardoxh.livedatacalladapter.Resource

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentOrderCompleteBinding
import com.q8intouch.ecovve.network.model.cart.CartPost
import com.q8intouch.ecovve.network.model.cart.CartRow
import kotlinx.android.synthetic.main.fragment_order_complete.*
import org.jetbrains.anko.onClick
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.maps.errors.ApiError
import com.q8intouch.ecovve.util.URLs
import com.q8intouch.ecovve.util.extension.errorResponse
import io.reactivex.disposables.Disposable
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.HttpException
import retrofit2.adapter.rxjava2.Result.response
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit


class OrderCompleteFragment : BaseFragment<OrderCompleteViewModel,FragmentOrderCompleteBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_order_complete
    }

    var disposable: Disposable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.removeCart((activity as AppCompatActivity?)!!)

        var callbac = object : OnBackPressedCallback {
            override fun handleOnBackPressed(): Boolean {
                // Handle the back button event
                findNavController().popBackStack()
                findNavController().popBackStack()
                findNavController().popBackStack()
                return true
            }
        }
        getActivity()!!.addOnBackPressedCallback(getViewLifecycleOwner(), callbac);

        val id = arguments!!.getString("id")
        txtOrderNumber.text = id

        btnViewOrders.onClick {
            findNavController().navigate(R.id.myOrdersFragment)
        }
    }
}