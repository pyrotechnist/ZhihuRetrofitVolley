package com.longyuan.zhihuretrofitvolley.injection;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by loxu on 19/10/2017.
 */
@Module
public class NetworkModule {

    private Context mContext;

    public NetworkModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    public RequestQueue provideRequestQueue() {
        return Volley.newRequestQueue(mContext);
    }
}
