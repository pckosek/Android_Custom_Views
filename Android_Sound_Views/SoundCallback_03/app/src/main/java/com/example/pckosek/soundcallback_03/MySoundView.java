package com.example.pckosek.soundcallback_03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


public class MySoundView extends View {

    /* ------------------------*/
    /*    member variables     */
    private int mCanvasWidth;
    private int mCanvasHeight;

    private Paint mPaint;

    private SoundViewListener mCallback;

    private ArrayList<MagicDot> mMagicDots = new ArrayList<>();

    /* ------------------------*/
    /*    constructor          */

    public MySoundView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupPaint();
    }

    /* ------------------------*/
    /*    class methods        */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mMagicDots.add(new MagicDot(event.getX(), event.getY()));
                mCallback.startPlaying();
                break;
            case MotionEvent.ACTION_MOVE:
                mMagicDots.add(new MagicDot(event.getX(), event.getY()));
                break;
            case MotionEvent.ACTION_UP:
                mCallback.stopPlaying();
                break;
        }
        return true;
    }

    /* --------------------------------*/
    /*    lifecycle method             */

    @Override
    protected void onSizeChanged(int w, int h, int prevW, int prevH) {
        super.onSizeChanged(w, h, prevW, prevH);

        // get the width and height
        this.mCanvasWidth = w;
        this.mCanvasHeight = h;
    }

    /* --------------------------------*/
    /*    protected draw operation     */

    @Override
    protected void onDraw(Canvas canvas) {

        if (!mMagicDots.isEmpty()) {
            for (MagicDot m : mMagicDots) {
                canvas.drawCircle(m.x, m.y, m.radius, mPaint);
            }
        }
    }

    /* --------------------------------*/
    /*    custom operations methods    */

    private void setupPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }


    /* --------------------------------*/
    /*    custom interface             */

    //   --> interface
    public interface SoundViewListener {
        void startPlaying();
        void stopPlaying();
    }

    //   --> interface assignment
    public void setSoundViewListener(SoundViewListener s) {
        mCallback = s;
    }


    /* --------------------------------*/
    /*    custom class                 */

    class MagicDot implements Runnable {

        private Handler mHandler = new Handler();

        private int mStepNumber = 0;

        public float y;
        public float x;
        public float radius = 3;

        public MagicDot(float px, float py) {
            x = px;
            y = py;
            mHandler.postDelayed(this, 25);
        }

        @Override
        public void run() {
            radius += .75;
            postInvalidate();

            mStepNumber++;
            if (mStepNumber < 50) {
                mHandler.postDelayed(this, 25);
            } else {
                mMagicDots.remove(this);
            }
        }
    }
}