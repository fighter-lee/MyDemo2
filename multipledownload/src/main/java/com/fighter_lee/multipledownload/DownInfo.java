package com.fighter_lee.multipledownload;

import java.io.File;

/**
 * Created by fighter on 2016/9/19.
 */
public class DownInfo {
    //下载状态
    public  int downState;

    //下载地址
    public String downURL;
    //保存地址
    public String saveURL;

    //进度怎么计算,大小/总长
    //大小
    public long size;

    //下载大小
    public long downSize;

    //id
    public int id;

    //默认初始化
    public static DownInfo initDownInfo(DetailInfo detailInfo) {

        DownInfo downInfo = new DownInfo();
        //初始化了
        downInfo.downState = DownManger.state_none;//空闲;
//        downInfo.downURL = Uris.DOWNAPKURL+detailInfo.getDownloadUrl();
        downInfo.downURL = detailInfo.getDownloadUrl();
        //保存的地址
        downInfo.saveURL = DownManger.downDir+ File.separator+detailInfo.getName()+".apk";

        //大小
        downInfo.size = detailInfo.getSize();

        //当前下载的进度
        downInfo.downSize = 0;

        //下载的id
        downInfo.id = detailInfo.getId();


        return downInfo;
    }



}
