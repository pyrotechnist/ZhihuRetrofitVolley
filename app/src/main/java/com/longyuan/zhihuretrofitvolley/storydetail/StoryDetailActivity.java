package com.longyuan.zhihuretrofitvolley.storydetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.longyuan.zhihuretrofitvolley.R;
import com.longyuan.zhihuretrofitvolley.comment.CommentActivity;
import com.longyuan.zhihuretrofitvolley.injection.DaggerNetworkComponent;
import com.longyuan.zhihuretrofitvolley.injection.NetworkModule;
import com.longyuan.zhihuretrofitvolley.pojo.StoryDetail;
import com.longyuan.zhihuretrofitvolley.pojo.StoryExtraInfo;
import com.longyuan.zhihuretrofitvolley.retrofit.api.StoryService;
import com.longyuan.zhihuretrofitvolley.utils.GsonRequest;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StoryDetailActivity extends AppCompatActivity {

    public static final String EXTRA_STORY_ID = "STORY_ID";

    public static final String SHORT_COMMENTS_NUMBER = "SHORT_COMMENTS_NUMBER";

    public static final String LONG_COMMENTS_NUMBER = "LONG_COMMENTS_NUMBER";

    public static final String USE_VOLLEY = "USE_VOLLEY";

    @Inject
    StoryService mStoryService;

    @BindView(R.id.image_storydetail)
    ImageView mImageView;

    @BindView(R.id.webview_storydetail)
    WebView mWebView;

    @BindView(R.id.text_storydetail)
    TextView mTextView;

    //@BindView(R.id.action_comments)
    MenuItem mMenuItemComments;

    //@BindView(R.id.action_fav)
    MenuItem mMenuItemFav;

    //@BindView(R.id.action_like)
    MenuItem mMenuItemLike;

    //@BindView(R.id.action_share)
    MenuItem mMenuItemShare;

    @BindView(R.id.storydetail_comments_counter)
    TextView mTextViewComments;

    @BindView(R.id.storydetail_popularity)
    TextView mTextViewCommentsPopularity;

    @BindView(R.id.storydetail_short_comments_counter)
    TextView mTextViewShortComments;

    @BindView(R.id.storydetail_long_comments_counter)
    TextView mTextViewLongComments;


    @Inject
    RequestQueue mRequestQueue;


    private String storyId;

    private String mShortCommentsNumber;

    private String mLongCommentsNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storydetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ButterKnife.bind(this);

        storyId = getIntent().getStringExtra(EXTRA_STORY_ID);

        boolean useVolley = getIntent().getBooleanExtra(USE_VOLLEY,false);


        DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule("https://news-at.zhihu.com/api/4/",getApplicationContext()))
                .build().inject(this);


        if(useVolley){

            getDataWithVolley(storyId);

        }else
        {
            getDataWithRetrofit(storyId);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_storydetail, menu);

        mMenuItemComments = menu.findItem(R.id.action_comments);

        mMenuItemLike = menu.findItem(R.id.action_like);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_comments) {

            Intent intent = new Intent(getApplicationContext(),CommentActivity.class);
            intent.putExtra(EXTRA_STORY_ID, storyId);
            intent.putExtra(SHORT_COMMENTS_NUMBER, mShortCommentsNumber);
            intent.putExtra(LONG_COMMENTS_NUMBER, mLongCommentsNumber);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void getDataWithVolley(String storyId){

        String url = "https://news-at.zhihu.com/api/4/news/"+storyId;
        // Volley use customized Request to parse json
        GsonRequest<StoryDetail> gsonRequest = new GsonRequest<>(url, StoryDetail.class, null, new Response.Listener<StoryDetail>() {
            @Override
            public void onResponse(StoryDetail response) {

                Glide.with(getApplicationContext()).load(response.getImage()).into(mImageView);

                mTextView.setText(response.getTitle());

                mWebView.loadData(response.getBody(),"text/html",null);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(gsonRequest);

    }

    private void getDataWithRetrofit(String storyId){

        mStoryService.getStoryDetail(storyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> processData(data),
                        throwable -> processError(throwable));


        mStoryService.getStoryExtraInfo(storyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> processExtraInfo(data),
                        throwable -> processError(throwable));
    }

    private void processData(StoryDetail storyDetail){

        Glide.with(getApplicationContext()).load(storyDetail.getImage()).into(mImageView);

        mTextView.setText(storyDetail.getTitle());

        mWebView.loadData(storyDetail.getBody(),"text/html",null);
    }

    private void processExtraInfo(StoryExtraInfo storyExtraInfo){

        mTextViewComments.setText(storyExtraInfo.getComments());

        mTextViewCommentsPopularity.setText(storyExtraInfo.getPopularity());

        mMenuItemComments.setTitle(storyExtraInfo.getComments());

        mMenuItemLike.setTitle(storyExtraInfo.getPopularity());

        mShortCommentsNumber = storyExtraInfo.getShortComments();

        mLongCommentsNumber = storyExtraInfo.getLongComments();

        mTextViewShortComments.setText(mShortCommentsNumber);

        mTextViewLongComments.setText(mLongCommentsNumber);

    }

    private void processError(Throwable e) {

        Log.e("Test", e.getLocalizedMessage(), e);
    }

}
