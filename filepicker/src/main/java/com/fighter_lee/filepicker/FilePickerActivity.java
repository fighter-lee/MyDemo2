package com.fighter_lee.filepicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.fighter_lee.filepicker.data.FilePickerConst;
import com.fighter_lee.filepicker.engine.PickerManager;
import com.fighter_lee.filepicker.fragment.DocFragment;
import com.fighter_lee.filepicker.fragment.DocPickerFragment;
import com.fighter_lee.filepicker.utils.FragmentUtil;

import java.util.ArrayList;

/**
 * Created by fighter_lee on 2017/7/28.
 */

public class FilePickerActivity extends AppCompatActivity implements
        DocFragment.DocFragmentListener,
        DocPickerFragment.DocPickerFragmentListener {
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(PickerManager.getInstance().getTheme());
        setContentView(R.layout.activity_file_picker);

        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            intent.getBundleExtra(FilePickerConst.EXTRA_PICKER_TYPE);
            if (getSupportActionBar() != null)
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            type = intent.getIntExtra(FilePickerConst.EXTRA_PICKER_TYPE, FilePickerConst.DOC_PICKER);
            ArrayList<String> selectedPaths = intent.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_FILES);

            if (selectedPaths != null && selectedPaths.size() > 0) {
                if (PickerManager.getInstance().getMaxCount() == 1) {
                    selectedPaths.clear();
                }
                PickerManager.getInstance().add(selectedPaths, FilePickerConst.FILE_TYPE_DOCUMENT);
            } else {
                selectedPaths = new ArrayList<>();
            }
            setToolbarTitle(PickerManager.getInstance().getCurrentCount(), PickerManager.getInstance().getTitle());
            openSpecificFragment(type, selectedPaths);

        }

    }

    private void setToolbarTitle(int count, String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (PickerManager.getInstance().getMaxCount() > 1)
                actionBar.setTitle(String.format("附件 (%d/%d)", count, PickerManager.getInstance().getMaxCount()));
            else {
                actionBar.setTitle(title);
            }
        }

    }

    private void openSpecificFragment(int type, @Nullable ArrayList<String> selectedPaths) {
        if (PickerManager.getInstance().isDocSupport())
            PickerManager.getInstance().addDocTypes();

        DocPickerFragment photoFragment = DocPickerFragment.newInstance(selectedPaths);
        FragmentUtil.addFragment(this, R.id.container, photoFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.picker_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_done) {
            returnData(PickerManager.getInstance().getSelectedFiles());

            return true;
        } else if (i == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void returnData(ArrayList<String> paths) {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(FilePickerConst.KEY_SELECTED_FILES, paths);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onItemSelected() {
        setToolbarTitle(PickerManager.getInstance().getCurrentCount(), PickerManager.getInstance().getTitle());

        if (PickerManager.getInstance().getMaxCount() == 1)
            returnData(PickerManager.getInstance().getSelectedFiles());
    }

}
