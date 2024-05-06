package com.q8intouch.ecovve.util;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.q8intouch.ecovve.R;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import androidx.core.app.ActivityCompat;

public class AppUtils {

    public static boolean isInternetAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE));
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager
                    .getActiveNetworkInfo().isConnected();
        } else return false;
    }


    public static void share(Activity activity, String title, String url, String app) {
        Intent share = new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.setPackage(app);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, "" + title + "  " + url);
//        activity.startActivity(share);
        activity.startActivity(Intent.createChooser(share, ""));
    }


    public static void showDailog(Activity activity, String data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View view2 = LayoutInflater.from(activity).inflate(R.layout.prompt_dailog, null);
        builder.setView(view2);
        final AlertDialog alertDialog = builder.show();
        TextView prompt = view2.findViewById(R.id.prompt);
        prompt.setText(data);
        Window view_base=((AlertDialog)alertDialog).getWindow();
        view_base.setWindowAnimations(R.style.DialogAnimation2);
        view_base.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        view_base.setWindowAnimations(R.style.DialogAnimation2);

        view2.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    public static String parseDateByPattern(String time, String pattern) {
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(pattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static Long parseDate(String time, String pattern) {
        String inputPattern = pattern;
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static float getFileSize(String filePath) {
        //String filepathstr=filepath.toString();
        File file = new File(filePath);
        float fileSizeInKB = file.length() / 1024;
        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        float fileSizeInMB = fileSizeInKB / 1024;
        return fileSizeInMB;
    }


    public static boolean checkIfAppIsInatalled(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void goToGooglePlay(Context context, String appPackageName) {
//        final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
        try {
            context.startActivity(
                    new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }


    public static Spanned getSpannedHtml(String htmlText) {
        Spanned text;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY);
        } else {
            text = Html.fromHtml(htmlText);
        }

        return text;
    }

//    public static String getAppId(Context context) {
//        if (context.getResources().getBoolean(R.bool.is_parent_user)) {
//            return AppConstant.UsersTypes.PARENT;
//        }
//        return AppConstant.UsersTypes.SITTER;
//    }

    public static int getIntValue(String value) {
        return Integer.valueOf(value.trim());
    }

    public static String getStringValue(Object o) {
        return String.valueOf(o);
    }

    public static Float getFloatValue(String value) {
        return Float.parseFloat(value);
    }

    public static String formatRate(float rate) {
        return String.format("%.2f", rate);
    }

    public static String getTimeZone() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
                Locale.getDefault());
        Date currentLocalTime = calendar.getTime();
        DateFormat date = new SimpleDateFormat("Z");
        return date.format(currentLocalTime);
    }

    public static void copyTextToClipboard(Context context, String label, String text) {
        ClipboardManager clipboard = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label, text);
        clipboard.setPrimaryClip(clip);
    }

//    public static void shareIntent(Context context, String subject, String text) {
//        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//
//        sharingIntent.setType("text/plain");
//        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        sharingIntent.putExtra(Intent.EXTRA_TEXT, text);
//        context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.share_via)));
//    }

    public static void printHash64(Context context) {
        // add code to print out the key hash
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:",
                        android.util.Base64.encodeToString(md.digest(), android.util.Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void setLocale(Context context) {
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    /**
     * start activity to crop image.
     *
     * @param activity       caller activity.
     * @param imageUri       image uri to show in activity to crop it.
     * @param requiredWidth  image required width.
     * @param requiredHeight image required height.
     */
//    public static void startCropImageActivity(Activity activity, Uri imageUri, int requiredWidth, int requiredHeight) {
//        CropImage.activity(imageUri)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setFixAspectRatio(true).setRequestedSize(requiredWidth, requiredHeight)
//                .start(activity);
//    }

    /**
     * encode image to base 64 string.
     *
     * @param image   bitmap image.
     * @param quality image quality to change image with this quality rate.
     * @return image after conversion in string format.
     */
//    public static String encodeBitmapToBase64(Bitmap image, int quality) {
//        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.PNG, quality, byteArrayOS);
//        return Base64.encodeBytes(byteArrayOS.toByteArray());
//    }


    /**
     * Rate CompanyApp In google play
     *
     * @param context The Activity context.
     */
    public static void rateApp(Context context) {
        try {
            Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    static void makeCall(Context context, String number) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + Uri.encode(number.trim())));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(callIntent);
    }

    static void sendSms(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + number));
        context.startActivity(intent);
    }

//    public static void changeSmsBroadcastReceiverState(Context context, boolean isEnabled) {
//
//        int flag = (isEnabled ?
//                PackageManager.COMPONENT_ENABLED_STATE_ENABLED :
//                PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
//        ComponentName component = new ComponentName(context, SmsReceiver.class);
//
//        context.getPackageManager()
//                .setComponentEnabledSetting(component, flag,
//                        PackageManager.DONT_KILL_APP);
//    }

    /**
     * Function to compare to times.
     */
    public static boolean compareTimes(String starDate, String endDate) {

        String pattern = "yyyy-MM-dd H:m a";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        try {
            //endTime = sdf.format(endTime);
            Date date1 = sdf.parse(starDate);
            starDate = sdf.format(date1);
            date1 = sdf.parse(starDate);
            Date date2 = sdf.parse(endDate);
            endDate = sdf.format(date2);
            date2 = sdf.parse(endDate);
            return date2.before(date1);
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    /**
     * Function to compare to times.
     */
    public static boolean compareStartTime(String starDate) {

        String pattern = "yyyy-MM-dd H:m a";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        try {
            Date today = Calendar.getInstance().getTime();
            String x = sdf.format(today);
            Date date1 = sdf.parse(x);
            Date date2 = sdf.parse(starDate);
            starDate = sdf.format(date2);
            date2 = sdf.parse(starDate);
            return date2.before(date1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    public static boolean isAppLanguageArabic(Context context) {
//        return PreferenceHelper.getPrefsLanguage(context).equals("ar");
//    }


    public static String getAppGooglePlayLink(Context context) {
        return "market://details?id=" + context.getPackageName();
    }

    public static String getAppLink(Context context) {
        return
                "http://play.google.com/store/apps/details?id=" + context.getPackageName();
    }

    public static boolean isAppInForeground(Context mContext) {
        String mPackageName = "";
        List<ActivityManager.RunningTaskInfo> tasks = null;
        List<ActivityManager.RunningAppProcessInfo> tasks1 = null;

        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > 20) {
            tasks1 = am.getRunningAppProcesses();
            if (!tasks1.isEmpty()) {
                mPackageName = tasks1.get(0).processName;
            }
        } else {
            tasks = am.getRunningTasks(1);
            if (!tasks.isEmpty()) {
                mPackageName = tasks.get(0).topActivity.getPackageName();
            }

        }
        if (!mPackageName.equals(mContext.getPackageName())) {
            return false;
        }

        return true;
    }


    /**
     * Function to convert english numbers to arabic
     * if mobile language arabic
     *
     * @param number Number to convert
     * @return Converted number
     */
    public static String convertNumberToEnglishDigits(String number) {
        try {
            return number.replaceAll("٠", "0").replaceAll("١", "1")
                    .replaceAll("٢", "2").replaceAll("٣", "3")
                    .replaceAll("٤", "4").replaceAll("٥", "5")
                    .replaceAll("٦", "6").replaceAll("٧", "7")
                    .replaceAll("٨", "8").replaceAll("٩", "9");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }

    public static String convertNumberToArabicDigits(String number) {
        try {
            return number.replaceAll("0", "٠").replaceAll("1", "١")
                    .replaceAll("2", "٢").replaceAll("3", "٣")
                    .replaceAll("4", "٤").replaceAll("5", "٥")
                    .replaceAll("6", "٦").replaceAll("7", "٧")
                    .replaceAll("8", "٨").replaceAll("9", "٩");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }

//    public static boolean isAppLanguageAr(Context context) {
//        return PreferenceHelper.getPrefsLanguage(context).equals("ar");
//    }

    public static boolean isValidBitmapSize(Bitmap bitmap, int bitmapValidSize) {
        int byteCount = bitmap.getByteCount();
        int bitmapMegaByteSize = (byteCount / 1024) / 1024;
        return bitmapMegaByteSize <= bitmapValidSize;
    }

    public static boolean isAtLeast24Api() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static boolean isAtLeast17Api() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }
    // DownloadImage AsyncTask

//    public static class DownloadImage {
//        private RegisterView registerView;
//
//        public DownloadImage(String imgeUrl, RegisterView registerView) {
//            new DownloadImageTask().execute(imgeUrl);
//            this.registerView = registerView;
//        }
//
//        private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//
//            @Override
//            protected Bitmap doInBackground(String... URL) {
//
//                String imageURL = URL[0];
//
//                Bitmap bitmap = null;
//                try {
//                    // Download Image from URL
//                    InputStream input = new java.net.URL(imageURL).openStream();
//                    // Decode Bitmap
//                    bitmap = BitmapFactory.decodeStream(input);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return bitmap;
//            }
//
//            @Override
//            protected void onPostExecute(Bitmap result) {
//                // Set the bitmap into ImageView
//                registerView.finishDownloadImage(result);
//            }
//        }
//
//    }


    public static String getDeviceLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static Date getDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-dd HH:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(date);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static String parseDate(String time) {
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
        String outputPattern = "MMM-dd-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static String changeDateFormat(String dateInput, String inputFormat, String outputFormat) {
        DateFormat fromFormat = new SimpleDateFormat(inputFormat);
        fromFormat.setLenient(false);
        DateFormat toFormat = new SimpleDateFormat(outputFormat);
        toFormat.setLenient(false);
        Date date = null;
        try {
            date = fromFormat.parse(dateInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toFormat.format(date);
    }

    public static String parseTime(String time) {
        String inputPattern = "HH:mm:ss";
        String outputPattern = "HH:ss a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static boolean isEventInCal(Context context, String meetingName) {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        Cursor cursor = context.getContentResolver()
                .query(Uri.parse("content://com.android.calendar/events"),
                        new String[]{CalendarContract.Events._ID,
                                CalendarContract.Events.TITLE,
                                CalendarContract.Events.EVENT_LOCATION,
                                CalendarContract.Events.DTSTART,
                                CalendarContract.Events.DTEND,},
                        CalendarContract.Events.TITLE + " = ? ",
                        new String[]{meetingName}, null);
        DatabaseUtils.dumpCursor(cursor);

        assert cursor != null;
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public static void showToastMsg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static String _s(Context context, int resMessage) {
        return context.getString(resMessage);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static Bitmap make_image_circil(Bitmap use_image) {
        Bitmap circleBitmap = Bitmap.createBitmap(use_image.getWidth(), use_image.getHeight(), Bitmap.Config.ARGB_8888);

        BitmapShader shader = new BitmapShader(use_image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);

        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(use_image.getWidth() / 2, use_image.getHeight() / 2, use_image.getWidth() / 2, paint);
        return circleBitmap;
    }

    public static void captureImageCameraOrGallery(final Context context) {

        final CharSequence[] options = {"Take photo", "Choose from library"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(
                context);

        builder.setTitle("Select");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (options[which].equals("Take photo")) {
                    try {

                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) context).startActivityForResult(takePicture, 0);//zero can be replaced with any action code


                    } catch (ActivityNotFoundException ex) {
                        String errorMessage = "Whoops - your device doesn't support capturing images!";

                    }

                } else if (options[which].equals("Choose from library")) {


                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    ((Activity) context).startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppTheme;

        dialog.show();

    }


}
