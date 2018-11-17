package com.example.pckosek.customviews_11;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MyNewView extends View {

    /* ------------------------*/
    /*    member variables     */

    private final static int MAX_POINTS = 50;

    private Paint mPaint;
    private int mPointIndx;

    private ArrayList<ArrayList<Point>> mPointListList;
    private int mStrandCount = 0;

    private boolean mBufferIsCirculating;


    /* ------------------------*/
    /*    constructor          */

    public MyNewView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupPointListList();
        initializePaint();

    }

    /* ------------------------*/
    /*    class methods        */

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN :
                makeNewCircularBuffer();
                addPointToCircularBuffer(
                        new Point(Math.round(event.getX()), Math.round(event.getY()))
                );
                break;
            case MotionEvent.ACTION_MOVE :
                addPointToCircularBuffer(
                        new Point(Math.round(event.getX()), Math.round(event.getY()))
                );
                break;
        }
        return true;
    }

    /* --------------------------------*/
    /*    protected draw operation     */

    @Override
    protected void onDraw(Canvas canvas) {

        for (ArrayList<Point> circlePoints : mPointListList) {
            // draw points
            for (Point p : circlePoints) {
                canvas.drawCircle(p.x, p.y, 20, mPaint);
            }
        }
    }

    /* ------------------------------------*/
    /*    custom initialization methods    */

    private void setupPointListList() {
        mPointListList = new ArrayList<ArrayList<Point>>();
    }

    private void initializePaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    /* ---------------------*/
    /*    custom methods    */

    private void makeNewCircularBuffer() {
        ArrayList<Point> circlePoints = new ArrayList<Point>();
        mPointListList.add(circlePoints);
        mBufferIsCirculating = false;
        mPointIndx = 0;
        mStrandCount++;
    }

    private void addPointToCircularBuffer(Point p){
        if (!mBufferIsCirculating) {
            mPointListList.get(mStrandCount-1).add(p);
        } else {
            mPointListList.get(mStrandCount-1).set(mPointIndx, p);
        }
        mPointIndx++;
        if (mPointIndx >= MAX_POINTS) {
            mBufferIsCirculating = true;
        }
        mPointIndx %= MAX_POINTS;
        postInvalidate();
    }

}