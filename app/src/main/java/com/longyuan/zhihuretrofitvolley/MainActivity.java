package com.longyuan.zhihuretrofitvolley;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.longyuan.zhihuretrofitvolley.injection.DaggerNetworkComponent;
import com.longyuan.zhihuretrofitvolley.injection.NetworkModule;
import com.longyuan.zhihuretrofitvolley.utils.NewsListAdapter;

import com.longyuan.zhihuretrofitvolley.utils.NewsListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.news_list)
    RecyclerView mNewList;

    @BindView(R.id.volley)
    Button mButtonVBolley;

    @Inject
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mButtonVBolley.setText("Hello From butterknife haha");

        DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(getApplicationContext()))
                .build().inject(this);

        //mNewList = (RecyclerView) findViewById(R.id.news_list);





        List<String> newsList= new ArrayList();

        newsList.add("first NEWS");

        newsList.add("second NEWS");

        NewsListAdapter newsListAdapter = new NewsListAdapter(newsList);

        mNewList.setAdapter(newsListAdapter);

        mNewList.setLayoutManager(new LinearLayoutManager(mNewList.getContext()));
    }
}
