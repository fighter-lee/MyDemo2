package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aidlservice.IService;
import com.example.aidlservice.IServiceListener;
import com.example.aidlservice.Info;
import com.example.aidlservice.MeiNvManager;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MeiNvManager meiNvManager;
    private IService iService;

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            meiNvManager = MeiNvManager.Stub.asInterface(service);
            iService = IService.Stub.asInterface(service);
            Log.d(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            meiNvManager = null;
            iService = null;
        }
    };

    private TextView tv_name;
    private TextView tv_height;
    private TextView tv_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService();
        initView();
    }

    private void initView() {
        Button click = (Button) findViewById(R.id.click);
        Button test = (Button) findViewById(R.id.test);
        Button download = (Button) findViewById(R.id.download);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_height = (TextView) findViewById(R.id.tv_height);
        tv_weight = (TextView) findViewById(R.id.tv_weight);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String height = meiNvManager.getHeight();
                    String name = meiNvManager.getName();
                    String weight = meiNvManager.getWeight();
                    tv_name.setText(name);
                    tv_height.setText(height);
                    tv_weight.setText(weight);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iService.test();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Info info = new Info();
                    info.setUrl("www.google.com");
                    info.setFileSize(100000);
                    iService.start(info, new IServiceListener.Stub() {
                        @Override
                        public void download(int progress) throws RemoteException {
                            Log.d(TAG, "download: "+progress);
                        }

                        @Override
                        public void cancel() throws RemoteException {
                            Log.d(TAG, "cancel: ");
                        }
                    });

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindService() {

        Intent intent = new Intent();
        //绑定服务端的service
        intent.setAction("com.example.aidlservice.AIDLService");
        //新版本（5.0后）必须显式intent启动 绑定服务
        intent.setComponent(new ComponentName("com.example.aidlservice","com.example.aidlservice.AIDLService"));
        //绑定的时候服务端自动创建
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }
}
