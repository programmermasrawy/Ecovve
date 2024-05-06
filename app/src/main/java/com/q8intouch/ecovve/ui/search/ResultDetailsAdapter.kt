package com.q8intouch.ecovve.ui.search


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.q8intouch.ecovve.network.model.EcovveLatLonSearch
import com.q8intouch.ecovve.util.URLs
import kotlinx.android.synthetic.main.row_cafe_info.view.*
import java.text.DecimalFormat

import java.util.*

class ResultDetailsAdapter (
        val items: List<EcovveLatLonSearch.Data.Outlets.DataOutlet?>, val context: Context,
        val controlsListeners: ControlsListeners) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(com.q8intouch.ecovve.R.layout.row_cafe_info, parent, false))
    }
    override fun getItemCount(): Int {
        return items.size
    }
    interface ControlsListeners {
        fun click(postion: Int)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtShopName.text = items.get(position)!!.name

        if(items.get(position)!!.is_open!!)
            holder.txtShopOpenStatus.text = context.resources.getString(com.q8intouch.ecovve.R.string.open)
        else {
            holder.txtShopOpenStatus.setTextColor(context.resources.getColor(com.q8intouch.ecovve.R.color.danger))
            holder.txtShopOpenStatus.text = context.resources.getString(com.q8intouch.ecovve.R.string.close)
        }
        if (!items.get(position)!!.brand.logo!!.isEmpty())
            if (!items.get(position)!!.brand.logo!!.toString().contains("http"))
                Glide.with(context).load(URLs.IMAGES_URL + items.get(position)!!.brand.logo!!).into(holder.imgShopLogo)
            else
                Glide.with(context).load(items.get(position)!!.brand.logo).into(holder.imgShopLogo)

        holder.txtShopDescription.text = items.get(position)!!.brand.description

        holder.txtPayBy.text = ""
        if (items[position]!!.brand.supportsKnet == 1){
            holder.txtPayBy.append(context.getString(com.q8intouch.ecovve.R.string.k_net))
        }

        if (items[position]!!.brand.supportsCc == 1){
            holder.txtPayBy.append(","+context.getString(com.q8intouch.ecovve.R.string.visa_card))
        }
        val myFormatter = DecimalFormat("############")
        holder.txtNumOfReviews.text = "("+ items[position]!!.reviewsCount +")"
        holder.rating.progress = Integer.parseInt("" + myFormatter.format(items[position]!!.reviewsRating!!))
        holder.txtAvgTime.text = ""+ items[position]!!.avgDeliveryTime + context.resources.getString(com.q8intouch.ecovve.R.string.mint)
        holder.txtMinOrder.text = ""+ items[position]!!.minimumCharge + context.resources.getString(com.q8intouch.ecovve.R.string.kd)
        holder.txtDelivery.text = ""+ items[position]!!.avgDeliveryFee + context.resources.getString(com.q8intouch.ecovve.R.string.kd)
        holder.layout.setOnClickListener {
            controlsListeners.click(position)
        }
    }

    /*  private fun calculateTime(startTime: String, endTime: String, txtShopOpenStatus: TextView): String {
          var state = ""
          val now = Calendar.getInstance()
          val hour = now.get(Calendar.HOUR)
          val minute = now.get(Calendar.MINUTE)

          val date1 = Calendar.getInstance()
          date1.set(Calendar.HOUR_OF_DAY, date1.time.hours)
          date1.set(Calendar.MINUTE, date1.time.minutes)
          date1.set(Calendar.SECOND, 0)

          val reference = startTime
          val parts = reference.split(":")
          val date2 = Calendar.getInstance()
          date2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]))
          date2.set(Calendar.MINUTE, Integer.parseInt(parts[1]))
          date2.set(Calendar.SECOND, 0)

          if (date1.before(date2)) {

              val end = endTime
              val partsEnd = end.split(":")
              val dateend = Calendar.getInstance()
              dateend.set(Calendar.HOUR_OF_DAY, Integer.parseInt(partsEnd[0]))
              dateend.set(Calendar.MINUTE, Integer.parseInt(partsEnd[1]))
              dateend.set(Calendar.SECOND, 0)
              state = if (dateend.before(date2)){
                  txtShopOpenStatus.setTextColor(context.resources.getColor(com.q8intouch.ecovve.R.color.danger))
                  txtShopOpenStatus.text = context.resources.getString(com.q8intouch.ecovve.R.string.close)
                  context.resources.getString(com.q8intouch.ecovve.R.string.close)

              } else {
                  txtShopOpenStatus.text = context.resources.getString(com.q8intouch.ecovve.R.string.open)
                  context.resources.getString(com.q8intouch.ecovve.R.string.open)
              }
          }
          else {
              val end = endTime
              val partsEnd = end.split(":")
              val dateend = Calendar.getInstance()
              dateend.set(Calendar.HOUR_OF_DAY, Integer.parseInt(partsEnd[0]))
              dateend.set(Calendar.MINUTE, Integer.parseInt(partsEnd[1]))
              dateend.set(Calendar.SECOND, 0)
              state = if (dateend.before(date1)){
                  txtShopOpenStatus.setTextColor(context.resources.getColor(com.q8intouch.ecovve.R.color.danger))
                  txtShopOpenStatus.text = context.resources.getString(com.q8intouch.ecovve.R.string.close)
                  context.resources.getString(com.q8intouch.ecovve.R.string.close)
              } else {
                  txtShopOpenStatus.text = context.resources.getString(com.q8intouch.ecovve.R.string.open)
                  context.resources.getString(com.q8intouch.ecovve.R.string.open)
              }
          }
          return state
      }*/

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val txtShopName = view.txtShopName
    val imgShopLogo = view.imgShopLogo
    val txtShopDescription = view.txtShopDescription
    val txtShopOpenStatus = view.txtShopOpenStatus
    val txtPayBy = view.txtPayBy
    val txtNumOfReviews = view.txtNumOfReviews
    val txtAvgTime = view.txtAvgTime
    val rating = view.rating
    val txtMinOrder = view.txtMinOrder
    val txtDelivery = view.txtDelivery
    val layout = view.skkkkk
}