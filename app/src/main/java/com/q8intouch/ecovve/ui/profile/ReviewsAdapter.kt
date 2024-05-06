package com.q8intouch.ecovve.ui.profile

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveUser
import kotlinx.android.synthetic.main.row_profile_review.view.*
import org.jetbrains.anko.onClick

class ReviewsAdapter (
        val items: List<EcovveUser.Data.Reviews.ReviewITem>, val context: Context,
        val controlsListeners: ControlsListeners
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_profile_review,
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return   items.size
    }

    interface ControlsListeners {
        fun click(postion: Int)
    }
    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.layout.onClick {
            controlsListeners.click(position)
        }

        holder.txtShopName.text = items[position].title
        holder.txtDesc.text = items[position].body
        holder.txtShopDate.text = items[position].createdAt
        holder.txtShopName.text = items[position].title
        holder.txtShopOpenStatus.text = items[position].star
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val txtShopName = view.txtShopName
    val txtDesc = view.txtDesc
    val txtShopDate = view.txtShopDate
    val txtShopOpenStatus = view.txtShopOpenStatus
    val imgShopLogo = view.imgShopLogo
    val layout = view.layout

}