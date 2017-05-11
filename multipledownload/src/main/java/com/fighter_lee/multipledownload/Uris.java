package com.fighter_lee.multipledownload;

/**
 * Created by fighter on 2016/9/14.
 */
public class Uris {
    public static final String HOSTURI = "http://yyhl.youyuan.com/";

    public static final String HOMEURI = HOSTURI +"home?index=";
    public static final String SUBJECTURI = HOSTURI +"subject?index=";

    public static final String IMAGEHOST = HOSTURI+"image?name=";

    public static final String DOWNAPKURL = HOSTURI+"download?name=";

    //占位符%s:代表String,%d代表数字
    public static final String BREAKDOWNURL = HOSTURI+"download?name=%s&range=%d";

    public static final String NEWDOWNURL = HOSTURI+"download?name=";
}
