package com.fighter_lee.mydemo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fighter_lee on 2017/8/2.
 */

public class Test {

    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<>();
        map1.put("1","xxx");
        map1.put("1","jjj");
        map1.put("2","zzz");
        for (String s : map1.keySet()) {
            String value = map1.get(s);
            System.out.println(value);
        }
    }
}
