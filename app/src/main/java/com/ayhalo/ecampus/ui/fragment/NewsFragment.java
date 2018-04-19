package com.ayhalo.ecampus.ui.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;

import com.ayhalo.ecampus.Global;
import com.ayhalo.ecampus.R;
import com.ayhalo.ecampus.databases.bean.Topic;
import com.ayhalo.ecampus.network.CallServer;
import com.ayhalo.ecampus.network.HttpListener;
import com.ayhalo.ecampus.network.TopicToGson;
import com.ayhalo.ecampus.ui.activity.ArticleContentActivity;
import com.ayhalo.ecampus.ui.adapter.ChannelAdapter;
import com.ayhalo.ecampus.ui.base.BaseFragment;
import com.ayhalo.ecampus.ui.widgets.OnItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * com.ayhalo.ecampus.ui.fragment
 * create by wuyh 2018/4/15.
 */

public class NewsFragment extends BaseFragment implements HttpListener<String>, OnItemClickListener, OnRefreshLoadMoreListener {

    private static final int REFRESHMORE = 1;
    private static final int LOADMORE = 2;
    private Toolbar toolbar;
    private List<Topic.ResultsBean> articleList = new ArrayList<>();
    private Request<String> newsJson;
    private RecyclerView recyclerView;
    private ChannelAdapter adapter;
    private RefreshLayout refreshLayout;
    private int page = 1;
    private int mode = REFRESHMORE;

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        toolbar = findView(R.id.toolbar);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        SmartRefreshLayout refreshLayout = findView(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setOnRefreshLoadMoreListener(this);
        refreshLayout.setHeaderHeight(50);
        refreshLayout.setFooterHeight(30);
        refreshLayout.setEnableAutoLoadMore(true);
        refreshLayout.autoRefresh();

        recyclerView = findView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new ChannelAdapter(getContext(), R.layout.item_channel_list, null);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void lazyFetchData() {
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        String data = response.get();
        Topic newsData = TopicToGson.handleTopicToGson(data);
        if (newsData != null) {
            if (mode == REFRESHMORE) {
                refreshLayout.finishRefresh();
                articleList.clear();
            } else if (mode == LOADMORE) {
                refreshLayout.finishLoadMore();
                page++;
            }
            articleList.addAll(newsData.getResults());
            adapter.setNewData(articleList);
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {
        if (mode == REFRESHMORE) {
            refreshLayout.finishRefresh(false);
        } else if (mode == LOADMORE)
            refreshLayout.finishLoadMore(false);
    }

    @Override
    public void onItemClick(View view, int position) {
        Topic.ResultsBean resultsBean = articleList.get(position);
        Intent intent = new Intent(getActivity(), ArticleContentActivity.class);
        intent.putExtra("url", Base64.encodeToString(resultsBean.getArticle_url().getBytes(), Base64.DEFAULT));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    private void getData(int page) {
        String url = Global.HOST + "api/topic/data/20/" + page + "/0/json";
        newsJson = NoHttp.createStringRequest(url, RequestMethod.GET);
        CallServer.getInstance().request(Global.NEWSWHAT, newsJson, getContext(), this, false, false);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        getData(page + 1);
        mode = LOADMORE;
        this.refreshLayout = refreshLayout;
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
        page = 1;
        getData(page);
        mode = REFRESHMORE;
        this.refreshLayout = refreshLayout;
    }
}
