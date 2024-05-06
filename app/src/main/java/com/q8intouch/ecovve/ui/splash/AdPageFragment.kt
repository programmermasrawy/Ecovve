package com.q8intouch.ecovve.ui.splash


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.q8intouch.ecovve.R
import android.os.CountDownTimer
import android.util.Log
import android.widget.ProgressBar
import androidx.databinding.adapters.SeekBarBindingAdapter.setProgress
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.q8intouch.ecovve.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_ad_page.*


class AdPageFragment : BaseFragment<SplashViewModel,com.q8intouch.ecovve.databinding.FragmentAdPageBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_ad_page
    }

    var mProgressBar: ProgressBar?=null
    var mCountDownTimer: CountDownTimer?=null
    var i = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mProgressBar = view.findViewById(R.id.progressbar) as ProgressBar
        mProgressBar?.progress = i

        viewModel.randomAD().observe(this, Observer {
            if(it.isSuccess){
                mCountDownTimer = object : CountDownTimer(5000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        Log.v("Log_tag", "Tick of Progress$i$millisUntilFinished")
                        i++
                        mProgressBar?.progress = i * 100 / (5000 / 1000)

                    }
                    override fun onFinish() {
                        //Do what you want
                        i++
                        mProgressBar?.progress = 100
                        findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
                    }
                }
                mCountDownTimer?.start()

            }
            else {
                findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
            }
        })
      }
}
