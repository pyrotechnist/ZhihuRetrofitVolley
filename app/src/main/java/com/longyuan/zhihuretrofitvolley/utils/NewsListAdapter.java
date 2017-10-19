package com.longyuan.zhihuretrofitvolley.utils;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longyuan.zhihuretrofitvolley.R;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by loxu on 19/10/2017.
 */


public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListAdapterVewHolder> {


    List<String> mNewsList;

    public NewsListAdapter(List<String> newsList){

        mNewsList = newsList;

    }

    @Override
    public NewsListAdapterVewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);

        return new NewsListAdapterVewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsListAdapterVewHolder holder, int position) {

        String news = mNewsList.get(position);

        holder.textViewTitle.setText(news);

    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public static class NewsListAdapterVewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.news_title)
        TextView textViewTitle;

        public NewsListAdapterVewHolder(View itemView) {
            super(itemView);

            //textViewTitle = (TextView) itemView.findViewById(R.id.news_title);
            ButterKnife.bind(this,itemView);
        }
    }
}

