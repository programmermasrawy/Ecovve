package com.q8intouch.ecovve.util.onesignal;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.q8intouch.ecovve.ui.MainActivity;

import org.json.JSONObject;

public class OneSignalNotification {

    private Context mAppContext;
    private Context context;

    public OneSignalNotification(Context context) {
        this.context = context;
    }

    public class ExtraLifeNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
        @Override
        public void notificationReceived(OSNotification notification) {
//            JSONObject data = notification.payload.additionalData;
//            String customKey,id;
//
//            if (data != null) {
//                customKey = data.optString("ad_type", null);
//                if (customKey != null)
//                {
//                    if (customKey.equals("1")){
//                        id = data.optString("ad_id", null);
//                        Intent intent = new Intent(context, MainActivity.class);
//                        intent.putExtra("notification",ad);
//                        intent.putExtra("id_notify",id);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                    }
//                    else if (customKey.equals("2"))
//                    {
//                        id = data.optString("auction_id", null);
//                        Intent intent = new Intent(context, MainActivity.class);
//                        intent.putExtra("notification",auction);
//                        intent.putExtra("id_notify",id);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                    }
//                    else if (customKey.equals("4"))
//                    {
//                        id = data.optString("auction_id", null);
//                        Intent intent = new Intent(context, MainActivity.class);
//                        intent.putExtra("notification",bids);
//                        intent.putExtra("id_notify",id);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                    }
//                    else {
//                        id = data.optString("offer_id", null);
//                        Intent intent = new Intent(context, MainActivity.class);
//                        intent.putExtra("notification",offer);
//                        intent.putExtra("id_notify",id);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//
//                    }
//                }
//                else {
//                    Intent intent = new Intent(context, SplashActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                }
//            }
//            else {
//                Intent intent = new Intent(context, SplashActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//            }

        }
    }


    public class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {

        Context context;
        public ExampleNotificationOpenedHandler(Context context) {
            this.context = context;
        }

        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
//            OSNotificationAction.ActionType actionType = result.action.type;
//            JSONObject data = result.notification.payload.additionalData;
//            String customKey,id;

//            if (data != null) {
//                customKey = data.optString("ad_type", null);
//                if (customKey != null)
//                {
//                    if (customKey.equals("1")){
//                        id = data.optString("ad_id", null);
                        Intent intent = new Intent(context, MainActivity.class);
//                        intent.putExtra("IDs",id);
                        context.startActivity(intent);
                    }
//                    else if (customKey.equals("2"))
//                    {
//                        id = data.optString("auction_id", null);
//                        Intent intent = new Intent(context, AuctionDetailsActivity.class);
//                        intent.putExtra("ID",id);
//                        context.startActivity(intent);
//                    }
//                    else if (customKey.equals("4"))
//                    {
//                        id = data.optString("auction_id", null);
//                        Intent intent = new Intent(context, ActivityBids.class);
//                        intent.putExtra("ID",id);
//                        context.startActivity(intent);
//                    }
//                    else {
//                        id = data.optString("offer_id", null);
//                        Intent intent = new Intent(context, OfferDetailActivity.class);
//                        intent.putExtra("ID",id);
//                        context.startActivity(intent);
//
//                    }
//                }
//                else {
//                    Intent intent = new Intent(context, SplashActivity.class);
//                    context.startActivity(intent);
//                }
//            }
//            else {
//                Intent intent = new Intent(context, SplashActivity.class);
//                context.startActivity(intent);
//            }

    }
}
