package com.ayhalo.ecampus.network;

import com.ayhalo.ecampus.databases.bean.Topic;
import com.google.gson.Gson;

/**
 * com.ayhalo.ecampus.network
 * create by wuyh 2018/4/16.
 */

public class TopicToGson {


    public static Topic handleTopicToGson(String response) {
        try {
            return new Gson().fromJson(response, Topic.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
