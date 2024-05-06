package com.q8intouch.ecovve.ui.shop_info


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveCafeInfo
import kotlinx.android.synthetic.main.row_shop_info_review.view.*

class ReviewAdapter (
        val items: List<EcovveCafeInfo.Data.Review?>?, val context: Context,
        val controlsListeners: ControlsListeners) : RecyclerView.Adapter<ViewHolderq>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderq {
        return ViewHolderq(LayoutInflater.from(context).inflate(R.layout.row_shop_info_review, parent, false))
    }
    override fun getItemCount(): Int {
        return items!!.size
    }
    interface ControlsListeners {
        fun click(postion: Int)
    }
    override fun onBindViewHolder(holder: ViewHolderq, position: Int) {
        holder.txtReviewUserName.setText(items!!.get(position)!!.title)
        holder.txtReviewDate.setText(items.get(position)!!.updated_at)
        holder.tatReviewComment.setText(items.get(position)!!.body)
        holder.layout.setOnClickListener {
            controlsListeners.click(position)
        }

    }
}

class ViewHolderq (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val txtReviewDate = view.txtReviewDate
    val txtReviewUserName = view.txtReviewUserName
    val tatReviewComment = view.tatReviewComment
    val layout = view.layout

}