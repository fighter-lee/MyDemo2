package com.fighter.injectdemo.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by fighter_lee on 2017/7/18.
 */

//编译时动态处理
@Retention(RetentionPolicy.CLASS)//表示在什么级别保存注解信息
@Target({ ElementType.FIELD, ElementType.TYPE })//表示注解可以用在什么地方
public @interface InjectView
{
    int value();
}