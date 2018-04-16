package com.ayhalo.ecampus.utils;


/**
 * com.lz.langfang.utils
 * Created by wuyh on 2018/3/7.
 */

public class LogUtils {

    private static String tag = "newlangfang";
    private static Boolean isDebug = true;

    public static void i(String msg) {
        if (isDebug)
            android.util.Log.i(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug)
            android.util.Log.i(tag, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            android.util.Log.d(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            android.util.Log.d(tag, msg);
    }

    public static void w(String msg) {
        if (isDebug)
            android.util.Log.w(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (isDebug)
            android.util.Log.w(tag, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            android.util.Log.v(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            android.util.Log.v(tag, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            android.util.Log.e(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            android.util.Log.e(tag, msg);
    }
}
