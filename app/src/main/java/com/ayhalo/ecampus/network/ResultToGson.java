package com.ayhalo.ecampus.network;

import com.ayhalo.ecampus.databases.bean.Result;
import com.google.gson.Gson;

/**
 * com.ayhalo.ecampus.network
 * create by wuyh 2018/4/22.
 */

public class ResultToGson {

    public static Result handleResultToGson(String response) {
        try {
            return new Gson().fromJson(response, Result.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
