package com.ayhalo.ecampus.network;


import android.content.Context;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * <p>针对队列的一个全局单例封装。</p>
 * Created by YanZhenjie on 2017/6/18.
 */
public class CallServer {

    private static CallServer sInstance;

    public static CallServer getInstance() {
        if (sInstance == null)
            synchronized (CallServer.class) {
                if (sInstance == null)
                    sInstance = new CallServer();
            }
        return sInstance;
    }

    private RequestQueue mRequestQueue;
    private DownloadQueue mDownloadQueue;


    private CallServer() {
        mRequestQueue = NoHttp.newRequestQueue(8);
        mDownloadQueue = NoHttp.newDownloadQueue(3);
    }

    public <T> void request(int what, Request<T> request, OnResponseListener<T> listener) {
        mRequestQueue.add(what, request, listener);
    }

    public <T> void request(int what, Request<T> request, Context context, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
        mRequestQueue.add(what, request, new HttpResponseListener<>(context, request, callback, canCancel, isLoading));
    }

    public void download(int what, DownloadRequest request, DownloadListener listener) {
        mDownloadQueue.add(what, request, listener);
    }

}
