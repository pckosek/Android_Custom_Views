package com.example.pckosek.customviews_08;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MyNewView extends View {

    /* ------------------------*/
    /*    member variables     */

    private final static int MAX_POINTS = 50;

    private Paint mPaint;
    private List<Point> mCirclePoints;
    private int mPointIndx;

    private boolean mBufferIsCirculating;


    /* ------------------------*/
    /*    constructor          */

    public MyNewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupCircularBuffer();
        setupPaint();
//        setupBackground();

    }

    /* ------------------------*/
    /*    class methods        */

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();
        Point p = new Point(Math.round(touchX), Math.round(touchY));

        if (!mBufferIsCirculating) {
            mCirclePoints.add(p);
        } else {
            mCirclePoints.set(mPointIndx, p);
        }
        mPointIndx++;
        if (mPointIndx >= MAX_POINTS) {
            mBufferIsCirculating = true;
        }
        mPointIndx %= MAX_POINTS;
        postInvalidate();

        return true;
    }

    /* --------------------------------*/
    /*    protected draw operation     */

    @Override
    protected void onDraw(Canvas canvas) {

        // draw points
        for (Point p : mCirclePoints) {
            canvas.drawCircle(p.x, p.y, 20, mPaint);
        }
    }

    /* --------------------------------*/
    /*    custom operations methods    */

    private void setupCircularBuffer() {
        mCirclePoints = new ArrayList<Point>();
        mBufferIsCirculating = false;
        mPointIndx = 0;
    }

    private void setupPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }


    public void clearCircles() {
        mCirclePoints.clear();
        postInvalidate();
    }


}