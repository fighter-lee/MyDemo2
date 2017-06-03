package com.fighter_lee.propertychangesupport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainActivity extends AppCompatActivity implements PropertyChangeListener{

    private static final String TAG = "MainActivity";
    private EditText deviceName;
    private EditText deviceStatus;
    private Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deviceName = (EditText) findViewById(R.id.et_deviceName);
        deviceStatus = (EditText) findViewById(R.id.et_deviceStatus);
        change = (Button) findViewById(R.id.bt_change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String device_name = deviceName.getText().toString().trim();
                String device_status = deviceStatus.getText().toString().trim();
                DeviceInfo.getInstance().setDeviceName(device_name);
                DeviceInfo.getInstance().setDeviceStatus(device_status);
            }
        });

        DeviceInfo.getInstance().addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        Log.d(TAG,"Source:"+propertyChangeEvent.getSource());
        Log.d(TAG,"PropertyName:"+propertyChangeEvent.getPropertyName());
        Log.d(TAG,"OldValue:"+propertyChangeEvent.getOldValue());
        Log.d(TAG,"NewValue:"+propertyChangeEvent.getNewValue().toString());
        Log.d(TAG,"---------------------------------------------------");
    }
}
