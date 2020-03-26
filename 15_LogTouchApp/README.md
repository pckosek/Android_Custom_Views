# Logging when a Custom View is Pressed

## A video of the operation of this file can be seen [here](https://www.youtube.com/watch?v=QFzYikO4D0k)

In this example, the `x` position is logged when the user clicks on the screen. Notice that this is a subclassed view and is embedded in a fragment. 

Also note that the `Log.i` is triggered twice, once on event down and once on event up.