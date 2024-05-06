package com.q8intouch.ecovve.ui.gift


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveAllPromotion
import com.q8intouch.ecovve.network.model.GiftCardsListResponse
import kotlinx.android.synthetic.main.gift_card_item_buy.view.*
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import org.jetbrains.anko.textColor
import java.util.*


class CardsAdapter (val items: List<GiftCardsListResponse.DataEntity>, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(com.q8intouch.ecovve.R.layout.gift_card_item_buy, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface ControlsListeners {
        fun click(postion: Int)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtShopName.text = items.get(position).name
        items[position].id
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.txtShopName.textColor = color

        if (position%2 == 0){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.lite_red))
            holder.txtShopName.textColor = context.getColor(R.color.colorPrimary)
        }
        if (position%4 == 0){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.benfsg))
            holder.txtShopName.textColor = context.getColor(R.color.blue)
        }
        if (position%3 == 0){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.orange))
            holder.txtShopName.textColor = context.getColor(R.color.colorPrimary)
        }
        else {
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.gift_box))
            holder.txtShopName.textColor = context.getColor(R.color.blue)
        }
        holder.layout.setOnClickListener {
            itemClickListener.click(position)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val txtShopName = view.tv
    val imageView = view.imageView
    val layout = view.layout
}