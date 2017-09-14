package com.example.aidlservice;

/**
 * Created by fighter_lee on 2017/9/11.
 */

interface IMeiNvListener {

    void onProgress(int progress);

    void onCancel();

    void onError(int error);

    void onSuccess();
}
