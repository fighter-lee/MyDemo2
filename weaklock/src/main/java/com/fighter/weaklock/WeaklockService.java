package com.fighter.weaklock;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;

/**
 * Created by fighter_lee on 2017/9/14.
 */

public class WeaklockService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        acquireWeakLock();
        return START_STICKY;
    }

    private void acquireWeakLock() {
//        WakeLock 的两种模式
//
//        不计数锁模式
//                计数锁模式
//        通过 setReferenceCounted(boolean value) 来指定. true 计数, false 不计数. 默认为计数机制.
//
//                如果是不计数模式, 不论之前 acquire() 了多少次, 调用一次 release() 就会释放所有锁.
//                如果是计数模式, 每次调用 acquire() 都会计数 count++, release() 的 count 的值必须相同.
//                参考

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);

        // 创建唤醒锁
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakelockTag");
        wakeLock.setReferenceCounted(false);
        // 获得唤醒锁
        wakeLock.acquire();
//        wakeLock.acquire(1000);

        wakeLock.release();

    }
}
