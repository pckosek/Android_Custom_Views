package com.example.pckosek.customviews_07;

import android.content.Context;
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

    private Paint mPaint;

    private List<Point> mCirclePoints;



    /* ------------------------*/
    /*    constructor          */

    public MyNewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCirclePoints = new ArrayList<Point>();
        setupPaint();
    }

    /* ------------------------*/
    /*    class methods        */

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();
        mCirclePoints.add(new Point(Math.round(touchX), Math.round(touchY)));
        postInvalidate();

        return true;
    }

    /* --------------------------------*/
    /*    protected draw operation     */

    @Override
    protected void onDraw(Canvas canvas) {
        for (Point p : mCirclePoints) {
            canvas.drawCircle(p.x, p.y, 20, mPaint);
        }
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


    public void clearCircles() {
        mCirclePoints.clear();
        postInvalidate();
    }


}