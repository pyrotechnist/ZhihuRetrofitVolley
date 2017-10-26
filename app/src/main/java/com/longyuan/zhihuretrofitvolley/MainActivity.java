package com.longyuan.zhihuretrofitvolley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.longyuan.zhihuretrofitvolley.injection.DaggerNetworkComponent;
import com.longyuan.zhihuretrofitvolley.injection.NetworkModule;
import com.longyuan.zhihuretrofitvolley.pojo.Stories;
import com.longyuan.zhihuretrofitvolley.pojo.Story;
import com.longyuan.zhihuretrofitvolley.pojo.StoryBase;
import com.longyuan.zhihuretrofitvolley.pojo.StoryExtraInfo;
import com.longyuan.zhihuretrofitvolley.pojo.TopStory;
import com.longyuan.zhihuretrofitvolley.retrofit.api.StoryService;
import com.longyuan.zhihuretrofitvolley.storydetail.StoryDetailActivity;
import com.longyuan.zhihuretrofitvolley.utils.GsonRequest;
import com.longyuan.zhihuretrofitvolley.utils.NewsListAdapter;

import com.longyuan.zhihuretrofitvolley.utils.OnItemClickListener;
import com.longyuan.zhihuretrofitvolley.utils.TopStoriesAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

import static com.longyuan.zhihuretrofitvolley.storydetail.StoryDetailActivity.EXTRA_STORY_ID;
import static com.longyuan.zhihuretrofitvolley.storydetail.StoryDetailActivity.USE_VOLLEY;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.news_list)
    RecyclerView mStoryList;

    //@BindView(R.id.volley)
    //Button mButtonVBolley;

    //@BindView(R.id.retrofit)
    //Button mButtonRetrofit;

    @BindView(R.id.viewPage_topStories)
    ViewPager viewPagerTopStories;

    @Inject
    RequestQueue mRequestQueue;

    @Inject
    StoryService mStoryService;

    private  List<Story> mStories;

    private  List<TopStory> mTopStories;

    private NewsListAdapter mStoryListAdapter;

    private TopStoriesAdapter mTopStoriesAdapter;

    private Map<String,Story> mStoriesMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule("https://news-at.zhihu.com/api/4/",getApplicationContext()))
                .build().inject(this);

        setRecyclerView();
        setupViewPager();

        testRetrofit();
    }


    private void setRecyclerView(){

        List<Story> newsList= new ArrayList();

        mStoryListAdapter = new NewsListAdapter(this,newsList);

        mStoryList.setAdapter(mStoryListAdapter);

        mStoryList.setLayoutManager(new LinearLayoutManager(mStoryList.getContext()));

        mStoryList.setNestedScrollingEnabled(false);

        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(mStoryList.getContext(),
                DividerItemDecoration.VERTICAL);

        mStoryListAdapter.setOnItemClickListener(new OnItemClickListener.OnStoryItemClickListener() {
            @Override
            public void onItemClick(StoryBase item) {
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),StoryDetailActivity.class);
                intent.putExtra(EXTRA_STORY_ID, item.getId());

                intent.putExtra(USE_VOLLEY, false);
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(StoryBase item, int position) {

            }
        });

        //mNewList.addItemDecoration(horizontalDecoration);
    }


    private void setupViewPager(){

        List<TopStory> topStories = new ArrayList<>();

        mTopStoriesAdapter = new TopStoriesAdapter(this,topStories);

        mTopStoriesAdapter.setOnItemClickListener(new OnItemClickListener.OnStoryItemClickListener() {
            @Override
            public void onItemClick(StoryBase item) {
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),StoryDetailActivity.class);
                intent.putExtra(EXTRA_STORY_ID, item.getId());

                intent.putExtra(USE_VOLLEY, false);
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(StoryBase item, int position) {

            }
        });


        viewPagerTopStories.setAdapter(mTopStoriesAdapter);


    }

    //@OnClick(R.id.retrofit)
    public void testRetrofit(){

        mStoryService.getStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> processData(data),
                        throwable -> processError(throwable));




        //mButtonRetrofit.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        //mButtonVBolley.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }

    //@OnClick(R.id.clean)
    public void clean(){
        //mStories.clear();
        //newsListAdapter.notifyDataSetChanged();
        mStoryListAdapter.updateData(Collections.EMPTY_LIST);
        //mButtonRetrofit.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        //mButtonVBolley.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }

    //@OnClick(R.id.volley)
    public void testVolley(){

        String url = "https://news-at.zhihu.com/api/4/news/latest";
        // Volley use customized Request to parse json
        GsonRequest<Stories> gsonRequest = new GsonRequest<>(url, Stories.class, null, new Response.Listener<Stories>() {
            @Override
            public void onResponse(Stories response) {
                mStories = response.getStories();
               // mButtonVBolley.setBackgroundColor(android.R.color.holo_blue_bright);

                //mButtonVBolley.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
                //mButtonRetrofit.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                mStoryListAdapter.updateData(mStories);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(gsonRequest);

    }

    private void processDataNew(){

/*
        Observable<Story> storyObservable = mStoryService.getStories()
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<Stories, Observable<Story>>() {
                    @Override
                    public Observable<Story> call(Stories stories) {
                        mStories = stories.getStories();
                        return Observable.from(mStories);
                    }
                });*/


        Observable<Story> storyObservable = Observable.from(mStories);

        Observable<StoryExtraInfo> storyExtraInfoObservable = Observable.from(mStories)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<Story, Observable<StoryExtraInfo>>() {
                    @Override
                    public Observable<StoryExtraInfo> call(Story story) {
                        return mStoryService.getStoryExtraInfo(story.getId());
                    }
                });

        Observable.zip(storyObservable, storyExtraInfoObservable, new Func2<Story, StoryExtraInfo, Story>() {
            @Override
            public Story call(Story story, StoryExtraInfo storyExtraInfo) {
                story.setStoryExtraInfo(storyExtraInfo);

                return story;
            }
        }).subscribe(new Observer<Story>() {
            @Override
            public void onCompleted() {
                System.exit(0);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Error:" + e.getMessage());
            }

            @Override
            public void onNext(Story result) {
                System.out.print(result + " ");
            }
        });


        mStoryListAdapter.updateData(mStories);
    }


    private void processData(Stories stories){
        mStories = stories.getStories().subList(0,9);

        mTopStories = stories.getTopStories();

        mTopStoriesAdapter.updateData(mTopStories);

        mStoryListAdapter.updateData(mStories);
    }

    private void processError(Throwable e) {
        Log.e("Test", e.getLocalizedMessage(), e);
    }

}
