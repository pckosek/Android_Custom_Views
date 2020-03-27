# A Custom View that Counts How Long is Was Pressed

## A video of the operation of this file can be seen [here](https://youtu.be/ALXd6Tc1kzY)

Similar to [02_CustomViews](../02_CustomViews) this custom `View` responds to touch events itself by overriding the `onTouchEvent` method:
https://github.com/pckosek/Android_Custom_Views/blob/d0b3ed4e000835ee30a8159ff8a9df567e7d43e2/03_CustomViews/app/src/main/java/com/example/pckosek/customviews_03/MyNewView.java#L31-L47


As in [02_CustomViews](../02_CustomViews), the color (retreived from resources) is changed to the accent color when the event starts
```
39                 this.setBackgroundColor( getResources().getColor(R.color.colorAccent) );
```
 and changed back to 

 - [Java Code](./app/src/main/java/com/example/pckosek/customviews_03) <br>
 - [Layout Resources](./app/src/main/res/layout)