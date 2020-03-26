package com.example.pckosek.soundcallback_01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class MySoundView extends View {

    /* ------------------------*/
    /*    member variables     */
    private int mCanvasWidth;
    private int mCanvasHeight;

    private Paint mPaint;


    /* ------------------------*/
    /*    constructor          */

    public MySoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
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

}