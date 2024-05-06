package com.q8intouch.ecovve.network.apis

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.util.URLs
import retrofit2.http.*

interface ReviewAPI {

    @GET(URLs.SHOW_REVIEW)
    fun showReview(@Path("id") userId:Int): LiveData<Resource<EccoveShowReview>>

    @GET(URLs.ORDER_REVIEW)
    fun showOrderReview(@Path("id") userId:Int): LiveData<Resource<EccoveShowReview>>

    @GET(URLs.ALL_REVIEW)
    fun showAllReview(): LiveData<Resource<EcovveReviewAll>>

    @GET(URLs.DELETE_REVIEW)
    fun deleteReview(@Path("id") userId:Int): LiveData<Resource<EcovveDelete>>


    @POST(URLs.DELETE_REVIEW)
    @FormUrlEncoded
    fun addReview(@Field("title") title:String,
                  @Field("body") body:String,
                  @Field("star") star:String,
                  @Field("user_id") userId:String,
                  @Field("outlet_id") outlet_id:String
                  ): LiveData<Resource<EcovveAddReview>>

    @POST(URLs.DELETE_REVIEW)
    @FormUrlEncoded
    fun orderFeedback(@Field("seal") seal:String,
                  @Field("delivery_time") delivery_time:String,
                  @Field("quality") quality :String,
                  @Field("delivery_rating") delivery_rating :String,
                  @Field("review") review:String,
                  @Field("order_id") order_id:String,
                  @Field("item[0][item_id]") item:String,
                  @Field("item[0][quality]") qualityitem:String,
                  @Field("price_to_value") price_to_value:String
    ): LiveData<Resource<EcovveAddReview>>


}