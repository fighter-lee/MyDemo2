package com.fighter_lee.mydemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fighter_lee on 2017/8/2.
 */

public class Test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Map<String, List<String>> map1 = new HashMap<>();
        list.add("1");
        map1.put("1",list);
        list.add("2");
        map1.put("1",list);

        List<String> list2 = new ArrayList<>();
        list2.add("3");
        map1.put("2",list2);
        map1.get("1").add("jjjjj");
        map1.get("1").add("zzzzz");
        map1.get("2").add("yyy");

        boolean p = map1.get("1").remove(null);
        System.out.println(p);
        for (String s : map1.keySet()) {
            List<String> value = map1.get(s);
            for (String s1 : value) {
                System.out.println(s1);
            }
            //            System.out.println(value);
        }
//        Set<Map.Entry<String, String>> entries = map1.entrySet();
//        for (Map.Entry<String, String> entry : entries) {
//            System.out.println(entry.getKey()+"___"+entry.getValue());
//        }

    }
}
