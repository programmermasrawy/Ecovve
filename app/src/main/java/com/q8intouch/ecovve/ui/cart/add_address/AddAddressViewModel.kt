package com.q8intouch.ecovve.ui.cart.add_address

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.OrderRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.apis.OrdersAPI
import com.q8intouch.ecovve.network.model.EcovveUser
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class AddAddressViewModel @Inject constructor(private val orderRepo: OrderRepo
                                             , private val ordersAPI: OrdersAPI
                                            ,private val userDataRepo: UserDataRepo
) :  ViewModel() {

    fun fetchUserData(id : String): LiveData<Resource<EcovveUser>> {
        return userDataRepo.userInfo(id).map {
            it
        }
    }
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
         lng: String
    ) = orderRepo.addAddress(
        name = name,
        phone = phone,
        area = area,
        block = block,
        parcel = parcel,
        building = building,
        floor = floor,
        additional = additional,
        street = street,
        lat = lat,
        lng = lng
    ).map { it }

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
//        payment_method :String
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
}
