package com.abupdate.androiddivconfiglibs;

/**
 * @author fighter_lee
 * @date 2018/3/2
 */
public class ConfigBean {

    private String productID;

    private String productSecret;

    private Device device;

    private MidType midType;

    private boolean requestPush;

    private boolean localUpdate;

    private ScreenOrientation screenOrientation;

    private String tempFilePath;

    private String svnPath;

    private boolean needIcon;

    private String oem;

    private String models;

    private String platform;

    private String deviceType;

    public String getOem() {
        return oem;
    }

    public ConfigBean setOem(String oem) {
        this.oem = oem;
        return this;
    }

    public String getModels() {
        return models;
    }

    public ConfigBean setModels(String models) {
        this.models = models;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public ConfigBean setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public ConfigBean setDeviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public String getProductID() {
        return productID;
    }

    public ConfigBean setProductID(String productID) {
        this.productID = productID;
        return this;
    }

    public String getProductSecret() {
        return productSecret;
    }

    public ConfigBean setProductSecret(String productSecret) {
        this.productSecret = productSecret;
        return this;
    }

    public boolean isNeedIcon() {
        return needIcon;
    }

    public ConfigBean setNeedIcon(boolean needIcon) {
        this.needIcon = needIcon;
        return this;
    }


    public String getTempFilePath() {
        return tempFilePath;
    }

    public ConfigBean setTempFilePath(String filePath) {
        this.tempFilePath = filePath;
        return this;
    }

    public String getSvnPath() {
        return svnPath;
    }

    public ConfigBean setSvnPath(String svnPath) {
        this.svnPath = svnPath;
        return this;
    }

    public Device getDevice() {
        return device;
    }

    public ConfigBean setDevice(Device device) {
        this.device = device;
        return this;
    }

    public MidType getMidType() {
        return midType;
    }

    public ConfigBean setMidType(MidType midType) {
        this.midType = midType;
        return this;
    }

    public boolean isRequestPush() {
        return requestPush;
    }

    public ConfigBean setRequestPush(boolean requestPush) {
        this.requestPush = requestPush;
        return this;
    }

    public boolean isLocalUpdate() {
        return localUpdate;
    }

    public ConfigBean setLocalUpdate(boolean localUpdate) {
        this.localUpdate = localUpdate;
        return this;
    }

    public ScreenOrientation getScreenOrientation() {
        return screenOrientation;
    }

    public ConfigBean setScreenOrientation(ScreenOrientation screenOrientation) {
        this.screenOrientation = screenOrientation;
        return this;
    }
}
