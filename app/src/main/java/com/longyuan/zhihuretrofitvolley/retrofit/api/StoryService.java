package com.longyuan.zhihuretrofitvolley.retrofit.api;

import com.longyuan.zhihuretrofitvolley.pojo.Stories;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by LONGYUAN on 2017/10/19.
 */

public interface StoryService {

    @GET("news/latest")
    Observable<Stories> getStories();

}
