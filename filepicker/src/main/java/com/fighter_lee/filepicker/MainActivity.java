package com.fighter_lee.filepicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fighter_lee.filepicker.data.FilePickerConst;
import com.fighter_lee.filepicker.engine.FilePickerBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> filePaths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        String[] zips = {".zip",".rar"};
        String[] bins = {".bin"};
        FilePickerBuilder.getInstance()
                .setTitle("选择升级包")
                .setMaxCount(3)
                .setSelectedFiles(filePaths)
                .addFileSupport("ZIP",zips)
                .addFileSupport("bin",bins)
                .enableDocSupport(false)
                .pickFile(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MainActivity", "resultCode:" + resultCode);
        ArrayList<String> list = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_FILES);
        for (String s : list) {
            Log.d("MainActivity", "选择了："+s);
        }
    }
}
