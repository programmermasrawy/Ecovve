package com.q8intouch.ecovve.ui.offers


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveOfferItems
import com.q8intouch.ecovve.util.URLs
import kotlinx.android.synthetic.main.row_offer_item.view.*
import java.text.DecimalFormat

class OfferItemsAdapter (
        val items: List<EcovveOfferItems.Data.Item>,
        val context: Context,
        val data: EcovveOfferItems.Data,
        val controlsListeners: ControlsListeners
    ) : RecyclerView.Adapter<ViewHolderdetails>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderdetails {
        return ViewHolderdetails(LayoutInflater.from(context).inflate(R.layout.row_offer_item, parent, false))
    }
    override fun getItemCount(): Int {
        return items.size
    }
    interface ControlsListeners {
        fun click(postion: Int)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolderdetails, position: Int) {
        holder.txtItemName.text = items[position].name
        holder.txtItemDescription.text = items[position].description
        holder.txtShopOpenStatus.text = ""+items[position].price
        holder.txtNumOfReviews.text = ""+ items[position].viewsNumber!!
        holder.layout.setOnClickListener {
            controlsListeners.click(position)
        }
        if (items[position].brand!!.commissionOrderPercentage!!.isNotEmpty()) {
            if (items[position].brand!!.commissionOrderPercentage!!.contains("%"))
                holder.txtOfferStatus.text = items[position].brand!!.commissionOrderPercentage!!
            else
                holder.txtOfferStatus.text = items[position].brand!!.commissionOrderPercentage!! + "%"
        }
        holder.txtNumOfReviews.text = "("+items[position].reviewsCount +")"
        val myFormatter = DecimalFormat("############")
        holder.rating.progress = Integer.parseInt("" + myFormatter.format(items[position].reviewsRating!!))
        if(items[position].image.isNotEmpty())
        if (! items[position].image[0].isEmpty())
            if (!items[position].image[0].toString().contains("http"))
                Glide.with(context).load(URLs.IMAGES_URL + items[position].image[0]).into(holder.imgItemLogo)
            else
                Glide.with(context).load(items[position].image[0]).into(holder.imgItemLogo)

    }
}

class ViewHolderdetails (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.materialCardView3
    val txtOfferStatus = view.txtOfferStatus
    val txtItemName = view.txtItemName
    val txtItemDescription = view.txtItemDescription
    val txtShopOpenStatus = view.txtShopOpenStatus
    val txtNumOfReviews = view.txtNumOfReviews
    val imgItemLogo = view.imgItemLogo
    val rating = view.rating


}