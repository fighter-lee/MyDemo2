package com.fighter.classloaderexample;

import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String key = applicationInfo.metaData.getString("key");
            String value = applicationInfo.metaData.getString("value");
            Log.d("Main",key+"_"+value);

            ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            String main = activityInfo.metaData.getString("main");
            Log.d("main",main);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
