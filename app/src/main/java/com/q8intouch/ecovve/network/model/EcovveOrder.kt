package com.q8intouch.ecovve.network.model

import com.q8intouch.ecovve.data.model.CartItem

data class PreviousOrder(var id: Int,
               var name: String,
               var logo: String,
               var item: CartItem
)