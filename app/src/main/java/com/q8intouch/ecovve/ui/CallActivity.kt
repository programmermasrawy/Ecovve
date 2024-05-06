package com.q8intouch.ecovve.ui

import android.content.ComponentName
import android.content.DialogInterface
import android.content.ServiceConnection
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.ContextThemeWrapper
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil.setContentView
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.util.Constants
import com.sinch.android.rtc.PushPair
import com.sinch.android.rtc.calling.Call
import com.sinch.android.rtc.calling.CallListener
import org.jetbrains.anko.audioManager

import com.q8intouch.ecovve.util.NotificationCallVo
import com.q8intouch.ecovve.util.sinch.AudioPlayer


class CallActivity : AppCompatActivity(){
    lateinit var mAudioPlayer: AudioPlayer

    var call : Call? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.q8intouch.ecovve.R.layout.activity_call)
        if (Constants.CALL!=null) {
            mAudioPlayer =  AudioPlayer(this);
            mAudioPlayer.playRingtone();

             call = Constants.CALL
            Constants.CALL = null
            val alertDialog = AlertDialog.Builder(this).create()
            alertDialog.setTitle( resources.getString(R.string.incoming_call))

            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,  resources.getString(R.string.reject)) { dialog, which ->
                alertDialog.dismiss()
                mAudioPlayer.stopRingtone()
                call!!.hangup()
                finish()
            }

            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,  resources.getString(R.string.answer)) { dialog, which ->
                call!!.answer()
                call!!.addCallListener(SinchCallListener())
                mAudioPlayer.stopRingtone()
                Toast.makeText(applicationContext,  resources.getString(R.string.connected_call), Toast.LENGTH_SHORT).show()
            }
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    private inner class SinchCallListener : CallListener {
        override fun onCallEnded(endedCall: Call) {
            audioManager.setStreamVolume(AudioManager.USE_DEFAULT_STREAM_TYPE, AudioManager.USE_DEFAULT_STREAM_TYPE, AudioManager.FLAG_SHOW_UI)
            Toast.makeText(applicationContext, resources.getString(R.string.ended_call), Toast.LENGTH_SHORT).show()
            endedCall.hangup()
            finish()
        }

        override fun onCallEstablished(establishedCall: Call) {
            Toast.makeText(applicationContext,  resources.getString(R.string.connected_call), Toast.LENGTH_SHORT).show()
            audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, AudioManager.STREAM_VOICE_CALL, AudioManager.FLAG_SHOW_UI)

        }

        override fun onCallProgressing(progressingCall: Call) {
            mAudioPlayer.playRingtone();
        }

        override fun onShouldSendPushNotification(call: Call, pushPairs: List<PushPair>) {}
    }

}
