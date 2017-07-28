package com.fighter.injectdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fighter.injectdemo.runtime.Bean;
import com.fighter.injectdemo.runtime.Column;
import com.fighter.injectdemo.runtime.NameAndType;
import com.fighter.injectdemo.runtime.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fighter_lee on 2017/7/18.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String tableName = getTableName(Bean.class);
        List<NameAndType> columns = getColumns(Bean.class);
        Log.d("MainActivity", tableName);
        for (NameAndType column : columns) {
            Log.d("MainActivity", column.name + "," + column.type);
        }
    }

    private static String getTableName(Class<?> bean) {
        String name = null;
        ////判断当前元素是否被Table注解|
        if (bean.isAnnotationPresent(Table.class)) {
            //获取注解对象
            Table table = bean.getAnnotation(Table.class);
            name =table.name();
        }
        return name;
    }

    private static List<NameAndType> getColumns(Class<?> bean) {
        List<NameAndType> columns = new ArrayList<NameAndType>();
        Field[] fields = bean.getDeclaredFields();
        if (fields != null) {
            //分析Bean中的变量是否需要生成sql字段
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.isAnnotationPresent(Column.class)) {//判断元素是否被Column注解
                    //生成sql字段的名
                    Column column = field.getAnnotation(Column.class);
                    String name=column.name();
                    //生成sql字段的类型
                    String type = null;
                    if (int.class.isAssignableFrom(field.getType())) {
                        type = "integer";
                    } else if (String.class.isAssignableFrom(field.getType())) {
                        type = "text";
                    } else {
                        throw new RuntimeException("unspported type=" + field.getType().getSimpleName());
                    }
                    columns.add(new NameAndType(type, name));
                }
            }
        }
        return columns;
    }
}
