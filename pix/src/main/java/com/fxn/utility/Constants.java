package com.fxn.utility;

import android.net.Uri;
import android.provider.MediaStore;
/**
 * Created by akshay on 06/04/18.
 */

public class Constants {
    public static final int sScrollbarAnimDuration = 300;
    public static String[] PROJECTION = new String[]{
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.DATE_MODIFIED,
    };
    public static Uri URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    public static String ORDERBY = MediaStore.Images.Media.DATE_TAKEN + " DESC";
    public static String ROOM_ID = "room_id";
    public static String PROMPT_CHAT_ROOMS = "chat_rooms_in_app";
    public static final String SNICH_KEY = "b9fbddf4-249a-4c5d-857b-df15fbf005a7";
    public static final String SNICH_SECRET = "TbVofDBmV0K4iLV2WrYLVw==";
    public static final String SNICH_HOST_NAME = "clientapi.sinch.com";
    public static final String SNICH_USER_ID = "121673";
    public static int USER_ID = -1;

}
