package com.ayhalo.ecampus;

import android.app.Application;
import android.content.Context;

import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DiskCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;

/**
 * com.ayhalo.ecampus
 * Created by wuyh on 2018/3/1.
 */
public class BaseApplication extends Application {

    private static Context mContext;

    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        InitializationConfig initial = InitializationConfig.newBuilder(mContext)
                .connectionTimeout(10000)
                .readTimeout(10000)
                .networkExecutor(new OkHttpNetworkExecutor())
                .cacheStore(new DiskCacheStore(mContext))
                .cookieStore(new DBCookieStore(mContext).setEnable(true))
                .build();
        NoHttp.initialize(initial);
        Logger.setDebug(true);
        Logger.setTag("nohttp___debug");

    }
}
