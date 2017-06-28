package com.fighter_lee.mydemo.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fighter_lee.mydemo.App;
import com.fighter_lee.mydemo.R;

/**
 * Created by fighter_lee on 2017/6/17.
 */

public class TestActivity extends Activity {

    private MyReceiver myReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        myReceiver = new MyReceiver(App.sCx);
        Button register = (Button) findViewById(R.id.register);
        Button send = (Button) findViewById(R.id.send);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main();
                getException(new NullPointerException());
                getException(new ClassCastException());
                getException(new Exception());
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadCast();
            }
        });

        getException(new NullPointerException());
        getException(new ClassCastException());
//        getException(new Exception());
    }

    public void getException(Exception e) {
        if (e instanceof NullPointerException){
            Log.d("TAH",1+"");
        }
        if (e instanceof ClassCastException){
            Log.d("TAH",2+"");
        }

        if (e instanceof Exception){
            Log.d("TAH",3+"");
        }
    }

    public void main() {

        myReceiver.register(myReceiver);

    }

    public void sendBroadCast() {
        Intent callbackIntent = new Intent("MqttService.callbackToActivity.v0");
        callbackIntent.putExtra("MqttService.clientHandle", "xxxx");
        LocalBroadcastManager.getInstance(App.sCx).sendBroadcast(callbackIntent);
    }
}
