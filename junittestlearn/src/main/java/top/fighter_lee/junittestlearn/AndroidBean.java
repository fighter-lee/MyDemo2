package top.fighter_lee.junittestlearn;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * @author fighter_lee
 * @date 2018/1/26
 */
public class AndroidBean implements Parcelable {

    private String name;

    public AndroidBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    private String age;

    protected AndroidBean(Parcel in) {
        name = in.readString();
        age = in.readString();
    }

    public static final Creator<AndroidBean> CREATOR = new Creator<AndroidBean>() {
        @Override
        public AndroidBean createFromParcel(Parcel in) {
            return new AndroidBean(in);
        }

        @Override
        public AndroidBean[] newArray(int size) {
            return new AndroidBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(age);
    }

    @Override
    public boolean equals(Object obj) {
        return TextUtils.equals(name,((AndroidBean)obj).getName());
    }
}
