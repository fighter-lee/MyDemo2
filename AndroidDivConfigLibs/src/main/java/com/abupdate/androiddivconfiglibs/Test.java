package com.abupdate.androiddivconfiglibs;

public class Test {

    public static void main(String[] args) {
        ConfigBean configBean =
                new ConfigBean()
                        .setDevice(Device.CAR)
                        .setLocalUpdate(false)
                        .setMidType(MidType.SN)
                        .setRequestPush(false)
                        .setNeedIcon(true)
                        .setScreenOrientation(ScreenOrientation.SCREEN_PORTRAIT)
                        .setProductID("00000000000")
                        .setProductSecret("11111111111111111111111111111111")
                        .setOem("adups")
                        .setModels("mi3")
                        .setPlatform("MSM8x10")
                        .setDeviceType("mifi")
                        .setTempFilePath("D:/document/test/temp")
                        .setSvnPath("D:/document/test/svnPath");
        try {
            new DivConfig().start(configBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
