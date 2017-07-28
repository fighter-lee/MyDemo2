package com.fighter.injectdemo.runtime;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by fighter_lee on 2017/7/18.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String name();//用来设置名字
}
