package com.q8intouch.ecovve.util

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.model.ShopCart
import com.q8intouch.ecovve.ui.cart.ShopCartItemViewModel
import com.q8intouch.ecovve.ui.cart.ShopCartViewModel
import com.q8intouch.ecovve.util.extension.getParentActivity
import eu.davidea.flexibleadapter.FlexibleAdapter

fun <T> mutableDataBinder(view: View, data: LiveData<T>?, observer: Observer<T>) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && data != null) {
        data.observe(parentActivity, observer)
    }
}

@BindingAdapter("imageUrl")
fun setMutableImageUrl(view: ImageView, imageUrl: LiveData<String>?) {
    mutableDataBinder(view, imageUrl, Observer { url ->
       Glide.with(view.context)
           .load(url)
           .apply(RequestOptions()
               .centerCrop()
               .fitCenter()
               .diskCacheStrategy(DiskCacheStrategy.ALL)
           )
           .into(view)
    })
}

@BindingAdapter("imageUrl")
fun setMutableImageUrl(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .apply(RequestOptions()
            .centerCrop()
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
        )
        .into(view)
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("shopsCartsAdapter")
fun setAndUpdateCartRecyclerViewAdapter(recyclerView: RecyclerView, shopsCarts : MutableLiveData<MutableMap<Int, ShopCart>>){
    mutableDataBinder(recyclerView,shopsCarts, Observer {
        val shopCartsViewModels = it.values.toList().map { shopCart -> ShopCartViewModel(shopCart) }
        recyclerView.run {
            if (adapter != null){
                (adapter!! as FlexibleAdapter<ShopCartViewModel>).updateDataSet(shopCartsViewModels)
            }else{
                layoutManager = LinearLayoutManager(recyclerView.context)
                adapter = FlexibleAdapter<ShopCartViewModel>(shopCartsViewModels)
            }
        }
    })
}
@Suppress("UNCHECKED_CAST")
@BindingAdapter("itemsAdapter")
fun setItemsRecyclerViewAdapter(recyclerView: RecyclerView,
                                items : MutableLiveData<MutableMap<CartItem,Int>>){
    mutableDataBinder(recyclerView,items, Observer {
        val shopCartsViewModels = it.keys.toList().map { cartItem -> ShopCartItemViewModel(cartItem,it[cartItem]!!) }
        recyclerView.run {
            if (adapter != null){
                (adapter!! as FlexibleAdapter<ShopCartItemViewModel>).updateDataSet(shopCartsViewModels)
            }else{
                layoutManager = LinearLayoutManager(recyclerView.context)
                adapter = FlexibleAdapter<ShopCartItemViewModel>(shopCartsViewModels)
            }
        }
    })
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("giftCardsAdapter")
fun setGiftCardsRecyclerViewAdapter(recyclerView: RecyclerView,
                                items : MutableLiveData<MutableMap<CartItem,Int>>){
    mutableDataBinder(recyclerView,items, Observer {
        val shopCartsViewModels = it.keys.toList().map { cartItem -> ShopCartItemViewModel(cartItem,it[cartItem]!!) }
        recyclerView.run {
            if (adapter != null){
                (adapter!! as FlexibleAdapter<ShopCartItemViewModel>).updateDataSet(shopCartsViewModels)
            }else{
                layoutManager = LinearLayoutManager(recyclerView.context)
                adapter = FlexibleAdapter<ShopCartItemViewModel>(shopCartsViewModels)
            }
        }
    })
}

@SuppressLint("SetTextI18n")
@BindingAdapter("quantityUnits")
fun setQuantityUnits(view: TextView, quantity: LiveData<Int>?) {
    mutableDataBinder(view, quantity, Observer { q ->
        view.text = "$q ${view.context.resources.getString(R.string.units)}"
    })
}

@SuppressLint("SetTextI18n")
@BindingAdapter("totalkd")
fun setTotalkd(view: TextView, total: LiveData<Float>?) {
    mutableDataBinder(view, total, Observer { t ->
        view.text = "$t ${view.context.resources.getString(R.string.currency_kd)}"
    })
}


@BindingAdapter("enableWhenLoading")
fun enableWhenLoading(view:View,isLoading:LiveData<Boolean>){
    mutableDataBinder(view,isLoading, Observer {
        view.isEnabled = it
    })
}
@BindingAdapter("disableWhenLoading")
fun disableWhenLoading(view:View,isLoading:LiveData<Boolean>){
    mutableDataBinder(view,isLoading, Observer {
        view.isEnabled = !it
    })
}

@BindingAdapter("visiableWhenLoading")
fun visiableWhenLoading(view:View,isLoading:LiveData<Boolean>){
    mutableDataBinder(view,isLoading, Observer {
        view.visibility = if(it) View.VISIBLE else View.GONE
    })
}
@BindingAdapter("invisiableWhenLoading")
fun invisiableWhenLoading(view:View,isLoading:LiveData<Boolean>){
    mutableDataBinder(view,isLoading, Observer {
        view.visibility = if(it) View.INVISIBLE else View.VISIBLE
    })
}
@BindingAdapter("goneWhenLoading")
fun goneWhenLoading(view:View,isLoading:LiveData<Boolean>){
    mutableDataBinder(view,isLoading, Observer {
        view.visibility = if(it) View.GONE else View.VISIBLE
    })
}