package com.q8intouch.ecovve.ui.order.order_feedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveOrderReviews
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class FeedbackViewModel @Inject constructor(
    private val userDataRepo: UserDataRepo
) : ViewModel() {

    fun showOrderReview(id: String): LiveData<Resource<EcovveOrderReviews>>
            =  userDataRepo.showOrderReview(id).map {
        it
    }
}
