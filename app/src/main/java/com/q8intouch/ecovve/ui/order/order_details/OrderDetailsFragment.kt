package com.q8intouch.ecovve.ui.order.order_details

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.media.AudioManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.network.model.EcovveUserOrders
import com.q8intouch.ecovve.ui.Tracking.TrackingOrderActivity
import com.q8intouch.ecovve.util.sinch.AudioPlayer
import com.sinch.android.rtc.PushPair
import com.sinch.android.rtc.Sinch
import com.sinch.android.rtc.calling.Call
import com.sinch.android.rtc.calling.CallClient
import com.sinch.android.rtc.calling.CallClientListener
import com.sinch.android.rtc.calling.CallListener
import kotlinx.android.synthetic.main.fragment_order_details.*

class OrderDetailsFragment : Fragment() {
    var call: Call? = null
    lateinit var mAudioPlayer: AudioPlayer
    lateinit var alertDialog: AlertDialog
    var data: EcovveUserOrders.Data? = null

    companion object {
        fun newInstance() = OrderDetailsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_details, container, false)
    }

    @SuppressLint("WrongConstant", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = arguments!!.getParcelable<EcovveUserOrders.Data>("amount")

        if (data != null) {
            sinchImplementation()
            data!!.status_list!!.forEachIndexed { index, data ->
                when {
                    index == 0 -> recieveddata.text = data!!.status_name
                    index == 1 -> inprogressdata.text = data!!.status_name
                    index == 2 -> shippeddata.text = data!!.status_name
                    index == 3 -> dlivereddata.text = data!!.status_name
                    index == 4 -> dlivereddata.text = data!!.status_name
                }
            }

            if (data!!.status!! == recieveddata.text) {
                recieved.setImageDrawable(context!!.resources.getDrawable(R.drawable.ic_order_step_checked))
            } else if (data!!.status!! == inprogressdata.text) {
                inprogress.setImageDrawable(context!!.resources.getDrawable(R.drawable.ic_order_step_checked))
            } else if (data!!.status!!.equals(shippeddata.text)) {
                shipped.setImageDrawable(context!!.resources.getDrawable(R.drawable.ic_order_step_checked))
            } else if (data!!.status!!.equals(dlivereddata.text)) {
                dliverd.setImageDrawable(context!!.resources.getDrawable(R.drawable.ic_order_step_checked))
            }
            txtOrderNumber.text = data!!.track_id
            txtOrderTime.text = data!!.created_at
            txtDeliveryTime.text = "" + data!!.track_id!!
            txtAddressName.text = "" + data!!.track_id
            txtAddress.text = data!!.outlet!!.name

            if (data!!.items != null && data!!.items.size != 0) {
                val adapter = ItemsAdapter(data!!.items, context!!, object :
                        ItemsAdapter.ControlsListeners {
                    override fun remove(postion: Int) {

                    }

                    override fun click(postion: Int) {

                    }
                })
                recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = adapter
            }
            btnTrackOrder.setOnClickListener {
                val intent = Intent(this@OrderDetailsFragment.context, TrackingOrderActivity::class.java)
                intent.putExtra("track_id", data!!.track_id)
                startActivity(intent)
            }
        }
    }

    private fun sinchImplementation() {
        val android_id = Settings.Secure.getString(activity!!.contentResolver,
                Settings.Secure.ANDROID_ID)
        Log.e("android_id", android_id)
        var sinchClient = Sinch.getSinchClientBuilder().context(context!!)
                .applicationKey(com.q8intouch.ecovve.util.Constants.SNICH_KEY)
                .applicationSecret(com.q8intouch.ecovve.util.Constants.SNICH_SECRET)
                .environmentHost(com.q8intouch.ecovve.util.Constants.SNICH_HOST_NAME)
                .userId(android_id)
                .build()
        sinchClient.setSupportCalling(true)
        sinchClient.setSupportManagedPush(true)
        sinchClient.setSupportPushNotifications(true)
        sinchClient.setSupportActiveConnectionInBackground(true)
        sinchClient.startListeningOnActiveConnection()
        sinchClient.start()
        sinchClient.callClient.addCallClientListener(SinchCallClientListener())
        mAudioPlayer = AudioPlayer(activity!!);
        materialButton.setOnClickListener {
            //            var bundle = bundleOf("id" to data!!.id!!)
//            Navigation.createNavigateOnClickListener(R.id.action_orderDetailsFragment_to_feedbackFragment, bundle)
            if (call == null) {
                // setting up custom headers
                var headers = HashMap<String, String>();
                headers.put("The first value is ", "@123");
                headers.put("Custom value ", "two!");

                call = sinchClient.getCallClient().callUser(data!!.delivery_man.mobileId!!,headers)//3118eb39cd813d34
                call!!.addCallListener(SinchCallListener());

                alertDialog = AlertDialog.Builder(activity!!).create()
                alertDialog.setTitle(data!!.delivery_man.name!!)
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, resources.getString(R.string.cancel_call)) { dialog, which ->
                    alertDialog.dismiss()
                    mAudioPlayer.stopRingtone()
                    call!!.hangup()
                }

                alertDialog.setCancelable(false)
                alertDialog.show()
            } else {
                call!!.hangup();
            }
        }
    }

    private inner class SinchCallListener : CallListener {
        override fun onCallEnded(endedCall: Call) {
            call = endedCall
            Toast.makeText(context!!, resources.getString(R.string.ended_call), Toast.LENGTH_SHORT).show()
            activity!!.volumeControlStream = AudioManager.USE_DEFAULT_STREAM_TYPE
            alertDialog.dismiss()
            mAudioPlayer.stopProgressTone()
            call!!.hangup()
            call = null;
        }

        override fun onCallEstablished(establishedCall: Call) {
            mAudioPlayer.stopProgressTone()
            Toast.makeText(context!!, resources.getString(R.string.connected_call), Toast.LENGTH_SHORT).show()
            activity!!.volumeControlStream = AudioManager.STREAM_VOICE_CALL
        }

        override fun onCallProgressing(progressingCall: Call) {
            mAudioPlayer.playProgressTone();
        }

        override fun onShouldSendPushNotification(call: Call, pushPairs: List<PushPair>) {

        }
    }

    private inner class SinchCallClientListener : CallClientListener {
        override fun onIncomingCall(callClient: CallClient, incomingCall: Call) {
            call = incomingCall
            Toast.makeText(context!!, resources.getString(R.string.incoming_call), Toast.LENGTH_SHORT).show()
            call!!.answer()
            call!!.addCallListener(SinchCallListener())
        }
    }

}