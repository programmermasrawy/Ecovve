package com.q8intouch.ecovve.ui.cart.payment_method_select


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.R
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.row_payment_method_select.view.*


class PaymentAdapter (val context: Context,
    val controlsListeners: ControlsListeners
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_payment_method_select,
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return  3
    }

    var lastCheckedRadioGroup: RadioGroup? = null
    interface ControlsListeners {
        fun click(postion: Int)

    }
    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rb = RadioButton(context)
        if (position == 0) {
            rb.setText(context.resources.getString(R.string.k_net))
            holder.imgPaymentIcon.setImageDrawable(context.getDrawable(R.drawable.logo_knet))
        }else if (position == 1) {
            rb.setText(context.resources.getString(R.string.master_card))
            holder.view.visibility = View.VISIBLE
            holder.imgPaymentIcon.setImageDrawable(context.getDrawable(R.drawable.ic_master_card))
        }
        else {
            rb.setText(context.resources.getString(R.string.cash_on_delivery))
            holder.view.visibility = View.VISIBLE
            holder.imgPaymentIcon.setImageDrawable(context.getDrawable(R.drawable.ic_cash))

        }
        holder.rbAddress.addView(rb)
        holder.rbAddress.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(radioGroup: RadioGroup?, p1: Int) {
                controlsListeners.click(position)

                if (lastCheckedRadioGroup != null
                    && lastCheckedRadioGroup!!.checkedRadioButtonId !== radioGroup!!.checkedRadioButtonId
                    && lastCheckedRadioGroup!!.checkedRadioButtonId !== -1
                ) {
                    lastCheckedRadioGroup!!.clearCheck()
                }
                lastCheckedRadioGroup = radioGroup
            }

        })
         holder.rbAddress.setOnClickListener {
            controlsListeners.click(position)
        }
        holder.materialCardView3.setOnClickListener {
            controlsListeners.click(position)
            holder.rbAddress.check(position)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rbAddress = view.rbAddress
    val imgPaymentIcon = view.imgPaymentIcon
    val materialCardView3 = view.materialCardView3
    val view = view.view

}