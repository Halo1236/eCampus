package com.ayhalo.ecampus.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.List;

/**
 * com.lz.activity.langfang.utils
 * Created by wuyh on 2018/3/12.
 */

public class UrlUtils {
    public static String combinaStr(String str, List<String> params)
            throws RuntimeException {
        try {
            String[] strs = str.split("[#]");
            String newStr = "";
            for (int i = 0; i < strs.length; i++) {
                String itemUrl = strs[i];
                newStr += itemUrl;
                if (i >= 0 && params != null && i < params.size())
                    newStr += params.get(i);
            }

            return newStr;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static String combinaStr(String str, String[] params)
            throws RuntimeException {
        try {
            String[] strs = str.split("[#]");
            String newStr = "";
            for (int i = 0; i < strs.length; i++) {
                String itemUrl = strs[i];
                newStr += itemUrl;
                if (i >= 0 && params != null && i < params.length)
                    newStr += params[i];
            }
            return newStr;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static String getFileNameFromUrl(String url) {
        if (url != null && !url.equals("")) {
            return url.substring(url.lastIndexOf("/") + 1);
        }
        return url;
    }

    public static <T> T getObjectData(String jsonString, Class<T> type) {

        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, type);
        } catch (JsonSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t;
    }
}
