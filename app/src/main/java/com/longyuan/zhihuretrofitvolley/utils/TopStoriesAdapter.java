package com.longyuan.zhihuretrofitvolley.utils;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.longyuan.zhihuretrofitvolley.R;
import com.longyuan.zhihuretrofitvolley.pojo.Story;
import com.longyuan.zhihuretrofitvolley.pojo.TopStory;

import java.util.List;

/**
 * Created by LONGYUAN on 2017/10/23.
 */

public class TopStoriesAdapter extends PagerAdapter {

    Context mContext;

    List<TopStory> mTopStories;

    private OnItemClickListener mOnItemClickListener;

    public TopStoriesAdapter(Context context, List<TopStory> imageList) {
        this.mContext = context;
        mTopStories = imageList;
    }


    @Override
    public int getCount() {
        return mTopStories.size();
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((View) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {

        View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.topstory_item,container,false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_topStory);

        TextView textView  = (TextView) itemView.findViewById(R.id.title_topStory);

        TopStory topStory = mTopStories.get(i);

        Glide.with(mContext).load(topStory.getImage()).centerCrop().into(imageView);

        ((ViewPager) container).addView(itemView, 0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(topStory);
            }
        });

        textView.setText(topStory.getTitle());

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((View) obj);
    }

    public void updateData(List<TopStory> topStories){

        mTopStories = topStories;

        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        this.mOnItemClickListener = onItemClickListener;
    }
}
