# A Custom View that Counts How Long is Was Pressed

## A video of the operation of this file can be seen [here](https://youtu.be/ALXd6Tc1kzY)
## A lecture of the operation of this file can be seen [here](https://www.youtube.com/watch?v=JRNKU45Mu5c)

 - [Java Files Direct Link](./app/src/main/java/com/example/pckosek/customviews_03) <br>
 - [Layout Resources Direct Link](./app/src/main/res/layout)

## 

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

 The `startCounting` method is shown here:
```java
     public void startCounting() {
        mCount = 0;
        mHandler.postDelayed(this, 100);
    }
```
In this method, line 63 tells the Handler (`mHandler`) to run the method `run` implemented in `this` (i.e. the `View` itself) in 100 ms.

Since `this` refers to the custom `View`, the runnable method is therefore:
```java
    @Override
    public void run() {
        mCount++;
        mHandler.postDelayed(this, 100);
        Log.i("COUNT", String.valueOf(mCount));
    }
```

It is in the above `run()` method that the count is incremented, logged and the runnable is called again in another 100 ms. This process of retriggering a future event to run is repeated every 100 ms until `mHandler.removeCallbacks(this);` is called in `stopCounting()`.

Further discussion of Handlers and Runnables can be seen on [CodePath](https://guides.codepath.com/android/Repeating-Periodic-Tasks#handler)