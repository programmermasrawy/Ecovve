package com.q8intouch.ecovve.ui.cart.payment_page

import android.content.res.Configuration
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.R.id.myweb
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentPaymentMethodsManageBinding
import com.q8intouch.ecovve.databinding.PaymentFragmentBinding
import kotlinx.android.synthetic.main.payment_fragment.*
import android.webkit.WebView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.URLs
import java.util.*


class PaymentFragment : BaseFragment<PaymentViewModel,PaymentFragmentBinding>() {
    override fun getLayoutRes(): Int {
return R.layout.payment_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            setUpLang()
            val wb = arguments!!.getString("amount")!!
        myweb.settings.javaScriptEnabled = true
        myweb.webViewClient = object :  WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url) // load the url




                if (a == 1) {
//                    view.findAllAsync("NOT CAPTURED")
//
//                    view.setFindListener { activeMatchOrdinal, numberOfMatches, isDoneCounting ->
//                        val uri = Uri.parse(url)
//                        val protocol = uri.getScheme()
//                        val server = uri.getAuthority()
//                        val path = uri.getPath()
//                        val args = uri.getQueryParameterNames()
//                        val pid = uri.getQueryParameter("paymentid")
//                        val result = uri.getQueryParameter("result")
//                        val tranid = uri.getQueryParameter("tranid")
//                        val postdate = uri.getQueryParameter("postdate")
//                        val ref = uri.getQueryParameter("ref")
//                        val trackid = uri.getQueryParameter("trackid")
//
                        //                        Toast.makeText(Knet.this, ""+limit, Toast.LENGTH_SHORT).show();
//                    if (key === 0) {
//                        if (numberOfMatches > 0) {
//                            deleteCartItems(
//                                getString(R.string.wrong_credit),
//                                pid,
//                                result,
//                                postdate,
//                                tranid,
//                                ref,
//                                trackid
//                            )
//                        }
//                        if (numberOfMatches == 0) {
//                            deleteCartItems(
//                                getString(R.string.successful_credit) + amount + getString(R.string.kd_price),
//                                pid,
//                                result,
//                                postdate,
//                                tranid,
//                                ref,
//                                trackid
//                            )
//                        }
//                        key++
//                    }
                    }
//                } else {
//                    a++
//                }
                return true
            }

        }
            myweb.loadUrl(wb);
//        myweb.loadData(wb,"text/html", null)
    }
    var a = 0
    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url) // load the url

            if(url.contains("NOT CAPTURED")){
                val uri = Uri.parse(url)
                val protocol = uri.getScheme()
                val server = uri.getAuthority()
                val path = uri.getPath()
                val args = uri.getQueryParameterNames()
                val pid = uri.getQueryParameter("paymentid")
                val result = uri.getQueryParameter("result")
                val tranid = uri.getQueryParameter("tranid")
                val postdate = uri.getQueryParameter("postdate")
                val ref = uri.getQueryParameter("ref")
                val trackid = uri.getQueryParameter("trackid")
                Snackbar.make(view!!,resources.getString(R.string.error),Snackbar.LENGTH_LONG).show()
                findNavController().popBackStack()
//                findNavController().navigate(R.id.action_PaymentFragment_to_orderCompleteFragment, bundleOf("id" to trackid))
            }
            else if (url.contains("=CAPTURED")) {
                val uri = Uri.parse(url)
                val protocol = uri.getScheme()
                val server = uri.getAuthority()
                val path = uri.getPath()
                val args = uri.getQueryParameterNames()
                val pid = uri.getQueryParameter("paymentid")
                val result = uri.getQueryParameter("result")
                val tranid = uri.getQueryParameter("tranid")
                val postdate = uri.getQueryParameter("postdate")
                val ref = uri.getQueryParameter("ref")
                val trackid = uri.getQueryParameter("trackid")
                findNavController().navigate(R.id.action_PaymentFragment_to_orderCompleteFragment, bundleOf("id" to trackid))

            }
//            if (a == 1) {
//                view.findAllAsync("NOT CAPTURED")
//
//                view.setFindListener { activeMatchOrdinal, numberOfMatches, isDoneCounting ->
//                    val uri = Uri.parse(url)
//                    val protocol = uri.getScheme()
//                    val server = uri.getAuthority()
//                    val path = uri.getPath()
//                    val args = uri.getQueryParameterNames()
//                    val pid = uri.getQueryParameter("paymentid")
//                    val result = uri.getQueryParameter("result")
//                    val tranid = uri.getQueryParameter("tranid")
//                    val postdate = uri.getQueryParameter("postdate")
//                    val ref = uri.getQueryParameter("ref")
//                    val trackid = uri.getQueryParameter("trackid")
//                    //                        Toast.makeText(Knet.this, ""+limit, Toast.LENGTH_SHORT).show();
////                    if (key === 0) {
////                        if (numberOfMatches > 0) {
////                            deleteCartItems(
////                                getString(R.string.wrong_credit),
////                                pid,
////                                result,
////                                postdate,
////                                tranid,
////                                ref,
////                                trackid
////                            )
////                        }
////                        if (numberOfMatches == 0) {
////                            deleteCartItems(
////                                getString(R.string.successful_credit) + amount + getString(R.string.kd_price),
////                                pid,
////                                result,
////                                postdate,
////                                tranid,
////                                ref,
////                                trackid
////                            )
////                        }
////                        key++
////                    }
//                }
//            } else {
//                a++
//            }
            return true
        }

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
