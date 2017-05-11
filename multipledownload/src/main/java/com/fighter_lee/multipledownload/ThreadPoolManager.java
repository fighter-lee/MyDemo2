package com.fighter_lee.multipledownload;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fighter on 2016/9/19.
 */
public class ThreadPoolManager
{

    private final ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager()
    {
        final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
        int core = CPU_COUNT + 1;//一直存在,不管有没有任务
        int maxSize = 10;//最大多少个线程

//        3--->10--->10  -->20
        //这里只能写runable线程的接口,不能写具体的实现类

        //开启线程池,第一步核心线程开启
        //第二步把队列加满
        //第三步如果队列放不下了,看最大的线程是否还能开启,能开启就把后面的线程进行开启
        //第四步,在从队列中取线程

        mThreadPoolExecutor = new ThreadPoolExecutor(core,maxSize,5,
                TimeUnit.HOURS,
                new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());


    }
    
    private static ThreadPoolManager sDownManager = new ThreadPoolManager();
    
    public static ThreadPoolManager getInstance()
    {
        return sDownManager;
    }
    
    // 创建一个线程池
    // 加入并运行线程池

    public void addThreadPool(Runnable runnable) {

        mThreadPoolExecutor.execute(runnable);
    }
    
}
