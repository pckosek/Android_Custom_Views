package com.example.pckosek.customviews_02;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class MyNewView extends View {

    /* ------------------------*/
    /*    constructor          */

    public MyNewView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /* ------------------------*/
    /*    class methods        */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean result = true;

        switch (action) {
            case MotionEvent.ACTION_DOWN :
                this.setBackgroundColor( getResources().getColor(R.color.colorAccent) );
                break;
            case MotionEvent.ACTION_UP :
                this.setBackgroundColor( getResources().getColor(R.color.colorPrimary) );
                break;
        }
        return result;
    }
}
