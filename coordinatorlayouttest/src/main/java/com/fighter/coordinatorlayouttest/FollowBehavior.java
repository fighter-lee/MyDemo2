package com.fighter.coordinatorlayouttest;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by fighter_lee on 2017/8/10.
 */

public class FollowBehavior extends CoordinatorLayout.Behavior<TextView> {

    public FollowBehavior(Context context, AttributeSet attributes){
        super(context,attributes);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof Button;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        child.setX(dependency.getX());
        child.setY(dependency.getY() + 100);
        return true;
    }
}
