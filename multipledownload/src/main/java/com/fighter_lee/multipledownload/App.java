package com.fighter_lee.multipledownload;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by fighter on 2016/5/12 00:12.
 */

public class App extends Application {
    //定义一个上下文
    public static Context context;

    //定义一个主handler
    public static Handler mainHandler;
    @Override
    public void onCreate() {

        super.onCreate();

//        ImageLoader.getInstance().init( ImageLoaderConfiguration.createDefault(this));
        //赋值
        context = this;
        mainHandler = new Handler();
    }
}
