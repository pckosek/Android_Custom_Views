package com.pckosek.taptimer;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class MyView extends View {

    private final static float MAX_RADIUS = 400f;


    private Paint mPaint;
    private int mBgColor = Color.parseColor("#D3D3D3");
    private float cx, cy, cRadius;
    RectF mRectF;
    private float mSweepAngle = 0;

    public boolean mOnLayoutSet = false;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize_paint();
    }

    public void initialize_paint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if(mOnLayoutSet==false) {
            float width = (float) this.getWidth();
            float height = (float) this.getHeight();

            cx = width / 2.f;
            cy = height / 2.f;

            cRadius = Math.min(width * 0.4f, MAX_RADIUS);
            mRectF = new RectF(
                    cx - cRadius,
                    cy - cRadius,
                    cx + cRadius,
                    cy + cRadius);

            Log.i("MY_VIEW", cx + "");
            Log.i("MY_VIEW", cy + "");
            Log.i("MY_VIEW", cRadius + "");
            mOnLayoutSet=true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ValueAnimator animation = ValueAnimator.ofFloat(360f, 0f);
            animation.setDuration(1000);
            animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
            {
                @Override
                public void onAnimationUpdate(ValueAnimator animation)
                {
                    mSweepAngle = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            animation.start();
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        canvas.drawArc (mRectF, -90, mSweepAngle, true, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(cx, cy, cRadius-10, mPaint);
    }
}
