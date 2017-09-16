package com.example.aidlservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fighter_lee on 2017/9/16.
 */

public class Info implements Parcelable {

    public static final Creator<Info> CREATOR = new Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel in) {
            return new Info(in);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    private String url;
    private long fileSize;

    public Info() {

    }

    protected Info(Parcel in) {
        url = in.readString();
        fileSize = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeLong(fileSize);
    }
}