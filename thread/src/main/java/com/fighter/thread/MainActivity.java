package com.fighter.thread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.adups.http_libs.AbstractRequest;
import com.adups.http_libs.Data;
import com.adups.http_libs.HttpException;
import com.adups.http_libs.HttpListener;
import com.adups.http_libs.HttpMethods;
import com.adups.http_libs.RequestConfig;
import com.adups.http_libs.Response;
import com.adups.http_libs.StringRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //        new ThreadPoolExecutor(5,10,10*1000, TimeUnit.SECONDS,)
        setContentView(R.layout.activity_main);
    }

    public void click_test(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                TimeUtils.get().startTime(new TimeoutCallback() {
                    @Override
                    public void onTimeout() {
                        Log.d(TAG,"到时间了");
                    }
                });
            }
        }).start();
    }

    /**
     * 固定个数
     *
     * @param view
     */
    public void click_FixedThreadPool(View view) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(30);
        for (int i = 1; i <= 100; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.v("zxy", "线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 单个线程
     *
     * @param view
     */
    public void click_SingleThread(View view) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 100; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.v("zxy", "线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 自动根据实现情况进行线程的重用
     *
     * @param view
     */
    public void click_CachedThread(View view) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 100; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.v("zxy", "线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        long time = index * 500;
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 延时周期任务
     *
     * @param view
     */
    public void click_ScheduledThread(View view) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        //延迟2秒后执行该任务
//        scheduledThreadPool.schedule(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 2, TimeUnit.SECONDS);
        //首次启动延迟5秒后，每隔2秒执行一次该任务
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                Log.v("zxy", "线程：" + threadName + ",正在执行任务");
            }
        }, 5, 2, TimeUnit.SECONDS);

    }

    public void click_net_test(View view) {
        App app = (App) getApplication();
        AbstractRequest<String> stringAbstractRequest = new StringRequest("https://iotapi.adups.com/register/1500003696")
                .setMethod(HttpMethods.Post)
                .setContent("{\"models\":\"mi3\",\"platform\":\"MSM8x10\",\"oem\":\"adups\",\"mid\":\"e0aee11a\",\"deviceType\":\"mifi\",\"version\":\"V1\",\"sdkversion\":\"1.0.9\"}".getBytes())
                .setHeaderContentType(RequestConfig.ContentType.TYPE_JSON)
                .setHttpListener(new HttpListener() {
                    @Override
                    public void onStart(AbstractRequest request) {
                        Log.d(TAG, "onStart: ");
                    }

                    @Override
                    public void onSuccess(Data data, Response response) {
                        Log.d(TAG, "onSuccess: "+response.resToString());
                    }

                    @Override
                    public void onFailure(HttpException e, Response response) {
                        Log.d(TAG, "onFailure: "+response.resToString());
                    }

                    @Override
                    public void onRetry(AbstractRequest request, int max, int times) {
                        Log.d(TAG, "onRetry: ");
                    }

                    @Override
                    public void onRedirect(AbstractRequest request, int max, int times) {
                        Log.d(TAG, "onRedirect: ");
                    }

                    @Override
                    public void onEnd(Response response) {
                        Log.d(TAG, "onEnd: ");
                    }
                });
        app.httpManager.executeAsync(stringAbstractRequest);
    }
}
