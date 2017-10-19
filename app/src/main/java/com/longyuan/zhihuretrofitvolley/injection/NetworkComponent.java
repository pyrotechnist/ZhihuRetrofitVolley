package com.longyuan.zhihuretrofitvolley.injection;

import com.longyuan.zhihuretrofitvolley.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by loxu on 19/10/2017.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {

    void inject(MainActivity mainActivity);

}
