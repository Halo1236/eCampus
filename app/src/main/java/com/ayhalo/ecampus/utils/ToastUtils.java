package com.ayhalo.ecampus.utils;

import android.widget.Toast;

import com.ayhalo.ecampus.BaseApplication;

/**
 * com.lz.langfang.utils
 * Created by wuyh on 2018/3/6.
 */

public class ToastUtils {

    public static void showShort(int resId) {
        Toast.makeText(BaseApplication.getAppContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(String message) {
        Toast.makeText(BaseApplication.getAppContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resId) {
        Toast.makeText(BaseApplication.getAppContext(), resId, Toast.LENGTH_LONG).show();
    }

    public static void showLong(String message) {
        Toast.makeText(BaseApplication.getAppContext(), message, Toast.LENGTH_LONG).show();
    }
}