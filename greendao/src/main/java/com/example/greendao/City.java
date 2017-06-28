package com.example.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fighter_lee on 2017/6/15.
 */

@Entity
public class City {

    private String name;

    @NotNull
    private Long population;

    @Generated(hash = 290613083)
    public City(String name, @NotNull Long population) {
        this.name = name;
        this.population = population;
    }

    @Generated(hash = 750791287)
    public City() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPopulation() {
        return this.population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
    
}
