package com.fighter.definitionview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by fighter_lee on 2017/9/20.
 */

public class MyGroupView extends ViewGroup {
    private static final String TAG = "MyGroupView";

    public MyGroupView(Context context) {
        super(context);
    }

    public MyGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width_mode = MeasureSpec.getMode(widthMeasureSpec);
        int heigh_mode = MeasureSpec.getMode(heightMeasureSpec);
        Log.d(TAG, "onMeasure: " + width_mode + "," + heigh_mode);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
