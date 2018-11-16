package com.example.pckosek.customviews_05;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class MyNewView extends View implements Runnable{

    /* ------------------------*/
    /*    member variables     */

    private Handler mHandler;

    private int mCount;
    private Paint mPaint;


    /* ------------------------*/
    /*    constructor          */

    public MyNewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHandler = new Handler();
        mCount = 0;
        setupPaint();
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

    /* --------------------------------*/
    /*    protected draw operation     */

    @Override
    protected void onDraw(Canvas canvas) {

        String displayText = String.valueOf(mCount);
        float textWidth = mPaint.measureText(displayText);

        int xPos = (int) ((canvas.getWidth() / 2) - ( textWidth / 2 ));
        int yPos = (int) ((canvas.getHeight() / 2) - ((mPaint.descent() + mPaint.ascent()) / 2)) ;

        canvas.drawText(displayText, xPos, yPos, mPaint);
    }

    /* --------------------------------*/
    /*    custom operations methods    */

    private void setupPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(80);
        mPaint.setStyle(Paint.Style.FILL);
    }

    private void incrementAndUpdate() {
        mCount++;
        postInvalidate();
    }

    /* ------------------------*/
    /*    interface methods    */

    @Override
    public void run() {
        incrementAndUpdate();
        mHandler.postDelayed(this, 50);
    }

    /* --------------------------------*/
    /*    custom operations methods    */
    public void startCounting() {
        mCount = 0;
        mHandler.postDelayed(this, 50);
    }

    public void stopCounting() {
        mHandler.removeCallbacks(this);
    }}
