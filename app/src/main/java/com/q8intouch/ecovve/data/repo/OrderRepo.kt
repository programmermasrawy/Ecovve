package com.q8intouch.ecovve.data.repo

import android.content.Context
import androidx.navigation.NavController
import com.q8intouch.ecovve.R.id.name
import com.q8intouch.ecovve.R.string.*
import com.q8intouch.ecovve.network.apis.OrdersAPI
import com.q8intouch.ecovve.network.model.EcovveOrderStorePickup
import com.q8intouch.ecovve.network.model.cart.CartPost
import com.q8intouch.ecovve.network.model.cart.CartRow
import com.q8intouch.ecovve.util.extension.map
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject


class OrderRepo @Inject constructor(private val ordersApi: OrdersAPI) {

    fun getOrdersList(userId: String) = ordersApi.getOrdersList(userId).map { it }

    fun getOrder(orderId: String) = ordersApi.getOrder(orderId).map { it }

    fun getOrderReview(orderId: String) = ordersApi.
            getOrderReview(orderId).map { it }

    fun getOouletArea(orderId: String) = ordersApi.
            getOutletArea(orderId).map { it }

    fun sendCart(cartRow: CartPost) = ordersApi.SendCart(cartRow).map {
        it
    }
    fun checkpick(
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
            area_id: String
    )  = ordersApi.checkoutPickLive(outlet_id = outlet_id,
    notes = notes,
    user_id = user_id,
    assigned_user_id = cart_id,
    time = time,
    free_credit = "on",
    payment_method = payment_method,
    delivery_type = delivery_type
            , expected_price = price,
            area_id = area_id,
    items= items).map { it }


    fun checkpickGift(
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
            gift_card_id:Map<String,String>,
            area_id: String
    )  = ordersApi.checkoutPickLiveGift(outlet_id = outlet_id,
            notes = notes,
            user_id = user_id,
            assigned_user_id = cart_id,
            time = time,
            free_credit = "on",
            payment_method = payment_method,
            delivery_type = delivery_type
            , expected_price = price,
            gift_card_id= gift_card_id,
            area_id = area_id,
            items= items).map { it }


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
    )  = ordersApi.checkoutPickLiveGiftCoupon(outlet_id = outlet_id,
            notes = notes,
            user_id = user_id,
            assigned_user_id = cart_id,
            time = time,
            free_credit = "on",
            payment_method = payment_method,
            delivery_type = delivery_type
            , expected_price = price,
            coupon_code= coupon_code,area_id = area_id,
            items= items).map { it }

    fun checkAddress(
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
            area_id: String
    )  = ordersApi.checkoutAdressLive(outlet_id = outlet_id,
            notes = notes,
            user_id = user_id,
            assigned_user_id = cart_id,
            address_id = address_id,
            free_credit = "on",
            payment_method = payment_method,
            delivery_type = delivery_type
            , expected_price = price,
            area_id = area_id,
            items= items).map { it }

   fun checkoutAdressGiftCard(
            outlet_id: String,
            notes: String,
            user_id: String,
            address_id: String,
            free_credit: String,
            payment_method: String,
            delivery_type :String,
            items : Map<String,String>,
            price : String,
            gift_card_id :Map<String,String>,
            area_id: String
    )  = ordersApi.checkoutAdressGiftCard(outlet_id = outlet_id,
            notes = notes,
            user_id = user_id,
            assigned_user_id = user_id,
            address_id = address_id,
            free_credit = "on",
            payment_method = payment_method,
            delivery_type = delivery_type
            , expected_price = price,
            area_id = area_id,
            gift_card_id =gift_card_id,
            items= items).map { it }

    fun checkoutAdressCoupon(
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
    )  = ordersApi.checkoutAdressCoupon(outlet_id = outlet_id,
            notes = notes,
            user_id = user_id,
            assigned_user_id = user_id,
            address_id = address_id,
            free_credit = "on",
            payment_method = payment_method,
            delivery_type = delivery_type
            , expected_price = price,
            gift_card_id =gift_card_id,
            area_id = area_id,
            items= items).map { it }


    fun addAddress(
        name: String,
        phone: String,
        area: String,
        block: String,
        parcel: String,
        building: String,
        floor: String,
        additional: String,
        street: String,
        lat: String,
        lng : String
    ) = ordersApi.addAddress(
        name = name,
        phone = phone,
        area = area,
        block = block,
        parcel = parcel,
        building = building,
        floor = floor,
        additional = additional,
        street = street,
        lat = 86.285 ,
        lng = 58.244
    ).map { it }


    fun checkPricePickup(
            user_id: String,
            outlet_id: String,
            delivery_type: String,
            pickup_time: String,
            free_credit: String,
            notes: String,
            assigned_user_id: String,
            payment_method: String
            ,items : Map<String,String>
            ,area_id: String
    ) = ordersApi.checkPricePickup(
            user_id = user_id,
            outlet_id = outlet_id,
            delivery_type = delivery_type,
            pickup_time = pickup_time,
            free_credit = free_credit,
            notes = notes,
            assigned_user_id = assigned_user_id,
            payment_method = payment_method,
            area_id = area_id,
            items =  items
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
    ) = ordersApi.checkPricePickupGift(
            user_id = user_id,
            outlet_id = outlet_id,
            delivery_type = delivery_type,
            pickup_time = pickup_time,
            free_credit = free_credit,
            notes = notes,
            assigned_user_id = assigned_user_id,
            payment_method = payment_method,
            area_id = area_id,
            gift_card_id = gift_card_id,
            items =  items
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
            coupon_code: String
            ,items : Map<String,String>
            ,area_id: String
    ) = ordersApi.checkPricePickupCoupon(
            user_id = user_id,
            outlet_id = outlet_id,
            delivery_type = delivery_type,
            pickup_time = pickup_time,
            free_credit = free_credit,
            notes = notes,
            assigned_user_id = assigned_user_id,
            payment_method = payment_method,
         coupon_code = coupon_code,
            items =  items,
            area_id = area_id
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
            ,items : Map<String,String>
            ,area_id: String
    ) = ordersApi.checkPricePickupAddress(
            user_id = user_id,
            outlet_id = outlet_id,
            delivery_type = delivery_type,
            address_id = address_id,
            free_credit = free_credit,
            notes = notes,
            assigned_user_id = assigned_user_id,
            payment_method = payment_method,
            area_id = area_id,
            items =  items
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
        ) = ordersApi.checkPriceAddressGift(
                user_id = user_id,
                outlet_id = outlet_id,
                delivery_type = delivery_type,
                address_id = address_id,
                free_credit = free_credit,
                notes = notes,
                assigned_user_id = assigned_user_id,
                payment_method = payment_method,
                area_id = area_id,
                gift_card_id = gift_card_id,
                items =  items
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
                ,items : Map<String,String>
                ,area_id: String
        ) = ordersApi.checkPriceAddressCoupon(
                user_id = user_id,
                outlet_id = outlet_id,
                delivery_type = delivery_type,
                address_id = address_id,
                free_credit = free_credit,
                notes = notes,
                assigned_user_id = assigned_user_id,
                payment_method = payment_method,
            coupon_code = coupon_code,
                area_id = area_id,
                items =  items
        ).map { it }
}