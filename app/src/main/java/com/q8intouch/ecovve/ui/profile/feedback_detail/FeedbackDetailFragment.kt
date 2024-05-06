package com.q8intouch.ecovve.ui.profile.feedback_detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.databinding.FragmentFeedbackDetailBinding
import com.q8intouch.ecovve.network.model.EcovveUser
import kotlinx.android.synthetic.main.fragment_feedback_detail.*

class FeedbackDetailFragment : Fragment() {

    companion object {
        fun newInstance() = FeedbackDetailFragment()
    }

    private lateinit var viewModel: FeedbackDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val feedbackBinding = DataBindingUtil.inflate<FragmentFeedbackDetailBinding>(inflater,R.layout.fragment_feedback_detail,container,false)
        return feedbackBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeedbackDetailViewModel::class.java)
        val data = arguments!!.getParcelable("data") as EcovveUser.Data.Reviews.ReviewITem
        if (data!=null){
            txtShopName.text = data.title
            txtDesc.text = data.body
            txtShopOpenStatus.text = data.star
            txtShopOpenStatus.text = data.star
        }
    }

}
