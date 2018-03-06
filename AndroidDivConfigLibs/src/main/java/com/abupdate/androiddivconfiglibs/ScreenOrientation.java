package com.abupdate.androiddivconfiglibs;

/**
 * @author fighter_lee
 * @date 2018/3/2
 */
public enum ScreenOrientation {
    SCREEN_PORTRAIT(Constants.TYPE_SCREEN_PORTRAIT), SCREEN_LANDSCAPE(Constants.TYPE_SCREEN_LANDSCAPE);
    private String type;

    ScreenOrientation(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static boolean valid(String type) {
        if (type.equals(SCREEN_LANDSCAPE.getType()) || type.equals(SCREEN_PORTRAIT.getType())){
            return true;
        }
        return false;
    }
}