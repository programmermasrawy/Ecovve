package com.q8intouch.ecovve.ui.cart.payment_method_select

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.data.repo.OrderRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.apis.OrdersAPI
import com.q8intouch.ecovve.network.model.EcovveOrderStorePickup
import com.q8intouch.ecovve.network.model.EcovveUser
import com.q8intouch.ecovve.util.extension.map
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PaymentMethodSelectViewModel @Inject constructor(private val cartRepo: CartRepo
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



    var cart = cartRepo.cart



    fun checkPricePickup(
            user_id: String,
            outlet_id: String,
            delivery_type: String,
            pickup_time: String,
            free_credit: String,
            notes: String,
            assigned_user_id: String,
            payment_method: String
            , items: Map<String,String>,area_id :String
    ) = orderRepo.checkPricePickup(
            user_id = user_id,
            outlet_id = outlet_id,
            delivery_type = delivery_type,
            pickup_time = pickup_time,
            free_credit = free_credit,
            notes = notes,
            assigned_user_id = assigned_user_id,
            payment_method = payment_method,
            items =  items
            ,area_id = area_id
    ).map { it }

    fun checkPricePickupGift(
            user_id: String,
            outlet_id: String,
            delivery_type: String,
            pickup_time: String,
            free_credit: String,
            notes: String,
            assigned_user_id: String,
            payment_method: String,
            gift_card_id: Map<String,String>
            ,items : Map<String,String>
            ,area_id: String
    ) = orderRepo.checkPricePickupGift(
            user_id = user_id,
            outlet_id = outlet_id,
            delivery_type = delivery_type,
            pickup_time = pickup_time,
            free_credit = free_credit,
            notes = notes,
            assigned_user_id = assigned_user_id,
            payment_method = payment_method,
            gift_card_id = gift_card_id,
            items =  items
            ,area_id = area_id
    ).map { it }

    fun checkPricePickupCoupon(
            user_id: String,
            outlet_id: String,
            delivery_type: String,
            pickup_time: String,
            free_credit: String,
            notes: String,
            assigned_user_id: String,
            payment_method: String,
            gift_card_id: String
            ,items : Map<String,String>
            ,area_id: String
    ) = orderRepo.checkPricePickupCoupon(
            user_id = user_id,
            outlet_id = outlet_id,
            delivery_type = delivery_type,
            pickup_time = pickup_time,
            free_credit = free_credit,
            notes = notes,
            assigned_user_id = assigned_user_id,
            payment_method = payment_method,
            coupon_code = gift_card_id,
            items =  items
            ,area_id = area_id
    ).map { it }
   fun checkPriceAddress(
            user_id: String,
            outlet_id: String,
            delivery_type: String,
            address_id: String,
            free_credit: String,
            notes: String,
            assigned_user_id: String,
            payment_method: String
            , items: Map<String,String>
            ,area_id: String
    ) = orderRepo.checkPriceAddress(
            user_id = user_id,
            outlet_id = outlet_id,
            delivery_type = delivery_type,
         address_id = address_id,
            free_credit = free_credit,
            notes = notes,
            assigned_user_id = assigned_user_id,
            payment_method = payment_method,
            items =  items
            ,area_id = area_id
    ).map { it }

    fun checkPriceAddressGift(
            user_id: String,
            outlet_id: String,
            delivery_type: String,
            address_id: String,
            free_credit: String,
            notes: String,
            assigned_user_id: String,
            payment_method: String
            ,gift_card_id :Map<String,String>
            ,items : Map<String,String>
            ,area_id: String
    ) = orderRepo.checkPriceAddressGift(
            user_id = user_id,
            outlet_id = outlet_id,
            delivery_type = delivery_type,
            address_id = address_id,
            free_credit = free_credit,
            notes = notes,
            assigned_user_id = assigned_user_id,
            payment_method = payment_method,
            gift_card_id = gift_card_id,
            items =  items
            ,area_id = area_id
    ).map { it }
    fun checkPriceAddressCoupon(
            user_id: String,
            outlet_id: String,
            delivery_type: String,
            address_id: String,
            free_credit: String,
            notes: String,
            assigned_user_id: String,
            payment_method: String
            ,coupon_code :String
            ,items : Map<String,String>,
            area_id: String
    ) = orderRepo.checkPriceAddressCoupon(
            user_id = user_id,
            outlet_id = outlet_id,
            delivery_type = delivery_type,
            address_id = address_id,
            free_credit = free_credit,
            notes = notes,
            assigned_user_id = assigned_user_id,
            payment_method = payment_method,
            coupon_code = coupon_code,
            items =  items
            ,area_id = area_id
    ).map { it }

    fun getOouletArea(orderId: String) = orderRepo.
            getOouletArea(orderId).map { it }


    fun checkpick(
            outlet_id: String,
            notes: String,
            user_id: String,
            user: String,
            time: String,
            free_credit: String,
            payment_method: String,
            delivery_type :String,
            items : Map<String,String>,
            price : String,
            area_id: String
    )  = orderRepo.
            checkpick(outlet_id = outlet_id,
            notes = notes,
            user_id = user_id,
            cart_id =  user,
            time = time,
            free_credit = free_credit,
            payment_method = payment_method,
            delivery_type = delivery_type,
            items= items,price = price,area_id = area_id).map { it }
    fun checkpickGift(
            outlet_id: String,
            notes: String,
            user_id: String,
            user: String,
            time: String,
            free_credit: String,
            payment_method: String,
            delivery_type :String,
            items : Map<String,String>,
            price : String,
            gift_card_id: Map<String,String>,
            area_id: String
    )  = orderRepo.
            checkpickGift(outlet_id = outlet_id,
                    notes = notes,
                    user_id = user_id,
                    cart_id =  user,
                    time = time,
                    free_credit = free_credit,
                    payment_method = payment_method,
                    delivery_type = delivery_type,
                    items= items,price = price,gift_card_id = gift_card_id,area_id = area_id).map { it }

    fun checkpickGiftCoupon(
            outlet_id: String,
            notes: String,
            user_id: String,
            cart_id: String,
            time: String,
            free_credit: String,
            payment_method: String,
            delivery_type :String,
            items : Map<String,String>,
            price : String,
            coupon_code:String,
           area_id: String
    )  = orderRepo.checkpickGiftCoupon(outlet_id = outlet_id,
            notes = notes,
            user_id = user_id,
            cart_id = cart_id,
            time = time,
            free_credit = "on",
            payment_method = payment_method,
            delivery_type = delivery_type
            , price = price,
            coupon_code= coupon_code,
            items= items,area_id = area_id).map { it }

    fun checkAdress(
            outlet_id: String,
            notes: String,
            user_id: String,
            user: String,
            address_id: String,
            free_credit: String,
            payment_method: String,
            delivery_type :String,
            items : Map<String,String>,
            price : String,
            area_id: String
    )  = orderRepo.
            checkAddress(outlet_id = outlet_id,
                    notes = notes,
                    user_id = user_id,
                    cart_id =  user,
                    address_id = address_id,
                    free_credit = free_credit,
                    payment_method = payment_method,
                    delivery_type = delivery_type,
                    items= items,price = price,area_id = area_id).map { it }

    fun checkoutAddressGiftCard(
            outlet_id: String,
            notes: String,
            user_id: String,
            cart_id: String,
            address_id: String,
            free_credit: String,
            payment_method: String,
            delivery_type :String,
            items : Map<String,String>,
            price : String,
            gift_card_id :Map<String,String>,
            area_id: String
    )  = orderRepo.checkoutAdressGiftCard(outlet_id = outlet_id,
            notes = notes,
            user_id = user_id,
            address_id = address_id,
            free_credit = free_credit,
            payment_method = payment_method,
            delivery_type = delivery_type
            , price = price,
            gift_card_id =gift_card_id,
            items= items,area_id = area_id).map { it }


    fun checkoutAddressCoupon(
            outlet_id: String,
            notes: String,
            user_id: String,
            address_id: String,
            free_credit: String,
            payment_method: String,
            delivery_type :String,
            items : Map<String,String>,
            price : String,
            gift_card_id : String,
            context: Context,
            findNavController: NavController
            ,area_id: String
    )  = orderRepo.checkoutAdressCoupon(outlet_id = outlet_id,
            notes = notes,
            user_id = user_id,
            address_id = address_id,
            free_credit = free_credit,
            payment_method = payment_method,
            delivery_type = delivery_type
            , price = price,
            gift_card_id =gift_card_id,
            items= items,context = context,findNavController = findNavController
            ,area_id = area_id).map { it }

    fun sendUser2(
        status: String,
        delivery: String,
        notes: String,
        user_id: String,
        cart_id: String,
        time: String,
        free_credit: String,
        payment_method: String,
        delivery_type :String,
        items : Map<String,String>,
        context: Context,
        findNavController: NavController
    ) :String {
        var cartRowresult : String = ""
        var call = ordersAPI.checkoutPick( status = status,
            delivery = delivery,
            notes = notes,
            user_id = user_id,
            assigned_user_id = cart_id,
            time = time,
            free_credit = free_credit,
            payment_method = payment_method,
            delivery_type = delivery_type,
                    items= items)
        call.enqueue(object : Callback<EcovveOrderStorePickup> {
            override fun onFailure(call: Call<EcovveOrderStorePickup>, t: Throwable) {
                Toast.makeText(context!!,t.localizedMessage.toString(),Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<EcovveOrderStorePickup>, response: Response<EcovveOrderStorePickup>) {
                if (response.isSuccessful) {
                    var data = response.body()
                    var bundle = androidx.core.os.bundleOf("amount" to "" + data!!.data.paymentUrl)
                    findNavController.navigate(R.id.PaymentFragment,bundle)
//                    Log.e("mahmoud",response.errorBody().toString())
                    cartRowresult = data.data.paymentUrl!!
                }
                else {
                    Log.e("mahmoud",response.errorBody().toString())
                    cartRowresult = ""
                }
            }
        })
        return cartRowresult
    }

}
