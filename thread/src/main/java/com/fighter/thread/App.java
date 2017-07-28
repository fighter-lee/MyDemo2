package com.fighter.thread;

import android.app.Application;

import com.adups.http_libs.HttpManager;

/**
 * Created by fighter_lee on 2017/7/20.
 */

public class App extends Application {

    public HttpManager httpManager;

    @Override
    public void onCreate() {
        super.onCreate();
        httpManager = HttpManager.build(this)
                .setRetryTimes(2)
                .setRedirectTimes(2)
                .setSocketTimeout(5 * 1000)
                .setSocketTimeout(5 * 1000)
                .create();
    }
}
