package com.q8intouch.ecovve.ui.social
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveCafeInfo
import com.q8intouch.ecovve.network.model.EcovveCafeInfoBrand
import com.q8intouch.ecovve.network.model.MenuResponse
import com.q8intouch.ecovve.util.URLs
import kotlinx.android.synthetic.main.row_select_item.view.*
import java.text.DecimalFormat

class itemsAdapter (
        val items: List<EcovveCafeInfoBrand.Data.Items.DataItems?>?, val context: Context,
        val controlsListeners: ControlsListeners
) : RecyclerView.Adapter<ViewHolderAS>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAS {
        return ViewHolderAS(LayoutInflater.from(context).inflate(R.layout.row_select_item, parent, false))
    }
    override fun getItemCount(): Int {
        return items!!.size
    }

    interface ControlsListeners {
        fun click(postion: Int)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolderAS, position: Int) {
        holder.txtItemName.text = items!!.get(position)!!.name
        holder.txtItemDescription.text = items[position]!!.description
        holder.txtItemPrice.text = ""+items[position]!!.price
        holder.txtNumOfReviews.text = ""+items.get(position)!!.reviewsCount
        if (items[position]!!.reviewsCount != null)
        holder.txtNumOfReviews.text = "("+ items[position]!!.reviewsCount +")"

        val myFormatter = DecimalFormat("############")
//        holder.rating.progress = Integer.parseInt("" + myFormatter.format(items[position]!!.reviewsRating!!))
        holder.layout.setOnClickListener {
            controlsListeners.click(position)
        }

        if (items[position]!!.image!!.isNotEmpty())
            if (!items[position]!!.image.toString().contains("http"))
                Glide.with(context).load(URLs.IMAGES_URL + items[position]!!.image[0]).into( holder.imgItemLogo)
            else
                Glide.with(context).load( items[position]!!.image[0]).into( holder.imgItemLogo)

    }
}

class ViewHolderAS (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.btnAddToCart
    val txtItemName = view.txtItemName
    val txtItemDescription = view.txtItemDescription
    val txtNumOfReviews = view.txtRate
    val imgItemLogo = view.imageView3
    val txtItemPrice = view.txtItemPrice

}