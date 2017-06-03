package com.fighter_lee.propertychangesupport;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by Administrator on 2017/6/3 0003.
 */

public class DeviceInfo {

    private static DeviceInfo deviceInfo;

    public static DeviceInfo getInstance() {
        if (deviceInfo == null) {
            deviceInfo = new DeviceInfo();
        }
        return deviceInfo;
    }

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public String getDeviceName() {
        return mDeviceName;
    }

    public void setDeviceName(String deviceName) {
        String oldValue = mDeviceName;
        this.mDeviceName = deviceName;
        changeSupport.firePropertyChange("deviceName", oldValue, deviceName);
    }

    public String getDeviceStatus() {
        return mDeviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        String oldValue = mDeviceStatus;
        this.mDeviceStatus = deviceStatus;
        changeSupport.firePropertyChange("deviceStatus", oldValue, deviceStatus);
    }

    private String mDeviceName;
    private String mDeviceStatus;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
