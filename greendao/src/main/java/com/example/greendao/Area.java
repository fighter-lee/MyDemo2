package com.example.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fighter_lee on 2017/6/15.
 */

@Entity
public class Area {

    private String country;

    private String local;

    @Generated(hash = 653259260)
    public Area(String country, String local) {
        this.country = country;
        this.local = local;
    }

    @Generated(hash = 179626505)
    public Area() {
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocal() {
        return this.local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
