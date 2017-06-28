package com.fighter_lee.mydemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by fighter_lee on 2017/6/17.
 */

public class App extends Application {

    public static Context sCx;

    @Override
    public void onCreate() {
        super.onCreate();
        sCx = getApplicationContext();
    }
}
