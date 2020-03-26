package com.pckosek.multipletouchevents;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyView extends View {

    private Paint mPaint;
    private ArrayList<MyAnimation> mAnimations;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize_paint();

        mAnimations = new ArrayList<>();
    }

    public void initialize_paint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            MyAnimation ma = new MyAnimation(
                    event.getX(),
                    event.getY(),
                    50 );
            mAnimations.add(ma);
            Log.i("MOTION_EVENT", mAnimations.size()+"");
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i=0; i<mAnimations.size(); i++) {
            MyAnimation ma = mAnimations.get(i);
            canvas.drawCircle(ma.cx, ma.cy, ma.cRadius, mPaint);
        }

    }

    protected class MyAnimation {

        public float cx, cy, cRadius;

        public MyAnimation(float x, float y, float radius) {
            cx = x;
            cy = y;
            cRadius = radius;
            init();
        }

        private void init() {
            ValueAnimator animation = ValueAnimator.ofFloat((float)cRadius, 0f);
            animation.setDuration(1500);
            animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
            {
                @Override
                public void onAnimationUpdate(ValueAnimator animation)
                {
                    cRadius = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            animation.addListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    mAnimations.remove(MyAnimation.this);
                }
            });
            animation.start();
        }
    }
}
