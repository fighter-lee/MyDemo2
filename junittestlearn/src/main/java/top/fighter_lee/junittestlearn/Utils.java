package top.fighter_lee.junittestlearn;

/**
 * @author fighter_lee
 * @date 2018/1/25
 */
public class Utils {

    public static String test1() {
        return "is test1";
    }

    public static String test2(String s1,String s2) {
        return s1+s2;
    }

    public static String test3(Bean bean) {
        return bean.getName()+bean.getAge();
    }

    public static String test4(AndroidBean bean) {
        return bean.getName();
    }

}
