package com.fighter_lee.filepicker.utils;

import android.support.v4.app.FragmentActivity;

import com.fighter_lee.filepicker.data.Document;
import com.fighter_lee.filepicker.engine.DocScannerTask;
import com.fighter_lee.filepicker.inter.FileResultCallback;

public class MediaStoreHelper {

  public static void getDocs(FragmentActivity activity, FileResultCallback<Document> fileResultCallback)
  {
    new DocScannerTask(activity,fileResultCallback).execute();
  }
}