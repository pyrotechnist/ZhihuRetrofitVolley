package com.longyuan.zhihuretrofitvolley.pojo;

import java.util.List;

/**
 * Created by LONGYUAN on 2017/10/23.
 */

public class StoryBase {

    private String gaPrefix;
    private String id;
    private int type;
    private String title;

    private StoryExtraInfo storyExtraInfo;

    public void setGaPrefix(String gaPrefix){
        this.gaPrefix = gaPrefix;
    }

    public String getGaPrefix(){
        return gaPrefix;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setType(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public StoryExtraInfo getStoryExtraInfo() {
        return storyExtraInfo;
    }

    public void setStoryExtraInfo(StoryExtraInfo storyExtraInfo) {
        this.storyExtraInfo = storyExtraInfo;
    }

    @Override
    public String toString(){
        return
                "Story{" +
                        ",ga_prefix = '" + gaPrefix + '\'' +
                        ",id = '" + id + '\'' +
                        ",type = '" + type + '\'' +
                        ",title = '" + title + '\'' +
                        "}";
    }
}
