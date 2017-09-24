package comchatory.rainvic.ccityhkchatadmin.util;

import android.content.Context;
import android.content.SharedPreferences;


import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Rainvic on 30/8/2016.
 */
public class PreferenceUtil {

    public static final long INIT_TIME_STAMP = 1472652299200l;

    private static final String SETTING_PREFERENCE = "setting_preference";
    private static final String USER_PREFERENCE = "USER_PREFERENCE";
    private static final String USER_AUTH_PREFERENCE = "USER_AUTH_PREFERENCE";

    private static final String USER_FIREBASE_AUTH_ID = "USER_FIREBASE_AUTH_ID";
    private static final String USER_FIREBASE_AUTH_TYPE = "USER_FIREBASE_AUTH_TYPE";

    public static final int ANONYMOUS_AUTH = 0;
    public static final int PHONE_AUTH = 1;
    public static final int FACEBOOK_AUTH = 2;

    public static final String ITEM_UPDATE_STAMP = "ITEM_UPDATE_STAMP";
    public static final String TYPE_UPDATE_STAMP = "TYPE_UPDATE_STAMP";
    public static final String IMG_UPDATE_STAMP = "IMG_UPDATE_STAMP";
    public static final String ITEM_SERVER_UPDATE_STAMP = "ITEM_SERVER_UPDATE_STAMP";
    public static final String TYPE_SERVER_UPDATE_STAMP = "TYPE_SERVER_UPDATE_STAMP";
    public static final String SCREEN_SIZE = "SCREEN_SIZE";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);



    /********************************************************
    USER SIGN IN ID
    *********************************************************/

    public static void setFireBaseAuthID(Context context, String authID) {
        SharedPreferences.Editor editor = getSharedPreferenceEditor(context);

        editor.putString(USER_FIREBASE_AUTH_ID, authID);
        editor.commit();
    }

    public static String getFireBaseAuthID(Context context) {
        SharedPreferences preferences = getSharedPreference(context);
        String authID = preferences.getString(USER_FIREBASE_AUTH_ID, null);
        return authID;
    }

    public static boolean deleteFireBaseAuthID(Context context) {
        SharedPreferences.Editor editor = getSharedPreferenceEditor(context);
        editor.remove(USER_FIREBASE_AUTH_ID);
        return editor.commit();
    }

    public static void setAuthTypeID(Context context, int authType) {
        SharedPreferences.Editor editor = getSharedPreferenceEditor(context);

        editor.putInt(USER_FIREBASE_AUTH_TYPE, authType);
        editor.commit();
    }

    public static int getAuthTypeID(Context context) {
        SharedPreferences preferences = getSharedPreference(context);
        return preferences.getInt(USER_FIREBASE_AUTH_TYPE, ANONYMOUS_AUTH);
    }

    public static boolean deleteAuthTypeID(Context context) {
        SharedPreferences.Editor editor = getSharedPreferenceEditor(context);
        editor.remove(USER_FIREBASE_AUTH_TYPE);
        return editor.commit();
    }
    /**************************************************************************
     * *    Update server stamp
     ***************************************************************************/
//    public static void setItemServerUpdateStamp(Context context, int curNumber) {
//        SharedPreferences.Editor editor = getSharedPreferenceEditor(context);
//
//        editor.putInt(ITEM_SERVER_UPDATE_STAMP, curNumber);
//        editor.commit();
//    }
//
//    public static int getItemServerUpdateStamp(Context context) {
//        SharedPreferences preferences = getSharedPreference(context);
//        int curNumber = preferences.getInt(ITEM_SERVER_UPDATE_STAMP, 1);
//        return curNumber;
//    }
//
//    public static void setTypeServerUpdateStamp(Context context, int curNumber) {
//        SharedPreferences.Editor editor = getSharedPreferenceEditor(context);
//
//        editor.putInt(TYPE_SERVER_UPDATE_STAMP, curNumber);
//        editor.commit();
//    }
//
//    public static int getTypeServerUpdateStamp(Context context) {
//        SharedPreferences preferences = getSharedPreference(context);
//        int curNumber = preferences.getInt(TYPE_SERVER_UPDATE_STAMP, 1);
//        return curNumber;
//    }
    /**************************************************************************
     * *    Screen Size
     ***************************************************************************/
    public static void setScreenWidthSize(Context context, long millis) {
        SharedPreferences.Editor editor = getSharedPreferenceEditor(context);

        editor.putLong(SCREEN_SIZE, millis);
        editor.commit();
    }

    public static long getScreenWidthSize(Context context) {
        SharedPreferences preferences = getSharedPreference(context);
        long millis = preferences.getLong(SCREEN_SIZE, 0);
        return millis;
    }



    /**************************************************************************
     * *      COMMON SHARED PREFERENCE METHOD
     ***************************************************************************/
    //setting
    private static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(SETTING_PREFERENCE, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getSharedPreferenceEditor(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SETTING_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        return context.getSharedPreferences(SETTING_PREFERENCE, Context.MODE_PRIVATE).edit();
    }

    //user
    private static SharedPreferences getUserSharedPreference(Context context) {
        return context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getUserSharedPreferenceEditor(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        return context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE).edit();
    }

    //user
    private static SharedPreferences getUserAuthSharedPreference(Context context) {
        return context.getSharedPreferences(USER_AUTH_PREFERENCE, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getUserAuthSharedPreferenceEditor(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USER_AUTH_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        return context.getSharedPreferences(USER_AUTH_PREFERENCE, Context.MODE_PRIVATE).edit();
    }
}
