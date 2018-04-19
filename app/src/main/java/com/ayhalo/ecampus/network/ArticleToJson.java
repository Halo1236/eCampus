package com.ayhalo.ecampus.network;

import com.ayhalo.ecampus.databases.bean.Article;
import com.google.gson.Gson;

/**
 * com.ayhalo.ecampus.network
 * create by wuyh 2018/4/19.
 */

public class ArticleToJson {
    public static Article handleArticleToGson(String response) {
        try {
            return new Gson().fromJson(response, Article.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
