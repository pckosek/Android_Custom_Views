package com.pckosek.tapresponder;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TapView extends View {

    private Paint mPaint;
    private float mRadius, mX, mY;


    public TapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize_canvas();
    }

    private void initialize_canvas() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mX = event.getX();
            mY = event.getY();
            animate_circle();
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX, mY, mRadius, mPaint);
    }


    public void animate_circle() {
        ValueAnimator animation = ValueAnimator.ofFloat(100f, 0f);
        animation.setDuration(1000);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                mRadius = (float) updatedAnimation.getAnimatedValue();
                postInvalidate();
            }
        });
        animation.start();
    }
}

