package com.ayhalo.ecampus.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * com.lz.activity.langfang3.utils
 * Created by wuyh on 2018/3/21.
 */

public class NetUtils {

    public static Boolean isNetworkReachable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current = cm.getActiveNetworkInfo();
        if (current == null) {
            return false;
        }
        return (current.isAvailable());
    }
}