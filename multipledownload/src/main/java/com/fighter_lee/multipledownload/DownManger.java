package com.fighter_lee.multipledownload;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fighter on 2016/9/19.
 */
public class DownManger
{
    public static String downDir;


    private DownManger()
    {
        downDir = Environment.getExternalStorageDirectory().getPath() + File.separator
            + App.context.getPackageName() + File.separator + "download";
        // 判断当前的目录是否存在,如果不存在,那么创建
        File saveDir = new File(downDir);
        if (!saveDir.exists())
        {
            // 不存在
            saveDir.mkdirs();
        }
        
    }
    
    private final static DownManger sDownManager = new DownManger();
    
    public static DownManger getInstance()
    {
        return sDownManager;
    }
    
    // 分析1.状态
    // 分析2.方法
    
    public static final int state_none = 101;// 空闲状态
    
    public static final int state_downing = 102;// 下载中状态
    
    public static final int state_pause = 103;// 暂停状态
    
    public static final int state_error = 104;// 出错状态
    
    public static final int state_success = 105;// 成功状态
    
    public static final int state_wait = 106;// 等待状态

    public HashMap<Integer, DownInfo> downInfos = new HashMap<>();
    
    // 下载的方法
    public void down(DetailInfo detailInfo)
    {

        DownInfo downInfo = downInfos.get(detailInfo.getId());
        
        // 在这里一定要判空
        if (downInfo == null)
        {
            downInfo = DownInfo.initDownInfo(detailInfo);

            downInfos.put(downInfo.id, downInfo);
        }
        
        // 到这一步才算是downinfo有数据了
        
        // 在这里会触发状态更改
        downInfo.downState = state_wait;// 只要点击了下载的方法,状态肯定是下载
        
        // 更改状态
        upDateState(downInfo);
        
        DownRunnable downRunnable = new DownRunnable(downInfo);
        
        // 开启线程去下载
        ThreadPoolManager.getInstance().addThreadPool(downRunnable);
        
    }
    
    // 把状态发布出去
    private void upDateState(final DownInfo downInfo)
    {
       /* for (int i = 0; i < mDownListeners.size(); i++)
        {
            mDownListener.publishState(downInfo);
        }*/

        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                for (DownListener downListener : mDownListeners) {
                    downListener.publishState(downInfo);
                }
            }
        });


    }

    //返回downinfo数据
    public DownInfo getDownInfo(DetailInfo detailInfo) {
        return downInfos.get(detailInfo.getId());
    }

    // 1. 定义接口
    public interface DownListener
    {
        void publishState(DownInfo downInfo);
        
        // 进度
        void publishProgress(DownInfo downInfo);
    }
    
    // 3. 接收接口对象
    public DownListener mDownListener;
    
    // 2. 设置
    public void setDownListener(DownListener downListener)
    {
        this.mDownListener = downListener;
    }

    // 监听集合
    private List<DownListener> mDownListeners = new ArrayList<>();
    
    public void addDownListener(DownListener downListener)
    {
        if (!mDownListeners.contains(downListener))
        {
            // 表示没有
            mDownListeners.add(downListener);
        }
    }
    
    // 下载
    public class DownRunnable implements Runnable
    {
        private DownInfo mDownInfo;
        
        public DownRunnable(DownInfo downInfo)
        {
            this.mDownInfo = downInfo;
        }
        
        @Override
        public void run()
        {
            // 第一步必须更改状态
            mDownInfo.downState = state_downing;
            upDateState(mDownInfo);
            
            // 下载有两种方式
            // 得到当前文件大小
            String saveFileStr = mDownInfo.saveURL;
            File saveFile = new File(saveFileStr);
            
            // 下载的url地址
            String downURl = "";
            
            if (saveFile.exists())
            {
                if (saveFile.length() == mDownInfo.downSize)
                {
                    // downURl = "http://yyhl.youyuan.com/download?name="+mDownInfo.downURL+"&range=672121";
                    
                    // 格式化下载路径
                    downURl = String.format(Uris.BREAKDOWNURL, mDownInfo.downURL, mDownInfo.downSize);
                    
                    // 暂停
                    downApk(downURl, mDownInfo);
                }
                else
                {

                    saveFile.delete();
                    mDownInfo.downSize = 0;
                    
                    // 下载
                    downURl = Uris.NEWDOWNURL + mDownInfo.downURL;
                    downApk(downURl, mDownInfo);
                }
            }
            else
            {
                // 文件不存在,就是重新下载
                downURl = Uris.NEWDOWNURL + mDownInfo.downURL;
                downApk(downURl, mDownInfo);
            }
            
        }
    }
    
    // 下载文件
    private void downApk(String downURl, DownInfo downInfo)
    {
        System.out.println("当前的url是什么:"+downURl);
        HttpUtil.HttpResult httpResult = HttpUtil.download(downURl);
        // 创建文件
        File saveFile = new File(downInfo.saveURL);
        // 判断一把
        if (httpResult != null && httpResult.getInputStream() != null)
        {
            FileOutputStream mFileOutputStream = null;
            try
            {
                InputStream inputStream = httpResult.getInputStream();

                mFileOutputStream = new FileOutputStream(saveFile, true);

                byte[] buffer = new byte[1024 * 10];

                int len = -1;

                while ((len = inputStream.read(buffer)) != -1 && downInfo.downState == state_downing) {
                    //写入文件
                    mFileOutputStream.write(buffer, 0, len);
                    downInfo.downSize += len;
                    //更新进度
                    updateProgress(downInfo);
                }

                if (saveFile.length() == downInfo.size) {
                    //说明下载完成了
                    downInfo.downState = state_success;
                    upDateState(downInfo);
                } else {
                    //暂停
                    if (downInfo.downState == state_pause) {
                        //暂停
                        upDateState(downInfo);
                    }

                }


            }
            catch (Exception e)
            {
                e.printStackTrace();

                downInfo.downSize = 0;
                saveFile.delete();

                System.out.println("当前下载apk出错");
                // 状态更新
                downInfo.downState = state_error;
                upDateState(downInfo);

            }
            finally
            {
                if (httpResult != null) {
                    httpResult.close();
                }

                if (mFileOutputStream != null) {
                    try {
                        mFileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }
        else
        {
            // 说明已经失败了
            downInfo.downSize = 0;
            saveFile.delete();
            System.out.println("当前的流拿不到");
            // 状态更新
            downInfo.downState = state_error;
            upDateState(downInfo);
            
        }
        
    }

    //更新进度
    private void updateProgress(final DownInfo downInfo) {

        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
        for (DownListener downListener : mDownListeners) {
            downListener.publishProgress(downInfo);
        }

            }
        });
    }

    // 暂停
    public void pause(DetailInfo detailInfo)
    {

        //怎么暂停
        if (detailInfo != null) {
            DownInfo downInfo = downInfos.get(detailInfo.getId());
            if (downInfo != null) {
                downInfo.downState = state_pause;
            }
        }

    }
    
    // 安装
    public void installAPK(DetailInfo detailInfo)
    {
 /*       <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <data android:scheme="content" />
        <data android:scheme="file" />
        <data android:mimeType="application/vnd.android.package-archive" />
        </intent-filter>*/


        DownInfo downInfo = DownManger.getInstance().getDownInfo(detailInfo);
        if (downInfo == null) {
            return;
        }

        //得到文件
        String saveFile = downInfo.saveURL;

        File file = new File(saveFile);

        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.context.startActivity(intent);

        System.out.println("安装");
    }
    
}
