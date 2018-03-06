package com.fighter.google_vr;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

public class MainActivity extends AppCompatActivity {

    private VrPanoramaView vrPanoramaView;
    private VrPanoramaView.Options paNormalOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vrPanoramaView = (VrPanoramaView) findViewById(R.id.mVrPanoramaView);
        paNormalOptions = new VrPanoramaView.Options();
        paNormalOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
        //        mVrPanoramaView.setFullscreenButtonEnabled (false); //隐藏全屏模式按钮
        vrPanoramaView.setInfoButtonEnabled(false); //设置隐藏最左边信息的按钮
        vrPanoramaView.setStereoModeButtonEnabled(false); //设置隐藏立体模型的按钮
        vrPanoramaView.setEventListener(new ActivityEventListener()); //设置监听
        vrPanoramaView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.andes), paNormalOptions);
    }

    private class ActivityEventListener extends VrPanoramaEventListener {
        @Override
        public void onLoadSuccess() {//图片加载成功
        }


        @Override
        public void onLoadError(String errorMessage) {//图片加载失败
        }

        @Override
        public void onClick() {//当我们点击了VrPanoramaView 时候触发            super.onClick();
            new Handler()
        }

        @Override
        public void onDisplayModeChanged(int newDisplayMode) {
            //改变显示模式时候出发（全屏模式和纸板模式）
            super.onDisplayModeChanged(newDisplayMode);
        }
    }
}
