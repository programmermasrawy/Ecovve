package com.q8intouch.ecovve.ui.home


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveAllHabits
import kotlinx.android.synthetic.main.extra_ui_choices.view.*

class ChoicesHabitsAdapter(val items: ArrayList<EcovveAllHabits.Data.Choice>, val context: Context, val itemClickListener: ControlsListeners)
    : RecyclerView.Adapter<ViewHolderCoHab>() {
    override fun onBindViewHolder(holder: ViewHolderCoHab, position: Int) {
        holder.check_extra.text = items!![position]!!.name!!
        holder.check_extra.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                itemClickListener.click(items[position]!!.id!!)
            }
            else{
                itemClickListener.remove(items[position]!!.id!!)
            }
        }
//        holder.layout.onClick {
//            itemClickListener.click(items[position]!!.id!!)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCoHab {
        return ViewHolderCoHab(LayoutInflater.from(context).inflate(R.layout.extra_ui_choices, parent, false))
    }

    interface ControlsListeners {
        fun click(position: Int)
        fun remove(position: Int)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }
}

class ViewHolderCoHab(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
    val check_extra = view.check_extra
}