package com.q8intouch.ecovve.data.repo

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.R.drawable.star
import com.q8intouch.ecovve.network.apis.ReviewAPI
import com.q8intouch.ecovve.network.apis.SearchBrandAPI
import com.q8intouch.ecovve.network.model.EcovveAddReview
import com.q8intouch.ecovve.network.model.EcovveBrandSearch
import com.q8intouch.ecovve.network.model.EcovveReviewAll
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class ReviewsRepo @Inject constructor(private val reviewAPI: ReviewAPI) {

    fun showReview(search: String): LiveData<Resource<EcovveReviewAll>> {
        return  reviewAPI.showAllReview().map {
            it
        }
    }

    fun addReview(title: String,body: String,star: String,user_id: String,outlet_id : String): LiveData<Resource<EcovveAddReview>> {
        return  reviewAPI.addReview(
            title,
            body,
            star,
            user_id,
            outlet_id
        ).map {
            it
        }
    }

    fun orderFeedback(seal: String,delivery_time: String,quality: String,delivery_rating: String,review : String
                      ,order_id : String,item : String,qualityitem : String,price_to_value : String): LiveData<Resource<EcovveAddReview>> {
        return  reviewAPI.orderFeedback(
            seal,
            delivery_time,
            quality,
            delivery_rating,
            review,
            order_id,
            item,
            qualityitem,
            price_to_value
        ).map {
            it
        }
    }
}