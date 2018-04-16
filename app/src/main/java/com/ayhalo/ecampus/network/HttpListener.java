package com.ayhalo.ecampus.network;

import com.yanzhenjie.nohttp.rest.Response;

/**
 * com.lz.langfang.network
 * Created by wuyh on 2018/3/7.
 */

public interface HttpListener<T> {

    void onSucceed(int what, Response<T> response);

    void onFailed(int what, Response<T> response);

}