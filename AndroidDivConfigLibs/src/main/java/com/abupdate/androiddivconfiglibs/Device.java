package com.abupdate.androiddivconfiglibs;

/**
 * @author fighter_lee
 * @date 2018/3/2
 */
public enum Device {

    CAR(Constants.DEVICETYPE_CAR), IOT(Constants.DEVICETYPE_IOT);
    private String type;

    Device(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static boolean valid(String type) {
        if (type.equals(CAR.getType()) || type.equals(IOT.getType())){
            return true;
        }
        return false;
    }

}
