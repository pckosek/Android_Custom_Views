package com.example.pckosek.customview_12;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyNewView extends View {

    /* ------------------------*/
    /*    member variables     */
    private int mCanvasWidth;
    private int mCanvasHeight;
    private Rect mCanvasBounds;

    private Paint mPaint;

    private final static int SCALE_FACTOR = 8;

    private Bitmap mBison;
    private int mBisonWidth;
    private int mBisonHeight;

    private ArrayList<Bison> mBisonList;


    /* ------------------------*/
    /*    constructor          */

    public MyNewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
        setupBison();
    }

    /* ------------------------*/
    /*    class methods        */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean result = true;

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                addBison((int)event.getX(), (int)event.getY());
                break;
            case MotionEvent.ACTION_UP:
                (mBisonList.get(mBisonList.size()-1)).startAnimation();
                break;
        }
        return result;
    }

    /* --------------------------------*/
    /*    lifecycle method             */

    @Override
    protected void onSizeChanged(int w, int h, int prevW, int prevH) {
        super.onSizeChanged(w, h, prevW, prevH);

        // get the width and height
        mCanvasWidth = w;
        mCanvasHeight = h;

        mCanvasBounds = new Rect(0, 0, mCanvasWidth, mCanvasHeight);
    }

    /* --------------------------------*/
    /*    protected draw operation     */

    @Override
    protected void onDraw(Canvas canvas) {

        if (!mBisonList.isEmpty()) {
            for (Bison thisBison : mBisonList) {
                canvas.drawBitmap(mBison, null, thisBison.getmPostionRect(), mPaint);
            }
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

    private void setupBison() {

        mBison       = BitmapFactory.decodeResource(getResources(), R.drawable.bison_01);
        mBisonWidth  = mBison.getWidth()/SCALE_FACTOR;
        mBisonHeight = mBison.getHeight()/SCALE_FACTOR;

        mBisonList = new ArrayList<Bison>();
    }

    private void addBison(int x, int y) {
        mBisonList.add(new Bison(x, y));
        postInvalidate();
    }

    /* --------------------------------*/
    /*    custom class                 */

    public class Bison implements Runnable{

        private static final int VELOCITY_X = 0;
        private static final int VELOCITY_Y = 3;

        private Handler mBisonAnimationHandler = new Handler();

        private Rect mPostionRect =  new Rect(0,0,mBisonWidth, mBisonHeight);

        public Bison(int xPos, int yPos) {
            mPostionRect.offset(xPos - mBisonWidth/2, yPos - mBisonHeight/2);
        }

        public Rect getmPostionRect() {
            return mPostionRect;
        }

        /* --------------------------------*/
        /*    custom operations methods    */

        public void startAnimation() {
            mBisonAnimationHandler.postDelayed(this, 50);
        }

        public void stopAnimation() {
            mBisonAnimationHandler.removeCallbacks(this);
        }

        /* --------------------------------*/
        /*    interface methods            */

        @Override
        public void run() {
            mPostionRect.offset(VELOCITY_X, VELOCITY_Y);
            postInvalidate();

            if (mCanvasBounds.contains(mPostionRect)) {
                this.startAnimation();
            } else {
                this.stopAnimation();
            }
        }
    }

}