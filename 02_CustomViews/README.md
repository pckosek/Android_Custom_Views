# A Custom View that Responds to Touch Events

## A video of the operation of this file can be seen [here](https://youtu.be/Ch35qT43Jiw)

 - [Java Code](./app/src/main/java/com/example/pckosek/customviews_02) <br>
 - [Layout Resources](./app/src/main/res/layout)

 This custom `View` implements the logic of responding to a touch event within the class itself. This is in contrast to an activity or fragment that contains the view implementing something like a `View.OnClickListener`.

 Here the touch event is intercepted by overriding the `onTouchEvent` method:
```java
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean result = true;

        switch (action) {
            case MotionEvent.ACTION_DOWN :
                this.setBackgroundColor( getResources().getColor(R.color.colorAccent) );
                break;
            case MotionEvent.ACTION_UP :
                this.setBackgroundColor( getResources().getColor(R.color.colorPrimary) );
                break;
        }
        return result;
    }
```

In the course of a single touch event, `onTouchEvent` may be called many - even hundreds - of times. It is triggered at the start of the press, any time there is movement and when the press is released. 

The `event` object contains a wealth of information regarding the press at this instance in time including the x and y position in the screen and whether or not this is the beginning (`MotionEvent.ACTION_DOWN`) or end (`MotionEvent.ACTION_UP`) of the press event.

The return value (`true` or `false`) describes whether or not the event is 'captured'. 
 - If the event is captured (`return false`) this event *is not* propogated upwards. This means that the super container view (possibly a layout or scrollview that might want to use the touch event for scrolling or swiping) *will not* be able to use the touch event.
 - If the event is *not captured* (`return true`) this event *is* propogated upwards. This means that the super container view (possibly a layout or scrollview that might want to use the touch event for scrolling or swiping) *will* be able to use the touch event.
 - More treatment of the return value can be seen [at CodePath](https://guides.codepath.com/android/gestures-and-touch-events#understanding-touch-events)