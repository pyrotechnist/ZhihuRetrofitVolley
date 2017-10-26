package com.longyuan.zhihuretrofitvolley.comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.longyuan.zhihuretrofitvolley.R;
import com.longyuan.zhihuretrofitvolley.injection.DaggerNetworkComponent;
import com.longyuan.zhihuretrofitvolley.injection.NetworkModule;
import com.longyuan.zhihuretrofitvolley.pojo.CommentItem;
import com.longyuan.zhihuretrofitvolley.pojo.LongComments;
import com.longyuan.zhihuretrofitvolley.pojo.Story;
import com.longyuan.zhihuretrofitvolley.pojo.StoryBase;
import com.longyuan.zhihuretrofitvolley.retrofit.api.StoryService;
import com.longyuan.zhihuretrofitvolley.storydetail.StoryDetailActivity;
import com.longyuan.zhihuretrofitvolley.utils.CommentsListAdapter;
import com.longyuan.zhihuretrofitvolley.utils.NewsListAdapter;
import com.longyuan.zhihuretrofitvolley.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.longyuan.zhihuretrofitvolley.storydetail.StoryDetailActivity.EXTRA_STORY_ID;
import static com.longyuan.zhihuretrofitvolley.storydetail.StoryDetailActivity.LONG_COMMENTS_NUMBER;
import static com.longyuan.zhihuretrofitvolley.storydetail.StoryDetailActivity.SHORT_COMMENTS_NUMBER;
import static com.longyuan.zhihuretrofitvolley.storydetail.StoryDetailActivity.USE_VOLLEY;

public class CommentActivity extends AppCompatActivity {


    @Inject
    StoryService mStoryService;

    @BindView(R.id.list_longComments)
    RecyclerView mRecyclerViewLongComments;

    @BindView(R.id.list_shortComments)
    RecyclerView mRecyclerViewShortComments;

    @BindView(R.id.title_longComments)
    TextView mTextViewLongComments;

    @BindView(R.id.title_shortComments)
    TextView mTextViewShortComments;

    private String mShortCommentsNumber;

    private String mLongCommentsNumber;

    private CommentsListAdapter mCommentsListAdapter;

    private CommentsListAdapter mShortCommentsListAdapterShort;

    private String storyId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule("https://news-at.zhihu.com/api/4/",getApplicationContext()))
                .build().inject(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        storyId = getIntent().getStringExtra(EXTRA_STORY_ID);

        mShortCommentsNumber = getIntent().getStringExtra(SHORT_COMMENTS_NUMBER);

        mLongCommentsNumber = getIntent().getStringExtra(LONG_COMMENTS_NUMBER);

        setRecyclerView();

        getLongComments(storyId);
    }

    private void setRecyclerView(){

        mTextViewShortComments.setText(mShortCommentsNumber + " short Comments");

        mTextViewLongComments.setText(mLongCommentsNumber + " long Comments");

        List<CommentItem> longCommentsList= new ArrayList();

        List<CommentItem> shortCommentsList= new ArrayList();

        mCommentsListAdapter = new CommentsListAdapter(this,longCommentsList);

        mShortCommentsListAdapterShort = new CommentsListAdapter(this,shortCommentsList);

        mRecyclerViewLongComments.setAdapter(mCommentsListAdapter);

        mRecyclerViewShortComments.setAdapter(mShortCommentsListAdapterShort);

        mRecyclerViewLongComments.setLayoutManager(new LinearLayoutManager(mRecyclerViewLongComments.getContext()));

        mRecyclerViewShortComments.setLayoutManager(new LinearLayoutManager(mRecyclerViewShortComments.getContext()));

        //mRecyclerViewLongComments.setNestedScrollingEnabled(false);

        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(mRecyclerViewLongComments.getContext(),
                DividerItemDecoration.VERTICAL);

        mRecyclerViewLongComments.addItemDecoration(horizontalDecoration);

        mCommentsListAdapter.setOnItemClickListener(new OnItemClickListener.OnCommentItemClickListener() {
            @Override
            public void onItemClick(CommentItem item) {
                Toast.makeText(getApplicationContext(),item.getId(),Toast.LENGTH_LONG).show();
               /* Intent intent = new Intent(getApplicationContext(),StoryDetailActivity.class);
                intent.putExtra(EXTRA_STORY_ID, item.getId());

                intent.putExtra(USE_VOLLEY, false);
                startActivity(intent);*/

            }

            @Override
            public void onItemLongClick(CommentItem item, int position) {

            }
        });

        //mNewList.addItemDecoration(horizontalDecoration);
    }


    @OnClick(R.id.title_shortComments)
    public void getShortComments(){

        mStoryService.getShortComments(storyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> processShortComments(data.getComments()),
                        throwable -> processError(throwable));
    }


    private void  getLongComments(String storyId)
    {

        mStoryService.getLongComments(storyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> processComments(data.getComments()),
                        throwable -> processError(throwable));
    }

    private void  getShortComments(String storyId)
    {

        mStoryService.getShortComments(storyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> processComments(data.getComments()),
                        throwable -> processError(throwable));
    }

    private void processComments( List<CommentItem> itemList){



        //mTextViewShortComments.setText(storyExtraInfo.getShortComments());

        mTextViewLongComments.setText(itemList.size() + " long comments");


        mCommentsListAdapter.updateData(itemList);

    }


    private void processShortComments( List<CommentItem> itemList){

        //mTextViewShortComments.setText(storyExtraInfo.getShortComments());

        mTextViewShortComments.setText(itemList.size() + " short comments");


        mShortCommentsListAdapterShort.updateData(itemList);

    }

    private void processError(Throwable e) {

        Log.e("Test", e.getLocalizedMessage(), e);
    }

}
