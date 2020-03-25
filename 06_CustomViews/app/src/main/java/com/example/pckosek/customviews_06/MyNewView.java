package com.example.pckosek.customviews_06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class MyNewView extends View {

    /* ------------------------*/
    /*    member variables     */

    private Paint mPaint;

    private float mCircleX;
    private float mCircleY;
    private float mRadius;



    /* ------------------------*/
    /*    constructor          */

    public MyNewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCircleX = (float)150.;
        mCircleY = (float)150.;
        mRadius = (float)20.;
        setupPaint();
    }

    /* ------------------------*/
    /*    class methods        */

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                float pointX = event.getX();
                float pointY = event.getY();

                setCircleCoordinatesAndDraw(pointX, pointY);
                break;
        }
        return true;
    }

    /* --------------------------------*/
    /*    protected draw operation     */

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawCircle(mCircleX, mCircleY, mRadius, mPaint);;
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

    private void setCircleCoordinatesAndDraw(float pointX, float pointY) {
        mCircleX = pointX;
        mCircleY = pointY;

        postInvalidate();
    }

}