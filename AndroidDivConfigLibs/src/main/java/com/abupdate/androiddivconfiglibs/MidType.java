package com.abupdate.androiddivconfiglibs;

/**
 * @author fighter_lee
 * @date 2018/3/2
 */
public enum MidType {

    SN(Constants.TYPE_SN), IMEI(Constants.TYPE_IMEI);
    private String type;

    MidType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static boolean valid(String type) {
        if (type.equals(SN.getType()) || type.equals(IMEI.getType())){
            return true;
        }
        return false;
    }

}
