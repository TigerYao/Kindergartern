package com.junbaole.kindergartern.data.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.junbaole.kindergartern.data.model.UserInfo;

public class SharedPreferenceUtil {

    private static final String FILE_NAME = "liangrenwang";


    public static void putString(Context mContext, String key, String value) {
        if (null == mContext || TextUtils.isEmpty(key)) {
            return;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        apply(editor);
    }

    public static String getString(Context mContext, String key, String defaultValue) {
        if (null == mContext || TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    public static void putInt(Context mContext, String key, int value) {
        if (null == mContext || TextUtils.isEmpty(key)) {
            return;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        apply(editor);
    }

    public static int getInt(Context mContext, String key, int defaultValue) {
        if (null == mContext || TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static void putLong(Context mContext, String key, long value) {
        if (null == mContext || TextUtils.isEmpty(key)) {
            return;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        apply(editor);
    }

    public static long getLong(Context mContext, String key, long defaultValue) {
        if (null == mContext || TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static void putBoolean(Context mContext, String key, boolean value) {
        if (null == mContext || TextUtils.isEmpty(key)) {
            return;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        apply(editor);
    }

    public static void putUserInfo(Context mContext,UserInfo userInfo){
        if (null == mContext || userInfo==null) {
            return;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("user_id", userInfo.id);
        editor.putString("user_name", userInfo.name);
        editor.putString("user_mobile", userInfo.phoneNum);
        apply(editor);
    }

    public static UserInfo getUserInfo(Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        UserInfo userInfo = new UserInfo();
        userInfo.id = sharedPreferences.getInt("user_id", -1);
        userInfo.name = sharedPreferences.getString("user_name", "");
        userInfo.phoneNum = sharedPreferences.getString("user_mobile", "");
        return userInfo;
    }

    public static boolean getBoolean(Context mContext, String key, boolean defaultValue) {
        if (null == mContext || TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static void putFloat(Context mContext, String key, float value) {
        if (null == mContext || TextUtils.isEmpty(key)) {
            return;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        apply(editor);
    }

    public static float getFloat(Context mContext, String key, float defaultValue) {
        if (null == mContext || TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public static void clear(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        apply(editor);
    }

//    public static void putStringSet(Context mContext, String key, Set<String> value) {
//        if (null == mContext || TextUtils.isEmpty(key)) {
//            return;
//        }
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putStringSet(key, value);
//        apply(editor);
//    }
//
//    public static Set<String> getStringSet(Context mContext, String key, Set<String> defaultValue) {
//        if (null == mContext || TextUtils.isEmpty(key)) {
//            return defaultValue;
//        }
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getStringSet(key, defaultValue);
//    }

    //*****************************************************************************
    //多进程Preferences数据共享
    public static void putStringProcess(Context ctx, String key, String value) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("preference_mu", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        apply(editor);
    }

    public static String getStringProcess(Context ctx, String key, String defValue) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("preference_mu", Context.MODE_MULTI_PROCESS);
        return sharedPreferences.getString(key, defValue);
    }
    //*****************************************************************************

    /**
     * use new api to reduce file operate
     *
     * @param editor editor
     */
    public static void apply(SharedPreferences.Editor editor) {
        if (SdkVersionUtils.hasGingerbread()) {
            editor.apply();
        } else {
            editor.commit();
        }
    }


}