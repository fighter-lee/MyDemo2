package com.example.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by fighter_lee on 2017/6/15.
 */

@Entity
public class DeviceInfo {

    @NotNull
    private String userName;

    @NotNull
    private String userId;

    @Generated(hash = 400593951)
    public DeviceInfo(@NotNull String userName, @NotNull String userId) {
        this.userName = userName;
        this.userId = userId;
    }

    @Generated(hash = 2125166935)
    public DeviceInfo() {
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
