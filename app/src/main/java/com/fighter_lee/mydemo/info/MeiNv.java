package com.fighter_lee.mydemo.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/6/9 00 09.
 */

public class MeiNv implements Parcelable {

    private String name;

    private String height;

    private String weight;

    protected MeiNv(Parcel in) {
        name = in.readString();
        height = in.readString();
        weight = in.readString();
    }

    public static final Creator<MeiNv> CREATOR = new Creator<MeiNv>() {
        @Override
        public MeiNv createFromParcel(Parcel in) {
            return new MeiNv(in);
        }

        @Override
        public MeiNv[] newArray(int size) {
            return new MeiNv[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(height);
        dest.writeString(weight);
    }
}
