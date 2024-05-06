package com.q8intouch.ecovve.ui.cart


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.gift_card_item.view.*
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveUser
import org.jetbrains.anko.textColor
import java.util.*


class UserCardsAdapter (val items: List<EcovveUser.Data.ReceivedGiftCards.DataRecieved>, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHold>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return ViewHold(LayoutInflater.from(context).inflate(com.q8intouch.ecovve.R.layout.gift_card_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }
    interface ControlsListeners {
        fun click(postion: Int, s: String)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.txtShopName.text = items[position].name
        items[position].id
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
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
//        if (position%1 == 0){
//
//        }
        holder.layout.setOnClickListener {
            itemClickListener.click(position, "add")
        }
        holder.checkbox.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                holder.layout.setCardBackgroundColor(context.resources.getColorStateList(R.color.card_shadow_1))
                itemClickListener.click(position,"add")
            }
            else{
                holder.layout.setCardBackgroundColor(context.resources.getColorStateList(R.color.card_background))
                itemClickListener.click(position,"remove")
            }
        }
    }
}

class ViewHold (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val txtShopName = view.tv
    val imageView = view.imageView
    val layout = view.layout
    val checkbox = view.checkbox
}