package com.q8intouch.ecovve.ui.program4


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.data.model.ShopCart
import com.q8intouch.ecovve.network.model.EcovveFaqAll
import com.q8intouch.ecovve.ui.order.order_details.ItemsAdapter
import kotlinx.android.synthetic.main.faqs_ui.view.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textColor

class FaqsAdapter (
    val items: List<EcovveFaqAll.Data?>?, val context: Context,
    val controlsListeners: ControlsListeners) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.faqs_ui, parent, false))
    }
    override fun getItemCount(): Int {
        return   items!!.size
    }
    interface ControlsListeners {
        fun click(postion: CartItem, shpId: Int)
    }
    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtFaq.text = items!![position]!!.question
        holder.answer.text = items[position]!!.answer
        var state = false

        holder.txtFaq.onClick {
            if (state){
                holder.layout.visibility = View.GONE
                holder.txtFaq.textColor = context.resources.getColor(R.color.textHalfDarkGray)
                holder.txtFaq.setCompoundDrawablesWithIntrinsicBounds(null,null,context.getDrawable(R.drawable.ic_arrow_down),null)
                state = false
            }
            else {
                holder.layout.visibility = View.VISIBLE
                holder.txtFaq.textColor = context.resources.getColor(R.color.colorPrimary)
                holder.txtFaq.setCompoundDrawablesWithIntrinsicBounds(null,null,context.getDrawable(R.drawable.arrow_up),null)
                state = true
            }
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val txtFaq = view.txtFaq
    val answer = view.answer
    val layout = view.layout
}