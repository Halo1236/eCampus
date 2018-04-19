package com.ayhalo.ecampus.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ayhalo.ecampus.Global;
import com.ayhalo.ecampus.R;
import com.ayhalo.ecampus.databases.bean.Article;
import com.ayhalo.ecampus.network.ArticleToJson;
import com.ayhalo.ecampus.network.CallServer;
import com.ayhalo.ecampus.network.HttpListener;
import com.ayhalo.ecampus.ui.base.BaseActivity;
import com.ayhalo.ecampus.utils.LogUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.zzhoujay.richtext.CacheType;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;

/**
 * com.ayhalo.ecampus.ui.activity
 * Created by wuyh on 2018/3/1.
 */

public class ArticleContentActivity extends BaseActivity implements HttpListener<String> {

    private static final String TAG = "ArticleContentActivity";
    private TextView title;
    private TextView author;
    private TextView content;
    private String article_url;
    private Request<String> newsJson;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_content;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        article_url = getIntent().getStringExtra("url");
        title = findView(R.id.iv_title);
        author = findView(R.id.iv_author);
        content = findView(R.id.iv_content);
        RichText.initCacheDir(this);
    }

    @Override
    protected void loadData() {
        getData();
    }

    private void getData() {
        LogUtils.d(TAG, article_url);
        String url = Global.HOST + "api/article/data/" + article_url.trim() + "/json";
        newsJson = NoHttp.createStringRequest(url, RequestMethod.GET);
        CallServer.getInstance().request(Global.ARTICLEWHAT, newsJson, this, this, false, false);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        String data = response.get();
        Article newsData = ArticleToJson.handleArticleToGson(data);
        if (newsData != null) {
            title.setText(newsData.getResults().getTitle());
            author.setText(String.format("%s  %s", newsData.getResults().getPublisher(), newsData.getResults().getPublish_time()));
            content.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            LogUtils.d(TAG, newsData.getResults().getContent());
            RichText.fromHtml(newsData.getResults().getContent())
                    .bind(this)
                    .autoFix(true)
                    .autoPlay(true)
                    .cache(CacheType.ALL)
                    .scaleType(ImageHolder.ScaleType.FIT_CENTER)
                    .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT)
                    .into(content);
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }

    @Override
    protected void onDestroy() {
        RichText.recycle();
        super.onDestroy();
    }
}
