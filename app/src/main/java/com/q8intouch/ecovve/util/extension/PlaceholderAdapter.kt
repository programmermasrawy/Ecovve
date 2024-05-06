package com.q8intouch.ecovve.util.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R

class PlaceholderAdapter constructor(@LayoutRes private val res:Int,private val rowsCount:Int,
                                     private val action:Int?) : RecyclerView.Adapter<PlaceholderAdapter.PlaceholerViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceholerViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(res,parent,false)
        if (action != null) {
            view.setOnClickListener {
                Navigation.findNavController(view).navigate(action)
            }
        }

        return PlaceholerViewHolder(view)
    }

    override fun getItemCount(): Int {
       return rowsCount
    }

    override fun onBindViewHolder(holder: PlaceholerViewHolder, position: Int) {

    }


    class PlaceholerViewHolder constructor(view:View) : RecyclerView.ViewHolder(view){

    }
}