# A Simple Custom View

## A video of the operation of this file can be seen [here](https://youtu.be/PxzMkgcQnEI)<br>A lecture of the operation of this file can be seen [here](https://www.youtube.com/watch?v=CHt5FvXrtCc)

 - [Java Files Direct Link](./app/src/main/java/com/example/pckosek/customviews_01) <br>
 - [Layout Resources Direct Link](./app/src/main/res/layout)
##

This view doesn't really do much in the Java code. It get's its size and color in activity_main.xml

You can see how to place a custom view in a layout xml file by calling it's full package path:
```xml
    <com.example.pckosek.customviews_01.MyNewView
        android:layout_width="150dp"
        android:layout_height="150dp"
        android:layout_marginBottom="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:background="@color/colorPrimary"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```