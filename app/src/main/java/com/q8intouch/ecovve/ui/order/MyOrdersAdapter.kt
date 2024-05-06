package com.q8intouch.ecovve.ui.order


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveUserOrders
import kotlinx.android.synthetic.main.row_order.view.*

class MyOrdersAdapter (
        val items: List<EcovveUserOrders.Data>,
        val context: Context,
        val itemClickListener: ControlsListeners
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_order, parent, false))
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    interface ControlsListeners {
        fun click(postion: Int)
        fun remove(postion: Int)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtShopName.text = items?.get(position)!!.outlet!!.name

        holder.txtOrderTotal.text = items?.get(position)!!.final_price
        holder.txtOrderNumber.text = items?.get(position)!!.track_id

        items.get(position).status_list!!.forEachIndexed { index, data ->
            when {
                index == 0 -> holder.recieveddata.text = data!!.status_name
                index == 1 -> holder.inprogressdata.text = data!!.status_name
                index == 2 -> holder.shippeddata.text = data!!.status_name
                index == 3 -> holder.dlivereddata.text = data!!.status_name
                index == 4 -> holder.dlivereddata.text = data!!.status_name
            }
        }
        if (items.get(position).status!!.equals(holder.recieveddata.text)) {
            holder.recieved.setImageDrawable(context.resources.getDrawable(R.drawable.ic_order_step_checked))
        } else if (items.get(position).status!!.equals(holder.inprogressdata.text)) {
            holder.inprogress.setImageDrawable(context.resources.getDrawable(R.drawable.ic_order_step_checked))
        } else if (items.get(position).status!!.equals(holder.shippeddata.text)) {
            holder.shipped.setImageDrawable(context.resources.getDrawable(R.drawable.ic_order_step_checked))
        } else if (items.get(position).status!!.equals(holder.dlivereddata.text)) {
            holder.dliverd.setImageDrawable(context.resources.getDrawable(R.drawable.ic_order_step_checked))
        }
        holder.btnCancelOrder.setOnClickListener {
            itemClickListener.remove(position)
        }
        holder.viewOrder.setOnClickListener {
            itemClickListener.click(position)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val txtShopName = view.txtShopName
    val txtOrderTotal = view.txtOrderTotal
    val txtOrderNumber = view.txtOrderNumber
    val btnCancelOrder = view.btnCancelOrder
    val btnTracking = view.btnTracking
    val time = view.txtDeliveryTime
    val layout = view.materialCardView2
    val recieveddata = view.recieveddata
    val inprogressdata = view.inprogressdata
    val shippeddata = view.shippeddata
    val dlivereddata = view.dlivereddata
    val recieved = view.findViewById<ImageView>(R.id.recieved)
    val inprogress = view.findViewById<ImageView>(R.id.inprogress)
    val shipped =view.findViewById<ImageView>(R.id.shipped)
    val dliverd = view.findViewById<ImageView>(R.id.dliverd)
    val viewOrder = view.viewOrder
}