package com.q8intouch.ecovve.notification


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveNotification
import kotlinx.android.synthetic.main.row_notification.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotificationsAdapter (
        val items: List<EcovveNotification.Data?>?, val context: Context,
        val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(com.q8intouch.ecovve.R.layout.row_notification, parent, false))
    } interface ControlsListeners {
        fun click(postion: Int, layout: MaterialCardView)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtShopName.text = items!!.get(position)!!.name
        holder.txtShopDescription.text = items.get(position)!!.content


        val sdf = SimpleDateFormat("yyyy-M-d HH:mm:ss")
        val currentDateandTime = sdf.format(Date())
        val time = caluculateRemainingTime(items.get(position)!!.createdAt, currentDateandTime)
        if (time.isNotEmpty()){
            if (time[0] >0){
                holder.txtShopOpenStatus.text = ""+ time.get(0).toString() + context.getString(R.string.day)
            }
            else
                if (time[1] >0){
                    holder.txtShopOpenStatus.text = ""+ time.get(1).toString() + context.getString(R.string.hour)
                }
            else
                    if (time[2] >0){
                        holder.txtShopOpenStatus.text = ""+ time.get(2).toString() + context.getString(R.string.mint)
                    }
            else
                        if (time[3] >0){
                            holder.txtShopOpenStatus.text = ""+ time.get(3).toString() + context.getString(R.string.second)
                        }
        }

        holder.layout.setOnClickListener {
            itemClickListener.click(position,holder.layout)
        }
    }
}


        @SuppressLint("SimpleDateFormat")
        private fun caluculateRemainingTime(dateStart: String?, dateStop: String?): ArrayList<Int> {
    if (dateStart != null && dateStop != null) {
        //HH converts hour in 24 hours format (0-23), day calculation
        val format = SimpleDateFormat("yyyy-M-d HH:mm:ss")

        var d1: Date? = null
        var d2: Date? = null
        var diffSeconds: Long = 0
        var diffMinutes: Long = 0
        var diffHours: Long = 0
        var diffDays: Long = 0
        try {
            d1 = format.parse(dateStart)
            d2 = format.parse(dateStop)

            //in milliseconds
            val diff = d2!!.getTime() - d1!!.getTime()

            diffSeconds = diff / 1000 % 60

            diffMinutes = diff / (60 * 1000) % 60
            diffHours = diff / (60 * 60 * 1000) % 24
            diffDays = diff / (24 * 60 * 60 * 1000)
            diffDays = diffDays + 1

        } catch (e: ParseException) {
            e.printStackTrace()
        }

        var datmajj = ArrayList<Int>()
        datmajj.add(diffDays.toInt())
        datmajj.add(diffHours.toInt())
        datmajj.add(diffMinutes.toInt())
        datmajj.add(diffSeconds.toInt())
        return datmajj
    } else {
        var datmajj = ArrayList<Int>()
        return datmajj
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
    val txtShopName = view.txtShopName
    val txtShopDescription = view.txtShopDescription
    val txtShopOpenStatus = view.txtShopOpenStatus
    val imgShopLogo = view.imgShopLogo
}