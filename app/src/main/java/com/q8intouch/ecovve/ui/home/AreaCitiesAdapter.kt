package com.q8intouch.ecovve.ui.home


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveCitiesWithAreas
import kotlinx.android.synthetic.main.text_city.view.*
import org.jetbrains.anko.onClick

class AreaCitiesAdapter (val items: List<EcovveCitiesWithAreas.Data.Area?>?,val lang : String, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolderCot>() {
    override fun onBindViewHolder(holder: ViewHolderCot, position: Int) {
        holder.layout.onClick {
            itemClickListener.click(position)
        }
        if (lang.equals("ar"))
            holder.city.text = items!![position]!!.name_ar
        else
            holder.city.text = items!![position]!!.name_en

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCot {
        return ViewHolderCot(LayoutInflater.from(context).inflate(R.layout.text_city, parent, false))
    } interface ControlsListeners {
        fun click(postion: Int)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }


}

 class ViewHolderCot (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
    val city = view.city

}