package com.ayhalo.ecampus.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ayhalo.ecampus.R;
import com.ayhalo.ecampus.databases.bean.Topic;
import com.ayhalo.ecampus.ui.widgets.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * com.ayhalo.ecampus.ui.adapter
 * create by wuyh 2018/4/16.
 */

public class ChannelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Topic.ResultsBean> datas = new ArrayList<>();
    private OnItemClickListener clickListener;
    private Context context;
    private LayoutInflater inflater;
    private int resourceID;

    public ChannelAdapter(Context context, int resourceID, List<Topic.ResultsBean> datas) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.resourceID = resourceID;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(resourceID, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Topic.ResultsBean item = datas.get(position);
        if (holder instanceof ChannelAdapter.Holder) {
            ((Holder) holder).title.setText(item.getTitle());
            ((Holder) holder).publisher_time.setText(item.getPublish_time());
            ((ChannelAdapter.Holder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public long getItemId(int position) {
        return datas.get(position).hashCode();
    }

    public void setNewData(List<Topic.ResultsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.clickListener = mOnItemClickListener;
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView title;
        TextView publisher_time;

        public Holder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.article_title);
            publisher_time = itemView.findViewById(R.id.author_update);
        }
    }
}
