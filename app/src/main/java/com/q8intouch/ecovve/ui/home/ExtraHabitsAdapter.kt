package com.q8intouch.ecovve.ui.home


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveAllHabits
import kotlinx.android.synthetic.main.extra_ui.view.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textColor

class ExtraHabitsAdapter(val items: List<EcovveAllHabits.Data.Extra>?, val context: Context, val itemClickListener: ControlsListeners)
    : RecyclerView.Adapter<ViewHolderCotAH>() {
    override fun onBindViewHolder(holder: ViewHolderCotAH, position: Int) {

        holder.title.text = items!![position]!!.name

        itemClickListener.click(items[position]!!.id!!, holder.choices)
        if (items[position]!!.is_required ==1)
            holder.required.visibility = View.VISIBLE
        var temp = true
        holder.title.onClick {
            if (temp){
                holder.choices.visibility = View.GONE
                holder.title.textColor = context.resources.getColor(R.color.textHalfDarkGray)
                holder.title.setCompoundDrawablesWithIntrinsicBounds(null,null,context.getDrawable(R.drawable.ic_arrow_down),null)
                temp = false
            }
            else {
                holder.choices.visibility = View.VISIBLE
                holder.title.textColor = context.resources.getColor(R.color.colorPrimary)
                holder.title.setCompoundDrawablesWithIntrinsicBounds(null,null,context.getDrawable(R.drawable.arrow_up),null)
                temp = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCotAH {
        return ViewHolderCotAH(LayoutInflater.from(context).inflate(R.layout.extra_ui, parent, false))
    }

    interface ControlsListeners {
        fun click(postion: Int, choices: RecyclerView)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }
}

class ViewHolderCotAH(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val layout = view.layout
    val title = view.title
    val choices = view.choices
    val required = view.required
}