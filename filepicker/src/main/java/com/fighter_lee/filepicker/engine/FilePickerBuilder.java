package com.fighter_lee.filepicker.engine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;

import com.fighter_lee.filepicker.FilePickerActivity;
import com.fighter_lee.filepicker.data.FilePickerConst;
import com.fighter_lee.filepicker.data.FileType;

import java.util.ArrayList;


public class FilePickerBuilder {

    private final Bundle mPickerOptionsBundle;

    public FilePickerBuilder() {
        mPickerOptionsBundle = new Bundle();
    }

    public static FilePickerBuilder getInstance() {
        return new FilePickerBuilder();
    }

    public FilePickerBuilder setMaxCount(int maxCount) {
        PickerManager.getInstance().setMaxCount(maxCount);
        return this;
    }

    public FilePickerBuilder setTitle(String title) {
        PickerManager.getInstance().setTitle(title);
        return this;
    }

    public FilePickerBuilder setActivityTheme(int theme) {
        PickerManager.getInstance().setTheme(theme);
        return this;
    }

    /**
     * 设置已选路径
     * @param selectedFiles
     * @return
     */
    public FilePickerBuilder setSelectedFiles(ArrayList<String> selectedFiles) {
        mPickerOptionsBundle.putStringArrayList(FilePickerConst.KEY_SELECTED_FILES, selectedFiles);
        return this;
    }

    public FilePickerBuilder showFolderView(boolean status) {
        PickerManager.getInstance().setShowFolderView(status);
        return this;
    }

    public FilePickerBuilder enableDocSupport(boolean status) {
        PickerManager.getInstance().setDocSupport(status);
        return this;
    }

    public FilePickerBuilder addFileSupport(String title, String[] extensions, @DrawableRes int drawable) {
        PickerManager.getInstance().addFileType(new FileType(title, extensions, drawable));
        return this;
    }

    public FilePickerBuilder addFileSupport(String title, String[] extensions) {
        PickerManager.getInstance().addFileType(new FileType(title, extensions, 0));
        return this;
    }

    public void pickFile(Activity context) {
        mPickerOptionsBundle.putInt(FilePickerConst.EXTRA_PICKER_TYPE, FilePickerConst.DOC_PICKER);
        start(context, FilePickerConst.DOC_PICKER);
    }

    public void pickFile(Fragment context) {
        mPickerOptionsBundle.putInt(FilePickerConst.EXTRA_PICKER_TYPE, FilePickerConst.DOC_PICKER);
        start(context, FilePickerConst.DOC_PICKER);
    }

    private void start(Activity context, int pickerType) {
        Intent intent = new Intent(context, FilePickerActivity.class);
        intent.putExtras(mPickerOptionsBundle);

        context.startActivityForResult(intent, FilePickerConst.REQUEST_CODE_DOC);
    }

    private void start(Fragment fragment, int pickerType) {

        Intent intent = new Intent(fragment.getActivity(), FilePickerActivity.class);
        intent.putExtra("xxxx","jjj");
        intent.putExtras(mPickerOptionsBundle);
        fragment.startActivityForResult(intent, FilePickerConst.REQUEST_CODE_DOC);
    }

}
