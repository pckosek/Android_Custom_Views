package com.example.pckosek.customviews_10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MyNewView extends View {

    /* ------------------------*/
    /*    member variables     */

    private Handler mHandler;
    private ScatterOps mScatterOps;

    private final static int MAX_POINTS = 50;

    private Paint mPaint;
    private List<Point> mCirclePoints;
    private int mPointIndx;

    private boolean mBufferIsCirculating;


    /* ------------------------*/
    /*    constructor          */

    public MyNewView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHandler = new Handler();

        setupCircularBuffer();
        initializePaint();

    }

    /* ------------------------*/
    /*    class methods        */

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN :
                mHandler.removeCallbacks(mScatterOps);
                clearCircularBuffer();
                addPointToCircularBuffer(
                        new Point(Math.round(event.getX()), Math.round(event.getY()))
                );
                break;
            case MotionEvent.ACTION_MOVE :
                addPointToCircularBuffer(
                        new Point(Math.round(event.getX()), Math.round(event.getY()))
                );
                break;
            case MotionEvent.ACTION_UP :
                scatterPoints();
                break;
        }
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

    /* ------------------------------------*/
    /*    custom initialization methods    */

    private void setupCircularBuffer() {
        mCirclePoints = new ArrayList<Point>();
        clearCircularBuffer();
    }

    private void initializePaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    /* ---------------------*/
    /*    custom methods    */

    private void clearCircularBuffer() {
        mCirclePoints.clear();
        mBufferIsCirculating = false;
        mPointIndx = 0;
    }

    private void addPointToCircularBuffer(Point p){
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
    }

    private void scatterPoints() {
        mScatterOps = new ScatterOps();
        mHandler.postDelayed(mScatterOps, 50);

    }

    public class ScatterOps implements Runnable {

        public ScatterOps(){
        }

        @Override
        public void run() {
            for (Point p : mCirclePoints) {
                int offsetX = ThreadLocalRandom.current().nextInt(-5, 5 + 1);
                int offsetY = ThreadLocalRandom.current().nextInt(-5, 5 + 1);
                p.offset(offsetX, offsetY);
            }
            postInvalidate();
            mHandler.postDelayed(this, 50);

        }
    }
}