package com.q8intouch.ecovve.ui.order.order_details


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveUserOrders
import kotlinx.android.synthetic.main.row_details_order.view.*

class OrdersAdapter (
        val items: List<EcovveUserOrders.Data>?, val context: Context,
        val itemClickListener: ControlsListeners) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_details_order, parent, false))
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    interface ControlsListeners {
        fun click(postion: Int)
        fun remove(postion: Int)
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtShopName.setText(items!!.get(position)!!.outlet!!.name)

//        val adapter = ItemsAdapter(items!!, context!!,object :
//            ItemsAdapter.ControlsListeners {
//            override fun remove(postion: Int) {
//
//            }
//
//            override fun click(postion: Int) {
//
//            }
//        })
//        holder.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
//        holder.recyclerView.adapter = adapter
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val recyclerView = view.recyclerView
        val txtShopName = view.txtShopName

    }
}