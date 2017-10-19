package com.longyuan.zhihuretrofitvolley;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.longyuan.zhihuretrofitvolley.injection.DaggerNetworkComponent;
import com.longyuan.zhihuretrofitvolley.injection.NetworkModule;
import com.longyuan.zhihuretrofitvolley.pojo.Stories;
import com.longyuan.zhihuretrofitvolley.pojo.Story;
import com.longyuan.zhihuretrofitvolley.retrofit.api.StoryService;
import com.longyuan.zhihuretrofitvolley.utils.GsonRequest;
import com.longyuan.zhihuretrofitvolley.utils.NewsListAdapter;

import com.longyuan.zhihuretrofitvolley.utils.NewsListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {

    @BindView(R.id.news_list)
    RecyclerView mNewList;

    @BindView(R.id.volley)
    Button mButtonVBolley;

    @BindView(R.id.retrofit)
    Button mButtonRetrofit;

    @Inject
    RequestQueue mRequestQueue;

    @Inject
    StoryService mStoryService;

    private  List<Story> mStories;

    private NewsListAdapter newsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        //mButtonVBolley.setText("Hello From butterknife haha");

        DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule("https://news-at.zhihu.com/api/4/",getApplicationContext()))
                .build().inject(this);

        //mNewList = (RecyclerView) findViewById(R.id.news_list);


        List<Story> newsList= new ArrayList();

        newsListAdapter = new NewsListAdapter(this,newsList);

        mNewList.setAdapter(newsListAdapter);

        mNewList.setLayoutManager(new LinearLayoutManager(mNewList.getContext()));

        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(mNewList.getContext(),
                DividerItemDecoration.VERTICAL);

        mNewList.addItemDecoration(horizontalDecoration);

        //testRetrofit();

    }

    @OnClick(R.id.retrofit)
    public void testRetrofit(){

        mStoryService.getStories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> processData(data),
                        throwable -> processError(throwable));

        mButtonRetrofit.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        mButtonVBolley.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }

    @OnClick(R.id.clean)
    public void clean(){
        //mStories.clear();
        //newsListAdapter.notifyDataSetChanged();
        newsListAdapter.updateData(Collections.EMPTY_LIST);
        mButtonRetrofit.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        mButtonVBolley.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }

    @OnClick(R.id.volley)
    public void testVolley(){

        String url = "https://news-at.zhihu.com/api/4/news/latest";
        // Volley use customized Request to parse json
        GsonRequest<Stories> gsonRequest = new GsonRequest<>(url, Stories.class, null, new Response.Listener<Stories>() {
            @Override
            public void onResponse(Stories response) {
                mStories = response.getStories();
               // mButtonVBolley.setBackgroundColor(android.R.color.holo_blue_bright);

                mButtonVBolley.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
                mButtonRetrofit.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                newsListAdapter.updateData(mStories);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(gsonRequest);

    }

    private void processData(Stories stories){
        mStories = stories.getStories();
        newsListAdapter.updateData(mStories);
    }

    private void processError(Throwable e) {
        Log.e("Test", e.getLocalizedMessage(), e);
    }

}
