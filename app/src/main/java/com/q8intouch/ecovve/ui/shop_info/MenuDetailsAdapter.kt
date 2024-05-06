package com.q8intouch.ecovve.ui.shop_info
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.MenuResponse
import com.q8intouch.ecovve.util.URLs
import kotlinx.android.synthetic.main.row_menu_item.view.*
import java.text.DecimalFormat

class MenuDetailsAdapter (
    val items: List<MenuResponse.Data.Items.DataItems>, val context: Context,
    val controlsListeners: ControlsListeners,
    val menu: MenuResponse.Data
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_menu_item, parent, false))
    }
    override fun getItemCount(): Int {
        return items.size
    }

    interface ControlsListeners {
        fun click(postion: Int)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtItemName.text = items.get(position).name
        holder.txtItemDescription.setText(items[position].description)
        holder.txtShopOpenStatus.text = ""+items[position].price + context.resources.getString(R.string.kd)
        holder.txtNumOfReviews.text = ""+items.get(position).reviewsCount
        if (items[position].reviewsRating != null)
        holder.txtNumOfReviews.text = "("+ items[position]!!.reviewsCount +")"

        val myFormatter = DecimalFormat("############")
        holder.rating.progress = Integer.parseInt("" + myFormatter.format(items[position]!!.reviewsRating!!))
        holder.layout.setOnClickListener {
            controlsListeners.click(position)
        }

        if (items[position].image.isNotEmpty()) {
            Log.e("marry",items[position].image.toString())
            if (!items[position].image.toString().contains("http"))
                Glide.with(context).load(URLs.IMAGES_URL + items[position].image[0].toString()).into(holder.imgItemLogo)
            else
                Glide.with(context).load(items[position].image[0].toString()).into(holder.imgItemLogo)
        }

//        if (items[position].image.isNotEmpty())
//            if (!items[position].image.toString().contains("http"))
//                Glide.with(context).load(URLs.IMAGES_URL + items[position].image).into( holder.imgItemLogo)
//            else
//                Glide.with(context).load(items[position].image).into( holder.imgItemLogo)

    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.btnAddToCart
    val txtItemName = view.txtItemName
    val txtItemDescription = view.txtItemDescription
    val txtShopOpenStatus = view.txtShopOpenStatus
    val txtNumOfReviews = view.txtNumOfReviews
    val rating = view.rating
    val imgItemLogo = view.imgItemLogo

}