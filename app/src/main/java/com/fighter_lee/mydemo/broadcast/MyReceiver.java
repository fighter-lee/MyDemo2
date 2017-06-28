package com.fighter_lee.mydemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by fighter_lee on 2017/6/17.
 */

public class MyReceiver extends BroadcastReceiver {

    private static Context mCx;

    public MyReceiver(Context context) {
        mCx = context;
    }

    public void register(BroadcastReceiver receiver) {
        Log.d("Receive","注册了");
        IntentFilter filter = new IntentFilter();
        filter.addAction("MqttService.callbackToActivity.v0");
        LocalBroadcastManager.getInstance(mCx).registerReceiver(receiver, filter);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action){
            case "MqttService.callbackToActivity.v0":
                Log.d("Service","收到广播啦");
                Log.d("Service",MyReceiver.this+"");
                break;
        }
    }
}
