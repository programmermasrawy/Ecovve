package com.q8intouch.ecovve.ui.contact
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.q8intouch.ecovve.R
import android.content.Intent
import android.app.Activity
import androidx.lifecycle.Observer
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.ContactUsFragmentBinding
import com.q8intouch.ecovve.util.AppUtils
import com.q8intouch.ecovve.util.LoadingDialog
import kotlinx.android.synthetic.main.contact_us_fragment.*
import org.jetbrains.anko.onClick

class ContactUsFragment : BaseFragment<ContactUsViewModel, ContactUsFragmentBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.contact_us_fragment
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContactUsViewModel::class.java)


        submit.onClick {
            val dialog = LoadingDialog.showDialog(context!!)
            if (etName.text.isEmpty()) {
                etName.error = resources.getString(R.string.invaidorempty)
            } else if (etPhone.text.isEmpty()) {
                etPhone.error = resources.getString(R.string.invaidorempty)
            }
            else if (etPhone.text.length <8){
                etPhone.error = resources.getString(R.string.phonelength)
            }
            else if (etMessage.text.isEmpty()) {
                etMessage.error = resources.getString(R.string.invaidorempty)
            } else {
                viewModel.contactUs(name = etName.text.toString(),phone = etPhone.text.toString(),address = etMessage.text.toString()).observe(this, Observer {
                    dialog.dismiss()
                    if (it.isSuccess){
                        AppUtils.showDailog(activity!!, resources.getString(com.q8intouch.ecovve.R.string.message_sent))
                    }
                })
            }
        }

        facebookContact.setOnClickListener(View.OnClickListener {
            share(activity!!, resources.getString(R.string.app_name), resources.getString(R.string.info_ecovve_com), "com.facebook.katana")
        })
        twitterContact.setOnClickListener(View.OnClickListener {
            share(activity!!, resources.getString(R.string.app_name), resources.getString(R.string.info_ecovve_com), "com.twitter.android")
        })
        instgramContact.setOnClickListener(View.OnClickListener {
            share(activity!!, resources.getString(R.string.app_name), resources.getString(R.string.info_ecovve_com), "com.instagram.android")
        })
        youtubeContact.setOnClickListener(View.OnClickListener {
            share(activity!!, resources.getString(R.string.app_name), "https://www.youtube.com/watch?v=PW_88sL_2dA", "Video/*")
        })
    }

    fun share(activity: Activity, title: String, url: String, app: String) {
        val share = Intent()
        share.action = Intent.ACTION_SEND
        share.setPackage(app)
        share.type = "text/plain"
        share.putExtra(Intent.EXTRA_TEXT, "$title  $url")
        activity.startActivity(Intent.createChooser(share, ""))
    }
}