package com.longyuan.zhihuretrofitvolley.retrofit.api;

import com.longyuan.zhihuretrofitvolley.pojo.LongComments;
import com.longyuan.zhihuretrofitvolley.pojo.ShortComments;
import com.longyuan.zhihuretrofitvolley.pojo.Stories;
import com.longyuan.zhihuretrofitvolley.pojo.StoryDetail;
import com.longyuan.zhihuretrofitvolley.pojo.StoryExtraInfo;
import com.longyuan.zhihuretrofitvolley.pojo.ThemeStories;
import com.longyuan.zhihuretrofitvolley.pojo.Themes;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by LONGYUAN on 2017/10/19.
 */

public interface StoryService {

    @GET("news/latest")
    Observable<Stories> getStories();


    @GET("news/{id}")
    Observable<StoryDetail> getStoryDetail(@Path("id") String id);

    @GET("story-extra/{id}")
    Observable<StoryExtraInfo> getStoryExtraInfo(@Path("id") String id);

    @GET("story/{id}/long-comments")
    Observable<LongComments> getLongComments(@Path("id") String id);

    @GET("story/{id}/short-comments")
    Observable<ShortComments> getShortComments(@Path("id") String id);

    @GET("themes")
    Observable<Themes> getThemes();

    @GET("theme/{themeId}")
    Observable<ThemeStories> getThemeStories(@Path("themeId") String themeId);


}
