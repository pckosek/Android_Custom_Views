# A One Second Custom Count-Down Animation

## A video of the operation of this file can be seen [here](https://www.youtube.com/watch?v=XHw28PQpZXY)

When the user taps the screen, a custom `ValueAnimator` is triggered that modulates the size of the radius of an arc. This arc (drawn by `canvas.drawArc (mRectF, -90, mSweepAngle, true, mPaint);` actually draws an arc that looks like a pie slice. It only appears like a thn ring because a blue circle is drawn over the arc in the center.