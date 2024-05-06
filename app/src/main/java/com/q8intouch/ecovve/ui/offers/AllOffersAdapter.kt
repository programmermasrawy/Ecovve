package com.q8intouch.ecovve.ui.offers


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveAllPromotion
import kotlinx.android.synthetic.main.row_offers.view.*

class AllOffersAdapter (val items: List<EcovveAllPromotion.Data>, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_offers, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface ControlsListeners {
        fun click(postion: Int)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtShopName.text = items.get(position).name
        if (items[position].item.isNotEmpty()) {
            if (items[position].item[0].brand.commissionOrderPercentage!!.contains("%"))
            holder.txtShopOpenStatus.text = items[position].item[0].brand.commissionOrderPercentage
            else
            holder.txtShopOpenStatus.text = items[position].item[0].brand.commissionOrderPercentage + "%"
        }
        holder.txtShopDescription.text = items.get(position).description
//        holder.txtShopOpenStatus.text = items.get(position).buyOneGetOneOffer
        holder.layout.setOnClickListener {
            itemClickListener.click(items.get(position).id!!)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val txtShopName = view.txtShopName
    val txtShopDescription = view.txtShopDescription
    val txtShopOpenStatus = view.txtShopOpenStatus
    val layout = view.layout
}