package com.q8intouch.ecovve.util.sinch;


import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.q8intouch.ecovve.R;
import com.q8intouch.ecovve.ui.CallActivity;
import com.q8intouch.ecovve.ui.MainActivity;
import com.q8intouch.ecovve.util.NotificationCallVo;
import com.sinch.android.rtc.NotificationResult;
import com.sinch.android.rtc.SinchHelpers;
import com.sinch.android.rtc.calling.CallNotificationResult;

import java.util.Map;

import androidx.core.app.NotificationCompat;

public class FirebaseMsgService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (SinchHelpers.isSinchPushPayload(remoteMessage.getData())) {
            // it's Sinch message - relay it to SinchClient
            NotificationResult result = SinchHelpers.queryPushNotificationPayload(FirebaseMsgService.this, remoteMessage.getData());
            if (result.isCall() && result.isValid()) {
                CallNotificationResult callResult = result.getCallResult();
                if (!callResult.isCallCanceled()) {
                    createNotification();
                }
            }

        } else {
            // it's NOT Sinch message - process yourself
            Log.d("FCM_DEBUGGING", "message4");

        }


    }

    private void createNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        Intent i = new Intent(this, CallActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);
        mBuilder.setAutoCancel(true);
        mBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        mBuilder.setWhen(20000);
        mBuilder.setTicker("Ticker");
        mBuilder.setContentInfo("Info");
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("New notification title");
        mBuilder.setContentText("Notification text");
        mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, mBuilder.build());
    }


}
//public class FirebaseMsgService extends FirebaseMessagingService {
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//        if (SinchHelpers.isSinchPushPayload(remoteMessage.getData())) {
//            // it's Sinch message - relay it to SinchClient
//            NotificationResult result = SinchHelpers.queryPushNotificationPayload(FirebaseMsgService.this, remoteMessage.getData());
////            NotificationResult result =   EcovveApplication.Companion.getAPP().getSinchClientM()
////                    .relayRemotePushNotificationPayload(remoteMessage.getData());
//            if(result.isCall()&& result.isValid()){
//                Log.e(",amamamm","mgmgmg");
//                CallNotificationResult callResult = result.getCallResult();
//                Map<String, String> customHeaders = callResult.getHeaders();
//                Intent intent = new Intent(this, MainActivity.class);
//                NotificationCallVo callVo = new NotificationCallVo();
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//        }
//    }
//
//    public class SinchCallClientListener implements CallClientListener {
//
//        @Override
//        public void onIncomingCall(CallClient callClient, Call call) {
//
//            Intent intent = new Intent(FirebaseMsgService.this, CallActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            Constants.CALL = call;
//            FirebaseMsgService.this.startActivity(intent);
//        }
//    }
//        ///To check if the app is in foreground ///
//        public static boolean foregrounded () {
//            ActivityManager.RunningAppProcessInfo appProcessInfo =
//                    new ActivityManager.RunningAppProcessInfo();
//            ActivityManager.getMyMemoryState(appProcessInfo);
//            return (appProcessInfo.importance == IMPORTANCE_FOREGROUND
//                    || appProcessInfo.importance == IMPORTANCE_VISIBLE);
//        }
//
//}