package com.example.multitouchapp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    private int mActivePointerId;   // this will hold

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mActivePointerId = event.getPointerId(0);

        int pointerIndex = event.findPointerIndex(mActivePointerId);

        float x = event.getX(pointerIndex);
        float y = event.getY(pointerIndex);

        Log.i("ON_TOUCH_EVENT", x+"");
        return true;
    }
}
