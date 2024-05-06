package com.q8intouch.ecovve.network.apis

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.network.model.cart.CartPost
import com.q8intouch.ecovve.network.model.cart.CartRow
import com.q8intouch.ecovve.util.URLs
import retrofit2.Call
import retrofit2.http.*
import com.q8intouch.ecovve.network.model.cart.EcovveRowCart
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.POST



interface OrdersAPI {

    @GET(URLs.ORDERS)
    fun getOrdersList(@Path("userId") userId: String): LiveData<Resource<ResponseBody>>

    @GET(URLs.ORDER)
    fun getOrder(@Path("orderId") orderId: String): LiveData<Resource<OrderResponse>>

    @GET(URLs.ORDER_REVIEW)
    fun getOrderReview(@Path("orderId") orderId: String): LiveData<Resource<EcovveOrderReviews>>

    @GET(URLs.OUTLET_AREA)
    fun getOutletArea(@Path("id") orderId: String): LiveData<Resource<EcovveOutletArea>>

    @POST(URLs.CART_STORE)
    fun SendCart(@Body cartRow: CartPost): LiveData<Resource<EcovveRowCart>>

    @POST(URLs.CART_STORE)
    fun login(
        @Body cartRow: CartPost
    ): Call<Response<CartRow>>

    @POST(URLs.CART_STORE)
    fun cart(@Body cartRow: CartPost): Call<EcovveRowCart>


    @POST(URLs.ADD_ADDRESS)
    @FormUrlEncoded
    fun addAddress(
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("area_id") area: String,
        @Field("block") block: String,
        @Field("parcel") parcel: String,
        @Field("building") building: String,
        @Field("floor") floor: String,
        @Field("additional") additional: String,
        @Field("street") street: String,
        @Field("lat") lat: Double,
        @Field("lng") lng: Double
    ): LiveData<Resource<AddAddressResponse>>


    @FormUrlEncoded
    @POST(URLs.CHECK_PRICE)
    fun checkPricePickup(
            @Field("user_id") user_id: String,
            @Field("outlet_id") outlet_id: String,
            @Field("delivery_type") delivery_type: String,
            @Field("pickup_time") pickup_time: String,
            @Field("free_credit") free_credit: String,
            @Field("notes") notes: String,
            @Field("assigned_user_id") assigned_user_id: String,
            @Field("payment_method") payment_method: String,
            @Field("area_id") area_id: String,
            @FieldMap(encoded = true)  items : Map<String,String>
    ): LiveData<Resource<EcovveCheckOrderPrice>>

    @FormUrlEncoded
    @POST(URLs.CHECK_PRICE)
    fun checkPricePickupGift(
            @Field("user_id") user_id: String,
            @Field("outlet_id") outlet_id: String,
            @Field("delivery_type") delivery_type: String,
            @Field("pickup_time") pickup_time: String,
            @Field("free_credit") free_credit: String,
            @Field("notes") notes: String,
            @Field("assigned_user_id") assigned_user_id: String,
            @Field("payment_method") payment_method: String,
            @Field("area_id") area_id: String,
            @FieldMap(encoded = true) gift_card_id: Map<String,String>,
            @FieldMap(encoded = true)  items : Map<String,String>
    ): LiveData<Resource<EcovveCheckOrderPrice>>


    @FormUrlEncoded
    @POST(URLs.CHECK_PRICE)
    fun checkPricePickupCoupon(
            @Field("user_id") user_id: String,
            @Field("outlet_id") outlet_id: String,
            @Field("delivery_type") delivery_type: String,
            @Field("pickup_time") pickup_time: String,
            @Field("free_credit") free_credit: String,
            @Field("notes") notes: String,
            @Field("assigned_user_id") assigned_user_id: String,
            @Field("payment_method") payment_method: String,
            @Field("coupon_code") coupon_code: String,
            @Field("area_id") area_id: String,
            @FieldMap(encoded = true)  items : Map<String,String>
    ): LiveData<Resource<EcovveCheckOrderPrice>>

    @FormUrlEncoded
    @POST(URLs.CHECK_PRICE)
    fun checkPricePickupAddress(
            @Field("user_id") user_id: String,
            @Field("outlet_id") outlet_id: String,
            @Field("delivery_type") delivery_type: String,
            @Field("address_id") address_id: String,
            @Field("free_credit") free_credit: String,
            @Field("notes") notes: String,
            @Field("assigned_user_id") assigned_user_id: String,
            @Field("payment_method") payment_method: String,
            @Field("area_id") area_id: String,
            @FieldMap(encoded = true)  items : Map<String,String>
    ): LiveData<Resource<EcovveCheckOrderPrice>>

    @FormUrlEncoded
    @POST(URLs.CHECK_PRICE)
    fun checkPriceAddressGift(
            @Field("user_id") user_id: String,
            @Field("outlet_id") outlet_id: String,
            @Field("delivery_type") delivery_type: String,
            @Field("address_id") address_id: String,
            @Field("free_credit") free_credit: String,
            @Field("notes") notes: String,
            @Field("assigned_user_id") assigned_user_id: String,
            @Field("payment_method") payment_method: String,
            @Field("area_id") area_id: String,
            @FieldMap(encoded = true) gift_card_id: Map<String,String>,
            @FieldMap(encoded = true)  items : Map<String,String>
    ): LiveData<Resource<EcovveCheckOrderPrice>>

    @FormUrlEncoded
    @POST(URLs.CHECK_PRICE)
    fun checkPriceAddressCoupon(
            @Field("user_id") user_id: String,
            @Field("outlet_id") outlet_id: String,
            @Field("delivery_type") delivery_type: String,
            @Field("address_id") address_id: String,
            @Field("free_credit") free_credit: String,
            @Field("notes") notes: String,
            @Field("assigned_user_id") assigned_user_id: String,
            @Field("payment_method") payment_method: String,
            @Field("coupon_code") coupon_code: String,
            @Field("area_id") area_id: String,
            @FieldMap(encoded = true)  items : Map<String,String>
    ): LiveData<Resource<EcovveCheckOrderPrice>>



    @FormUrlEncoded
    @POST(URLs.CHECKOUT)
    fun checkout(
        @Field("status") status: String,
        @Field("delivery_type") delivery: String,
        @Field("notes") notes: String,
        @Field("user_id") user_id: String,
        @Field("cart_id") cart_id: String,
        @Field("address") address: String,
        @Field("free_credit") free_credit: String,
        @Field("payment_method") payment_method: String,
        @Field("delivery_type") delivery_type: String
    ): Call<EcovveOrderStore>

    @FormUrlEncoded
    @POST(URLs.CHECKOUT)
    fun checkoutPick(
        @Field("status") status: String,
        @Field("delivery_type") delivery: String,
        @Field("notes") notes: String,
        @Field("assigned_user_id") assigned_user_id: String,
        @Field("user_id") user_id: String,
        @Field("pickuptime") time: String,
        @Field("free_credit") free_credit: String,
        @Field("payment_method") payment_method: String,
        @Field("delivery_type") delivery_type: String,
        @FieldMap  items : Map<String,String>
    ): Call<EcovveOrderStorePickup>

     @FormUrlEncoded
        @POST(URLs.CHECKOUT)
        fun checkoutPickLive(
            @Field("outlet_id") outlet_id: String,
            @Field("notes") notes: String,
            @Field("assigned_user_id") assigned_user_id: String,
            @Field("user_id") user_id: String,
            @Field("pickup_time") time: String,
            @Field("free_credit") free_credit: String,
            @Field("payment_method") payment_method: String,
            @Field("delivery_type") delivery_type: String,
            @Field("expected_price") expected_price: String,
            @Field("area_id") area_id: String,
            @FieldMap(encoded = true)  items : Map<String,String>
        ): LiveData<Resource<EcovveOrderStorePickup>>

    @FormUrlEncoded
    @POST(URLs.CHECKOUT)
    fun checkoutPickLiveGift(
            @Field("outlet_id") outlet_id: String,
            @Field("notes") notes: String,
            @Field("assigned_user_id") assigned_user_id: String,
            @Field("user_id") user_id: String,
            @Field("pickup_time") time: String,
            @Field("free_credit") free_credit: String,
            @Field("payment_method") payment_method: String,
            @Field("delivery_type") delivery_type: String,
            @Field("expected_price") expected_price: String,
            @Field("area_id") area_id: String,
            @FieldMap(encoded = true) gift_card_id: Map<String,String>,
            @FieldMap(encoded = true)  items : Map<String,String>
    ): LiveData<Resource<EcovveOrderStorePickup>>

    @FormUrlEncoded
    @POST(URLs.CHECKOUT)
    fun checkoutPickLiveGiftCoupon(
            @Field("outlet_id") outlet_id: String,
            @Field("notes") notes: String,
            @Field("assigned_user_id") assigned_user_id: String,
            @Field("user_id") user_id: String,
            @Field("pickup_time") time: String,
            @Field("free_credit") free_credit: String,
            @Field("payment_method") payment_method: String,
            @Field("delivery_type") delivery_type: String,
            @Field("expected_price") expected_price: String,
            @Field("coupon_code") coupon_code: String,
            @Field("area_id") area_id: String,
            @FieldMap(encoded = true)  items : Map<String,String>
    ): LiveData<Resource<EcovveOrderStorePickup>>

    @FormUrlEncoded
    @POST(URLs.CHECKOUT)
    fun checkoutAdressLive(
            @Field("outlet_id") outlet_id: String,
            @Field("notes") notes: String,
            @Field("assigned_user_id") assigned_user_id: String,
            @Field("user_id") user_id: String,
            @Field("address_id") address_id: String,
            @Field("free_credit") free_credit: String,
            @Field("payment_method") payment_method: String,
            @Field("delivery_type") delivery_type: String,
            @Field("expected_price") expected_price: String,
            @Field("area_id") area_id: String,
            @FieldMap(encoded = true)  items : Map<String,String>
    ): LiveData<Resource<EcovveOrderStorePickup>>


    @FormUrlEncoded
    @POST(URLs.CHECKOUT)
    fun checkoutAdressGiftCard(
            @Field("outlet_id") outlet_id: String,
            @Field("notes") notes: String,
            @Field("assigned_user_id") assigned_user_id: String,
            @Field("user_id") user_id: String,
            @Field("address_id") address_id: String,
            @Field("free_credit") free_credit: String,
            @Field("payment_method") payment_method: String,
            @Field("delivery_type") delivery_type: String,
            @Field("expected_price") expected_price: String,
            @Field("area_id") area_id: String,
            @FieldMap(encoded = true) gift_card_id :Map<String,String>,
            @FieldMap(encoded = true)  items : Map<String,String>
    ): LiveData<Resource<EcovveOrderStorePickup>>

    @FormUrlEncoded
    @POST(URLs.CHECKOUT)
    fun checkoutAdressCoupon(
            @Field("outlet_id") outlet_id: String,
            @Field("notes") notes: String,
            @Field("assigned_user_id") assigned_user_id: String,
            @Field("user_id") user_id: String,
            @Field("address_id") address_id: String,
            @Field("free_credit") free_credit: String,
            @Field("payment_method") payment_method: String,
            @Field("delivery_type") delivery_type: String,
            @Field("expected_price") expected_price: String,
            @Field("coupon_code") gift_card_id: String,
            @Field("area_id") area_id: String,
            @FieldMap(encoded = true)  items : Map<String,String>
    ): LiveData<Resource<EcovveOrderStorePickup>>


}
