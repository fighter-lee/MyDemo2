package com.fighter.injectdemo.runtime;

/**
 * Created by fighter_lee on 2017/7/18.
 */

@Table(name = "table_lyb")
public class Bean {
    
    @Column(name = "lyb")
    public String name;

    @Column(name = "description")
    public String column;

}
