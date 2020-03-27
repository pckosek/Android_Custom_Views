# A Custom View that Counts How Long is Was Pressed

## A video of the operation of this file can be seen [here](https://youtu.be/ALXd6Tc1kzY)

Similar to [02_CustomViews](../02_CustomViews) this custom `View` responds to touch events itself by overriding the `onTouchEvent` method:
```java
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean result = true;

        switch (action) {
            case MotionEvent.ACTION_DOWN :
                startCounting();
                this.setBackgroundColor( getResources().getColor(R.color.colorAccent) );
                break;
            case MotionEvent.ACTION_UP :
                stopCounting();
                this.setBackgroundColor( getResources().getColor(R.color.colorPrimary) );
                break;
        }
        return result;
    }
```

As in [02_CustomViews](../02_CustomViews), the color (retreived from resources) is changed to the accent color (pink) when the event starts (line 39)
```java
                this.setBackgroundColor( getResources().getColor(R.color.colorPrimary) );
```
 and changed back to blue on line 43.

 The `startCounting` method shown here:
```java
     public void startCounting() {
        mCount = 0;
        mHandler.postDelayed(this, 100);
    }
```




 - [Java Code](./app/src/main/java/com/example/pckosek/customviews_03) <br>
 - [Layout Resources](./app/src/main/res/layout)