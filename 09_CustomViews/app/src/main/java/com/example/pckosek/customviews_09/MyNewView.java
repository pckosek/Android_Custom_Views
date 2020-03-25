package com.example.pckosek.customviews_09;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyNewView extends View implements Response.Listener<String> {

    /* ------------------------*/
    /*    member variables     */

    private RestaurantOrder mMyRestaurantOrder;
    private Paint mPaint;


    /* ------------------------*/
    /*    constructor          */

    public MyNewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPaint();

        mMyRestaurantOrder = new RestaurantOrder(0, 0, "not set");
    }

    /* ------------------------*/
    /*    class methods        */


    /* --------------------------------*/
    /*    protected draw operation     */

    @Override
    protected void onDraw(Canvas canvas) {

        String displayText = String.valueOf(mMyRestaurantOrder.getOrderCostString());
        float textWidth = mPaint.measureText(displayText);

        int xPos = (int) ((canvas.getWidth() / 2) - ( textWidth / 2 ));
        int yPos = (int) ((canvas.getHeight() / 2) - ((mPaint.descent() + mPaint.ascent()) / 2)) ;

        canvas.drawText(displayText, xPos, yPos, mPaint);
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


    @Override
    public void onResponse(String response) {
        Gson gson = new GsonBuilder().create();     // instantiate a gson builder
        mMyRestaurantOrder = gson.fromJson(response, RestaurantOrder.class);

        postInvalidate();

    }
}
