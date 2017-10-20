package com.longyuan.zhihuretrofitvolley.utils;

import com.longyuan.zhihuretrofitvolley.pojo.Story;

/**
 * Created by loxu on 20/10/2017.
 */

public interface OnItemClickListener {

    void onItemClick(Story item);

    void onItemLongClick(Story item,int position);

}
