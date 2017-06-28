package com.fighter.classloaderexample;


/**
 * Created by fighter_lee on 2017/6/28.
 */

public class ClassloaderTest {
    private static final String TAG = "ClassloaderTest";
    public static void main(String[] args) {
        System.out.println(ClassloaderTest.class.getClassLoader().getClass().getName());
        System.out.println(System.class.getClassLoader());
//        Log.d(TAG,"classload类加载器名称："+ClassloaderTest.class.getClassLoader().getClass().getName());
//        Log.d(TAG,"system类加载器名称："+System.class.getClassLoader().getClass().getName());

        ClassLoader classLoader = ClassloaderTest.class.getClassLoader();
        while (classLoader != null){
            System.out.println(classLoader.getClass().getName()+"-->");
//            Log.d(TAG,classLoader.getClass().getName()+"-->");
            classLoader = classLoader.getParent();
        }
        System.out.println(classLoader);
//        Log.d(TAG,classLoader+"");
    }

}
