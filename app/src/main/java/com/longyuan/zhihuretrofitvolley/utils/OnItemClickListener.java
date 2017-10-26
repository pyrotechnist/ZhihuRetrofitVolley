package com.longyuan.zhihuretrofitvolley.utils;

import com.longyuan.zhihuretrofitvolley.pojo.CommentItem;
import com.longyuan.zhihuretrofitvolley.pojo.Story;
import com.longyuan.zhihuretrofitvolley.pojo.StoryBase;

/**
 * Created by loxu on 20/10/2017.
 */



public interface OnItemClickListener {

    interface OnStoryItemClickListener{

    void onItemClick(StoryBase item);

    void onItemLongClick(StoryBase item,int position);

    }

    interface OnCommentItemClickListener{

        void onItemClick(CommentItem item);

        void onItemLongClick(CommentItem item,int position);
    }
}
