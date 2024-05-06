package com.q8intouch.ecovve.ui.order.order_feedback

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentFeedbackBinding

class FeedbackFragment : BaseFragment<FeedbackViewModel,FragmentFeedbackBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_feedback
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = arguments!!.getString("id")

        viewModel.showOrderReview(id).observe(this, Observer {
            if (it.isSuccess){

            }
            else {

            }
        })

    }

}
