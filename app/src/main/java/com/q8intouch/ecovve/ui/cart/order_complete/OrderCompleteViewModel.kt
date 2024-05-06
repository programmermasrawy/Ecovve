package com.q8intouch.ecovve.ui.cart.order_complete

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel;
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.data.repo.OrderRepo
import com.q8intouch.ecovve.network.apis.OrdersAPI
import com.q8intouch.ecovve.network.model.cart.CartPost
import com.q8intouch.ecovve.network.model.cart.CartRow
import com.q8intouch.ecovve.util.extension.map
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import com.q8intouch.ecovve.network.model.cart.EcovveRowCart

class OrderCompleteViewModel  @Inject constructor(private val orderRepo: OrderRepo
, private val ordersAPI: OrdersAPI,private val cartRepo: CartRepo) :  ViewModel() {

     fun SendCart(cartRow: CartPost) = orderRepo.sendCart(cartRow).map{
        it
    }
    fun removeCart(context: AppCompatActivity){
        cartRepo.cart.value!!.toList().first().second.items.value!!.toList().forEach {
            cartRepo.removeItem(it.first,context)
        }
    }

    fun sendNetworkRequestUser(cartRow: CartPost, context : Context)  {
        var cartRowresult : EcovveRowCart? = null
        var call = ordersAPI.cart(cartRow);
        call.enqueue(object : Callback<EcovveRowCart> {
            override fun onFailure(call: Call<EcovveRowCart>?, t: Throwable?) {
                Log.v("kell2", call!!.request().tag().toString()+"mm")
                Log.v("kell2", call.request().body().toString()+"mm")
                Log.v("kell2", call.request().cacheControl().toString()+"mm")
                Log.v("kell2", call.request().cacheControl().toString()+"mm")

            }
            override fun onResponse(call: Call<EcovveRowCart>?, response: retrofit2.Response<EcovveRowCart>?) {
                Log.v("sekoo", ""+response!!.body()!!.data.id!! )
            }
        })
//        return cartRowresult!!
    }


    fun sendUser(cartRow: CartPost, context: Context)  {
        var cartRowresult : CartRow? = null
        var call = ordersAPI.login(cartRow);
        call.enqueue(object : Callback<Response<CartRow>>{
            override fun onFailure(call: Call<Response<CartRow>>, t: Throwable) {

                Log.v("kell2", call.request().body().toString()+"mm")
                Log.v("kell2", call.request().tag().toString()+"mm")
                Log.v("kell2", call.request().cacheControl().toString()+"mm")
                Log.v("kell2", call.request().cacheControl().toString()+"mm")

            }

            override fun onResponse(call: Call<Response<CartRow>>, response: Response<Response<CartRow>>) {
                Toast.makeText(context ,  "nnnnn" + call!!.toString() +
                        "",Toast.LENGTH_LONG).show()
            }

        })
    }


}
