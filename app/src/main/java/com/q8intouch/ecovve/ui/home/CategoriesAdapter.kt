package com.q8intouch.ecovve.ui.home


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveAllCategoryResponse
import com.q8intouch.ecovve.network.model.EcovveAllPromotion
import com.q8intouch.ecovve.util.URLs
import kotlinx.android.synthetic.main.layout_category.view.*

class CategoriesAdapter (val items: List<EcovveAllCategoryResponse.Data>, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolderA>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderA {
        return ViewHolderA(LayoutInflater.from(context).inflate(R.layout.layout_category, parent, false))
    } interface ControlsListeners {
        fun click(postion: String,name : String,catID: Int)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolderA, position: Int) {
        holder.cat.text = items[position].name
        if(items[position].image!!.isNotEmpty())
            if (!items[position].image!!.toString().contains("http"))
        Glide.with(context).load(URLs.IMAGES_URL+ items[position].image!!).into(holder.imageview)
        else {
//                Glide.with(context).load(items[position].image!!).into(holder.imageview)
            if (items[position].image!!.toString().contains("placeholder")){
                Glide.with(context).load(items[position].image!!).into(holder.imageview)
            }
            else
                    holder.imageview.setImageDrawable(context.resources.getDrawable(R.drawable.ic_coffee_cup))

            }
        holder.layout.setOnClickListener {
            itemClickListener.click(""+items[position].id,items[position].name!!,position)
        }

    }
}

class ViewHolderA (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
    val imageview = view.image
    val cat = view.cat
}