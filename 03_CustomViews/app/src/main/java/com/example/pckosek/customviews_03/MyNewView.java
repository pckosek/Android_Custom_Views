package com.example.pckosek.customviews_03;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class MyNewView extends View  implements Runnable {

    /* ------------------------*/
    /*    member variables     */

    int mCount;
    Handler mHandler;

    /* ------------------------*/
    /*    constructor          */

    public MyNewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCount = 0;
        mHandler = new Handler();
    }

    /* ------------------------*/
    /*    class methods        */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean result = true;

        switch (action) {
            case MotionEvent.ACTION_DOWN :
                startCounting();
                this.setBackgroundColor( getResources().getColor(R.color.colorAccent) );
                break;
            case MotionEvent.ACTION_UP :
                stopCounting();
                this.setBackgroundColor( getResources().getColor(R.color.colorPrimary) );
                break;
        }
        return result;
    }

    /* ------------------------*/
    /*    interface methods    */

    @Override
    public void run() {
        mCount++;
        mHandler.postDelayed(this, 100);
        Log.i("COUNT", String.valueOf(mCount));
    }

    /* --------------------------------*/
    /*    custom operations methods    */
    public void startCounting() {
        mCount = 0;
        mHandler.postDelayed(this, 100);
    }

    public void stopCounting() {
        mHandler.removeCallbacks(this);
    }
}
