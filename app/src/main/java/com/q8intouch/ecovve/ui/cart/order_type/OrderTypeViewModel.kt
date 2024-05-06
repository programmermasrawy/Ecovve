package com.q8intouch.ecovve.ui.cart.order_type

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.data.repo.OrderRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.apis.OrdersAPI
import com.q8intouch.ecovve.network.model.EcovveDelete
import com.q8intouch.ecovve.network.model.EcovveOrderStore
import com.q8intouch.ecovve.network.model.EcovveUser
import com.q8intouch.ecovve.network.model.cart.CartPost
import com.q8intouch.ecovve.util.extension.map
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import javax.inject.Inject


class OrderTypeViewModel @Inject constructor(private val cartRepo: CartRepo
                                             ,private val userDataRepo: UserDataRepo
                                             ,private val orderDataRepo: UserDataRepo
                                             ,private val orderRepo: OrderRepo
                                             ,private val ordersAPI: OrdersAPI
) :  ViewModel() {
    fun fetchUserData(): LiveData<EcovveUser?> {
        return userDataRepo.getUserInfo().map {
            it
        }
    }

    fun getOouletArea(orderId: String) = orderRepo.
            getOouletArea(orderId).map { it }


    fun deleteAddress(id:String): LiveData<Resource<EcovveDelete>> {
        return  userDataRepo.deleteAddress(id).map {
            it
        }
    }
    val cart = cartRepo.cart

    fun SendCart(cartRow: CartPost) = orderRepo.sendCart(cartRow).map{
        it
    }

//    fun checkout(
//        status: String,
//        delivery: String,
//        notes: String,
//        user_id: String,
//        cart_id: String,
//        address: String,
//        free_credit: String,
//        coupon_code: String,
//        gift_card_id: String,
//        payment_method: String
//    ) = orderRepo.checkout(
//        status = status,
//        delivery = delivery,
//        notes = notes,
//        user_id = user_id,
//        cart_id = cart_id,
//        address = address,
//        free_credit = free_credit,
//        coupon_code = coupon_code,
//        gift_card_id = gift_card_id,
//        payment_method = payment_method
//    ).map { it }


    fun sendUser(
        status: String,
        delivery: String,
        notes: String,
        user_id: String,
        cart_id: String,
        address: String,
        free_credit: String,
        payment_method: String,
        delivery_type : String,
        context: Context,
        findNavController: NavController
    ) :String {
        var cartRowresult : String = ""
        var call = ordersAPI.checkout( status = status,
            delivery = delivery,
            notes = notes,
            user_id = user_id,
            cart_id = cart_id,
            address = address,
            free_credit = free_credit,
            payment_method = payment_method,
            delivery_type = delivery_type);
        call.enqueue(object : Callback<EcovveOrderStore> {
            override fun onFailure(call: Call<EcovveOrderStore>, t: Throwable) {

            }

            override fun onResponse(call: Call<EcovveOrderStore>, response: Response<EcovveOrderStore>) {

//                var html = response.body()!!.string()
//                var bundle = androidx.core.os.bundleOf("amount" to "" + html)
//                findNavController.navigate(R.id.PaymentFragment,bundle)
//
//                cartRowresult = html
            }
        })
        return cartRowresult
    }

}
