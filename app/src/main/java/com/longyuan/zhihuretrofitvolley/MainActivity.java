package com.longyuan.zhihuretrofitvolley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.longyuan.zhihuretrofitvolley.injection.DaggerNetworkComponent;
import com.longyuan.zhihuretrofitvolley.injection.NetworkModule;
import com.longyuan.zhihuretrofitvolley.pojo.Stories;
import com.longyuan.zhihuretrofitvolley.pojo.Story;
import com.longyuan.zhihuretrofitvolley.retrofit.api.StoryService;
import com.longyuan.zhihuretrofitvolley.storydetail.StoryDetailActivity;
import com.longyuan.zhihuretrofitvolley.utils.GsonRequest;
import com.longyuan.zhihuretrofitvolley.utils.NewsListAdapter;

import com.longyuan.zhihuretrofitvolley.utils.NewsListAdapter;
import com.longyuan.zhihuretrofitvolley.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.longyuan.zhihuretrofitvolley.storydetail.StoryDetailActivity.EXTRA_STORY_ID;
import static com.longyuan.zhihuretrofitvolley.storydetail.StoryDetailActivity.USE_VOLLEY;
import static java.security.AccessController.getContext;

public class MainActivity extends Activity {

    @BindView(R.id.news_list)
    RecyclerView mStoryList;

    @BindView(R.id.volley)
    Button mButtonVBolley;

    @BindView(R.id.retrofit)
    Button mButtonRetrofit;

    @Inject
    RequestQueue mRequestQueue;

    @Inject
    StoryService mStoryService;

    private  List<Story> mStories;

    private NewsListAdapter mStoryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule("https://news-at.zhihu.com/api/4/",getApplicationContext()))
                .build().inject(this);

        setRecyclerView();
    }


    private void setRecyclerView(){

        List<Story> newsList= new ArrayList();

        mStoryListAdapter = new NewsListAdapter(this,newsList);

        mStoryList.setAdapter(mStoryListAdapter);

        mStoryList.setLayoutManager(new LinearLayoutManager(mStoryList.getContext()));

        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(mStoryList.getContext(),
                DividerItemDecoration.VERTICAL);

        mStoryListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Story item) {
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),StoryDetailActivity.class);
                intent.putExtra(EXTRA_STORY_ID, item.getId());

                intent.putExtra(USE_VOLLEY, true);
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(Story item, int position) {

            }
        });

        //mNewList.addItemDecoration(horizontalDecoration);
    }

    @OnClick(R.id.retrofit)
    public void testRetrofit(){

        mStoryService.getStories()
                .subscribeOn(Schedulers.io())
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
        mStoryListAdapter.updateData(Collections.EMPTY_LIST);
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
                mStoryListAdapter.updateData(mStories);
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
        mStoryListAdapter.updateData(mStories);
    }

    private void processError(Throwable e) {
        Log.e("Test", e.getLocalizedMessage(), e);
    }

}
