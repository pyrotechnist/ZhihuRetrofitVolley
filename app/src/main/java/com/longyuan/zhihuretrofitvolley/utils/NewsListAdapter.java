package com.longyuan.zhihuretrofitvolley.utils;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.longyuan.zhihuretrofitvolley.R;
import com.longyuan.zhihuretrofitvolley.pojo.Story;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by loxu on 19/10/2017.
 */


public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListAdapterVewHolder> {


    List<Story> mStoryList;

    Context mContext;

    private OnItemClickListener.OnStoryItemClickListener mOnItemClickListener;

    public NewsListAdapter(Context context,List<Story> storyList){

        mContext = context;
        mStoryList = storyList;

    }

    @Override
    public NewsListAdapterVewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);

        return new NewsListAdapterVewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsListAdapterVewHolder holder, int position) {

        Story story = mStoryList.get(position);

        holder.textViewTitle.setText(story.getTitle());

        Glide.with(mContext).load(story.getImages().get(0)).into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(story);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mStoryList.size();
    }

    public static class NewsListAdapterVewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.news_title)
        TextView textViewTitle;

        @BindView(R.id.image)
        ImageView imageView;

        public NewsListAdapterVewHolder(View itemView) {
            super(itemView);

            //textViewTitle = (TextView) itemView.findViewById(R.id.news_title);
            ButterKnife.bind(this,itemView);
        }
    }

    public void updateData(List<Story> stories){

        mStoryList = stories;

        notifyDataSetChanged();
    }

    public void cleanData(){

        mStoryList.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener.OnStoryItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}

