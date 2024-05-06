package com.q8intouch.ecovve.ui.social


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveUser
import kotlinx.android.synthetic.main.row_chatbot_address.view.*


class AdressAdapterBot (
        val items: List<EcovveUser.Data.Addresse>, val context: Context,
        val controlsListeners: ControlsListeners
) : RecyclerView.Adapter<ViewHolderAddress>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAddress {
        return ViewHolderAddress(
            LayoutInflater.from(context).inflate(
                R.layout.row_chatbot_address,
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return   items.size
    }

    var lastCheckedRadioGroup: RadioGroup? = null
    interface ControlsListeners {
        fun click(postion: Int)
        fun remove(postion: Int)
    }
    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolderAddress, position: Int) {
//        holder.rbAddress.setText(items.get(position).name)
        val rb = RadioButton(context)
        rb.setText(items.get(position).name)
        holder.rbAddress.addView(rb)
        holder.rbAddress.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(radioGroup: RadioGroup?, p1: Int) {
                controlsListeners.click(position)

                if (lastCheckedRadioGroup != null
                    && lastCheckedRadioGroup!!.getCheckedRadioButtonId() !== radioGroup!!.checkedRadioButtonId
                    && lastCheckedRadioGroup!!.getCheckedRadioButtonId() !== -1
                ) {
                    lastCheckedRadioGroup!!.clearCheck()
                }
                lastCheckedRadioGroup = radioGroup
            }

        })
        holder.txtAddress.setText(items.get(position).street)
        holder.btnDelete.setOnClickListener {
            controlsListeners.remove(position)
        }
        holder.rbAddress.setOnClickListener {
            controlsListeners.click(position)
            holder.rbAddress.check(position)
        }
    }
}

class ViewHolderAddress (view: View) : RecyclerView.ViewHolder(view) {
    val rbAddress = view.rbAddress
    val txtAddress = view.txtAddress
    val btnDelete = view.btnDelete

}