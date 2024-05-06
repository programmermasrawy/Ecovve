package com.q8intouch.ecovve.ui.program4

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.data.model.CartItem
import com.q8intouch.ecovve.databinding.Program4UFragmentBinding
import com.q8intouch.ecovve.util.AppUtils
import com.q8intouch.ecovve.util.Constants
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.extension.fillRV
import kotlinx.android.synthetic.main.fragment_nav_info.*
import kotlinx.android.synthetic.main.program4_u_fragment.*

class Program4UFragment : BaseFragment<Program4UViewModel,Program4UFragmentBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.program4_u_fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Program4UViewModel::class.java)
        binding.userPoints.text = "" + Constants.POINT
        binding.freecredit.text =""+ Constants.User.freeCredit
        val dialog = LoadingDialog.showDialog(view!!.context)
        viewModel.getAllRewards().observe(this, Observer {
            dialog.dismiss()
            if (it.isSuccess) {
                val rewardsAdapter = RewardsAdapter(it.resource!!.data!!, context!!, object :
                        RewardsAdapter.ControlsListeners {
                    override fun click(postion: Int, rewardId: Int) {
                        viewModel.exchangeReward(""+Constants.User!!.id,""+rewardId).observe(this@Program4UFragment, Observer {
                            if (it.isSuccess){
                                Constants.POINT =""+ it.resource!!.data.points
                                binding.userPoints.text = Constants.POINT
                                binding.freecredit.text =""+ it.resource!!.data!!.freeCredit
                                AppUtils.showDailog(activity!!,
                                        resources.getString(R.string.successful_exchange))
                            }
                        })
                    }
                })
                rv4U.setLayoutManager(LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false))
                rv4U.adapter = rewardsAdapter
            }
            else {
                Log.e("mahmoudError",it.error!!.localizedMessage)
            }
        })
    }
}
