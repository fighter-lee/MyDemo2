package com.fighter_lee.mydemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Button start = (Button) findViewById(R.id.start);
        final Button cancel = (Button) findViewById(R.id.cancel);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleSend();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    public static class alarmreceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if (intent.getAction().equals("repeating")) {
                Toast.makeText(context, "repeating alarm", Toast.LENGTH_SHORT).show();
                Log.d("xxx","收到了");
            } else {
                Toast.makeText(context, "alarm", Toast.LENGTH_LONG).show();
                Log.d("xxx","收到了");
            }
        }
    }

    public void circleSend() {
        Intent intent =new Intent(this, alarmreceiver.class);
        intent.setAction("repeating");
        PendingIntent sender=PendingIntent.getBroadcast(this, 0, intent, 0);
        //开始时间
        long firstime= SystemClock.elapsedRealtime();
        AlarmManager am=(AlarmManager)getSystemService(ALARM_SERVICE);//5秒一个周期，不停的发送广播am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime, 5*1000, sender);
        //5秒一个周期，不停的发送广播
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime, 60*1000*60, sender);
    }

    public void delaySend() {
        Intent intent = new Intent(this, alarmreceiver.class);
        intent.setAction("short");
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);//设定一个五秒后的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 10);
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    }

    public void cancel() {
        Intent intent =new Intent(this, alarmreceiver.class);
        intent.setAction("repeating");
        PendingIntent sender=PendingIntent
                .getBroadcast(this, 0, intent, 0);
        AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarm.cancel(sender);
    }

}
