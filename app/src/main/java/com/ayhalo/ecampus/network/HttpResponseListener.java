package com.ayhalo.ecampus.network;

import android.content.Context;
import android.content.DialogInterface;

import com.ayhalo.ecampus.utils.LogUtils;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.error.NotFoundCacheError;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.error.URLError;
import com.yanzhenjie.nohttp.error.UnKnownHostError;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * com.lz.langfang.network
 * Created by wuyh on 2018/3/7.
 */
public class HttpResponseListener<T> implements OnResponseListener<T> {

    private Context mContext;
    /**
     * Dialog.
     */
    private SweetAlertDialog mWaitDialog;
    /**
     * Request.
     */
    private Request<?> mRequest;
    /**
     * 结果回调.
     */
    private HttpListener<T> callback;

    /**
     * @param context      context用来实例化dialog.
     * @param request      请求对象.
     * @param httpCallback 回调对象.
     * @param canCancel    是否允许用户取消请求.
     * @param isLoading    是否显示dialog.
     */
    public HttpResponseListener(Context context, Request<?> request,
                                HttpListener<T> httpCallback, boolean canCancel, boolean isLoading) {
        this.mContext = context;
        this.mRequest = request;
        if (context != null && isLoading) {
            mWaitDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            mWaitDialog.setCancelable(canCancel);
            mWaitDialog.setCanceledOnTouchOutside(false);
            mWaitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mRequest.cancel();
                }
            });
        }
        this.callback = httpCallback;
    }

    /**
     * 开始请求, 这里显示一个dialog.
     */
    @Override
    public void onStart(int what) {
        if (mWaitDialog != null && !mWaitDialog.isShowing())
            mWaitDialog.show();
    }

    /**
     * 结束请求, 这里关闭dialog.
     */
    @Override
    public void onFinish(int what) {
        if (mWaitDialog != null && mWaitDialog.isShowing())
            mWaitDialog.dismiss();
    }

    /**
     * 成功回调.
     */
    @Override
    public void onSucceed(int what, Response<T> response) {
        if (callback != null) {
            // 这里判断一下http响应码，这个响应码问下你们的服务端你们的状态有几种，一般是200成功。
            // w3c标准http响应码：http://www.w3school.com.cn/tags/html_ref_httpmessages.asp
            callback.onSucceed(what, response);
        }
    }

    /**
     * 失败回调.
     */
    @Override
    public void onFailed(int what, Response<T> response) {
        Exception exception = response.getException();
        if (exception instanceof NetworkError) {// 网络不好
            //ToastUtils.showLong(R.string.error_please_check_network);
        } else if (exception instanceof TimeoutError) {// 请求超时
            //ToastUtils.showShort(R.string.error_timeout);
        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
            //ToastUtils.showShort(R.string.error_not_found_server);
        } else if (exception instanceof URLError) {// URL是错的
            //ToastUtils.showShort(R.string.error_url_error);
        } else if (exception instanceof NotFoundCacheError) {
            // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
        } else {
            //ToastUtils.showShort(R.string.error_unknow);
        }
        LogUtils.e("错误：" + exception.getMessage());
        if (callback != null)
            callback.onFailed(what, response);
    }

}