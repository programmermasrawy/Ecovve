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
import com.q8intouch.ecovve.network.model.EcovveAllRewards
import com.q8intouch.ecovve.network.model.EcovveFaqAll
import kotlinx.android.synthetic.main.row_4u.view.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textColor

class RewardsAdapter (
    val items: List<EcovveAllRewards.Data?>?, val context: Context,
    val controlsListeners: ControlsListeners) : RecyclerView.Adapter<ViewHolderRewards>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRewards{
        return ViewHolderRewards(LayoutInflater.from(context).inflate(R.layout.row_4u, parent, false))
    }
    override fun getItemCount(): Int {
        return   items!!.size
    }
    interface ControlsListeners {
        fun click(postion: Int, shpId: Int)
    }
    @SuppressLint("WrongConstant", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolderRewards, position: Int) {
        holder.title.text = ""+items!![position]!!.point + ""+context.resources.getString(R.string.point)
        holder.amount.text = ""+ items[position]!!.credit + context.resources.getString(R.string.kwd)

        holder.exchange.onClick {
            controlsListeners.click(position,items[position]!!.id!!)
        }

    }

}
public  class ViewHolderRewards (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the title that will add each animal to
    val title = view.title
    val amount = view.amount
    val exchange = view.exchange
}
