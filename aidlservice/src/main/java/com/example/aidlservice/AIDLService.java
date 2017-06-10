package com.example.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by fighter_lee on 2017/6/10.
 */

public class AIDLService extends Service {

    public final String TAG = this.getClass().getSimpleName();

    MeiNvManager.Stub meiNvManager = new MeiNvManager.Stub() {

        @Override
        public String getName() throws RemoteException {
            return meiNv.name;
        }

        @Override
        public String getHeight() throws RemoteException {
            return meiNv.height;
        }

        @Override
        public String getWeight() throws RemoteException {
            return meiNv.weight;
        }
    };
    private MeiNv meiNv;
    private Handler handler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return meiNvManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        startChange();
    }

    private void startChange() {
        meiNv = new MeiNv();
        meiNv.name = "柳岩";
        meiNv.height = "16";
        meiNv.weight = "10";
        handler.postDelayed(runnable, 2000);
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run () {
            double random = Math.random();
            meiNv.name = "柳岩";
            meiNv.height = "16" + (int)(random * 10);
            meiNv.weight = "10" + (int)(random * 10);
            handler.postDelayed(this,2000);
        }
    };
    //    new MeiNvManager.Stub(){}
    //    MeiNvManager.Stub meiNv = new MeiNvManager.Stub(){


}
