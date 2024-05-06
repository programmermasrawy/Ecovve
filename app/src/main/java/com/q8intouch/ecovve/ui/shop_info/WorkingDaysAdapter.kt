package com.q8intouch.ecovve.ui.shop_info


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveCafeInfo
import kotlinx.android.synthetic.main.row_shop_info_review.view.*
import kotlinx.android.synthetic.main.row_working_days.view.*

class WorkingDaysAdapter (
        val data: List<EcovveCafeInfo.Data.workingDays?>?, val context: Context,
        val controlsListeners: ControlsListeners) : RecyclerView.Adapter<ViewHold>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return ViewHold(LayoutInflater.from(context).inflate(R.layout.row_working_days, parent, false))
    }
    override fun getItemCount(): Int {
        return data!!.size
    }
    interface ControlsListeners {
        fun click(postion: Int)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (data!![position]!!.days!=null) {
            if (data!![position]!!.days!!.day_name != null)
                holder.day.text = data!![position]!!.days!!.day_name
            holder.time.text = "" + data!![position]!!.start_time + " - " + data!![position]!!.end_time
        }
        else {
            holder.day.visibility = View.GONE
            holder.time.visibility = View.GONE
        }
    }
}

class ViewHold (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val day = view.day
    val time = view.time


}