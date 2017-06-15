package com.example.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fighter_lee on 2017/6/15.
 */

@Entity
public class Country {

    @Index
    private String name;

    @NotNull
    private Long num;

    @NotNull
    private String song;

    @Generated(hash = 88507615)
    public Country(String name, @NotNull Long num, @NotNull String song) {
        this.name = name;
        this.num = num;
        this.song = song;
    }

    @Generated(hash = 668024697)
    public Country() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNum() {
        return this.num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getSong() {
        return this.song;
    }

    public void setSong(String song) {
        this.song = song;
    }
}
