package com.q8intouch.ecovve.ui.cart.payment_page

import androidx.lifecycle.ViewModel;
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.data.repo.OrderRepo
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.apis.OrdersAPI
import javax.inject.Inject

class PaymentViewModel  @Inject constructor(private val cartRepo: CartRepo
                                            , private val userDataRepo: UserDataRepo
                                            , private val orderDataRepo: UserDataRepo
                                            , private val orderRepo: OrderRepo
                                            , private val ordersAPI: OrdersAPI
) :  ViewModel() {
}
