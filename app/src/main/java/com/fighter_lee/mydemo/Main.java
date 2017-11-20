package com.fighter_lee.mydemo;

import java.util.ResourceBundle;

/**
 * Created by fighter_lee on 2017/9/28.
 */

public class Main {

    public static void main(String[] args) {
        String fileName="com/fighter_lee/mydemo/config";
        //        config.properties它跟普通java类的命名规则完全一样：
        //        - 文件名和包名是区分大小写
        //        - 扩展名 .properties 省略
        //        - 资源文件必须位于classpath下，如果在某个包下成在定义fileName时要加上包的名称，且包名区分大小写
        //        如果是Web项目，不写包路径可以，此时将资源文件放在WEB-INF\classes\目录下就可以。

        ResourceBundle paramBundle = ResourceBundle.getBundle(fileName);
        System.out.println(paramBundle.getString("DRIVER"));
        System.out.println(paramBundle.getString("URL"));
        System.out.println(paramBundle.getString("user"));
        System.out.println(paramBundle.getString("PASSWORD"));//这儿是区分大小写的

        System.out.println(paramBundle.getString("path"));
    }

}
