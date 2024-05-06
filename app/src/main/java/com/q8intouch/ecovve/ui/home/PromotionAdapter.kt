package com.q8intouch.ecovve.ui.home


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveAllBanners
import com.q8intouch.ecovve.util.URLs
import kotlinx.android.synthetic.main.promotion_list_item.view.*

class PromotionAdapter (val items: List<EcovveAllBanners.Data?>?, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.promotion_list_item, parent, false))
    } interface ControlsListeners {
        fun click(postion: Int)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(items!![position]!!.image!!.isNotEmpty())
            if (items[position]!!.image!!.toString().contains("placeholder")){
                if (position%2 == 0){
                    holder.imageview.setImageDrawable(context.resources.getDrawable(R.drawable.featured_offer2))
                }
                else {
                    holder.imageview.setImageDrawable(context.resources.getDrawable(R.drawable.img_featured_offers))
                }
            }
        else
            if (!items[position]!!.image!!.toString().contains("http"))
        Glide.with(context).load(URLs.IMAGES_URL+ items[position]!!.image!!).into(holder.imageview)
        else {
//                Glide.with(context).load(items[position].image!!).into(holder.imageview)
            if (!items[position]!!.image!!.toString().contains("placeholder")){
                Glide.with(context).load(items[position]!!.image!!).into(holder.imageview)
            }
                else {
                if (position%2 == 0){
                    holder.imageview.setImageDrawable(context.resources.getDrawable(R.drawable.featured_offer2))
                }
                else {
                    holder.imageview.setImageDrawable(context.resources.getDrawable(R.drawable.img_featured_offers))
                }
            }
            }
        holder.layout.setOnClickListener {
            itemClickListener.click(position)
        }

    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
    val imageview = view.imageview
}