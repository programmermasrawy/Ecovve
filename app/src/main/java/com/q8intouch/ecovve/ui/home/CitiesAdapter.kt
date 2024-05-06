package com.q8intouch.ecovve.ui.home


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveCitiesWithAreas
import kotlinx.android.synthetic.main.area_row.view.*
import org.jetbrains.anko.onClick

class CitiesAdapter (val items: List<EcovveCitiesWithAreas.Data?>?,val lang : String, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolderArea>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderArea {
        return ViewHolderArea(LayoutInflater.from(context).inflate(R.layout.area_row, parent, false))
    } interface ControlsListeners {
        fun click(postion: String,name : String,catID: Int)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolderArea, position: Int) {
        if (lang.equals("ar"))
        holder.area_data.text = items!![position]!!.name_ar
        else
        holder.area_data.text = items!![position]!!.name_en
        var data = items[position]

        val promoadapter = AreaCitiesAdapter(items!![position]!!.area!!,lang, context!!, object :
                AreaCitiesAdapter.ControlsListeners {
            override fun click(positiona: Int) {
                if (lang.equals("ar"))
                    itemClickListener.click(""+data!!.area!![positiona]!!.id,data!!.area!![positiona]!!.name_ar!!,items[positiona]!!.id!!)
                else
                    itemClickListener.click(""+data!!.area!![positiona]!!.id,data!!.area!![positiona]!!.name_en!!,items[positiona]!!.id!!)

            }
        })
        val layoutmanager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL,false)
        holder.city_data.layoutManager = layoutmanager
        holder.city_data.adapter = promoadapter
        var temp = false

        holder.layout.onClick {
            if (data!!.area!!.size==0) {
                if (lang == "ar")
                itemClickListener.click(""+data.id!!,data!!.name_ar!!,0)
                else
                itemClickListener.click(""+data.id!!,data!!.name_en!!,0)
            } else {
                if (temp) {
                    holder.city_data.visibility = View.GONE
                    holder.area_data.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.map_marker), null, context.getDrawable(R.drawable.ic_arrow_down), null)
                    temp = false
                } else {
                    holder.city_data.visibility = View.VISIBLE
                    holder.area_data.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.map_marker), null, context.getDrawable(R.drawable.arrow_up), null)

                    temp = true
                }
            }
        }
    }
}

class ViewHolderArea (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
    val area_data = view.area_data
    val city_data = view.city_data
}