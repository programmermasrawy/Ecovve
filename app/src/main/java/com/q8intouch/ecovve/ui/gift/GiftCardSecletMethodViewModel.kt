package com.q8intouch.ecovve.ui.gift

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.data.repo.OrderRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.EcovveGiftCardCheckout
import com.q8intouch.ecovve.network.apis.OrdersAPI
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject

class GiftCardSecletMethodViewModel @Inject constructor(private val cartRepo: CartRepo
                                                        , private val userDataRepo: UserDataRepo
                                                        , private val orderDataRepo: UserDataRepo
                                                        , private val orderRepo: OrderRepo
                                                        , private val ordersAPI: OrdersAPI
) :  ViewModel() {

    fun giftCardCheckout(name: String,
                         description: String,
                         amount: String,
                         expire_date:String ,
                         status:String ,
                         giver_id:String ,
                         taker_id:String ,
                         notification_type:String): LiveData<Resource<EcovveGiftCardCheckout>> {
        return  userDataRepo.giftCardCheckout(
                name = name,
                description = description
                ,amount = amount
                ,expire_date = expire_date
                ,status = status,
                giver_id = giver_id,
                taker_id = taker_id,
                notification_type = notification_type
        ).map {
            it
        }
    }

    fun giftCardCheckoutByEmail(name: String,
                                description: String,
                                amount: String,
                                expire_date:String ,
                                status:String ,
                                notification_type:String
                                ,taker_email : String): LiveData<Resource<EcovveGiftCardCheckout>> {
        return  userDataRepo.giftCardCheckoutByEmail(
                name = name,
                description = description
                ,amount = amount
                ,expire_date = expire_date
                ,status = status,
                notification_type = notification_type
                ,taker_email = taker_email
        ).map {
            it
        }
    }
}
