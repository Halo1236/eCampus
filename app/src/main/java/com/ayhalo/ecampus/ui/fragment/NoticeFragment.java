package com.ayhalo.ecampus.ui.fragment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ayhalo.ecampus.Global;
import com.ayhalo.ecampus.R;
import com.ayhalo.ecampus.databases.bean.Topic;
import com.ayhalo.ecampus.network.CallServer;
import com.ayhalo.ecampus.network.HttpListener;
import com.ayhalo.ecampus.network.TopicToGson;
import com.ayhalo.ecampus.ui.adapter.ChannelAdapter;
import com.ayhalo.ecampus.ui.base.BaseFragment;
import com.ayhalo.ecampus.ui.widgets.OnItemClickListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

/**
 * com.ayhalo.ecampus.ui.fragment
 * create by wuyh 2018/4/16.
 */

public class NoticeFragment extends BaseFragment implements HttpListener<String> {

    private Toolbar toolbar;
    private List<Topic.ResultsBean> articleList;
    private Request<String> newsJson;
    private RecyclerView recyclerView;
    private ChannelAdapter adapter;

    public static NoticeFragment newInstance() {
        NoticeFragment fragment = new NoticeFragment();
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
        recyclerView = findView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new ChannelAdapter(getContext(), R.layout.item_channel_list, null);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Paper paper = papers.get(position);
//                Intent intent = new Intent(getContext(), EpaperDisplayActivity.class);
//                intent.putExtra("paperName", paper.getName());
//                intent.putExtra("paperId", paper.getId());
//                intent.putExtra("paperUpdateTime", paper.getUpdateTime());
//                intent.putExtra("paperVersionId", paper.getVersionId());
//                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });

    }

    @Override
    protected void lazyFetchData() {
        String url = Global.NOTICESURL;
        newsJson = NoHttp.createStringRequest(url, RequestMethod.GET);
        CallServer.getInstance().request(Global.NEWSWHAT, newsJson, getContext(), this, false, false);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        String data = response.get();
        Topic newsData = TopicToGson.handleTopicToGson(data);
        if (newsData != null) {
            articleList = newsData.getResults();
            adapter.setNewData(articleList);
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }
}
