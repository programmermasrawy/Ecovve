package com.q8intouch.ecovve.ui.cart.add_address


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveOutletArea
import kotlinx.android.synthetic.main.area_row.view.*
import org.jetbrains.anko.onClick
import java.util.ArrayList

class DelAreaAdapter (val items: ArrayList<EcovveOutletArea.Data.DeliveryArea>, val lang: String, val context: Context, val itemClickListener: ControlsListeners) : RecyclerView.Adapter<ViewHolderAre>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAre {
        return ViewHolderAre(LayoutInflater.from(context).inflate(R.layout.area_row, parent, false))
    }

    interface ControlsListeners {
        fun click(postion: String, name: String, catID: Int)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolderAre, position: Int) {
        if (lang.equals("ar"))
            holder.area_data.text = items!![position]!!.nameAr
        else
            holder.area_data.text = items!![position]!!.nameEn
        var data = items[position]


        var temp = false

        holder.layout.onClick {
            if (lang == "ar")
                itemClickListener.click("" + data.id!!, data!!.nameAr!!, 0)
            else
                itemClickListener.click("" + data.id!!, data!!.nameEn!!, 0)

        }

    }
}
    class ViewHolderAre(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val layout = view.layout
        val area_data = view.area_data
        val city_data = view.city_data
    }