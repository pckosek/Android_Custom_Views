package com.example.pckosek.customviews_07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private MyNewView mMyNewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyNewView = findViewById(R.id.mnv_canvas);

        mButton = findViewById(R.id.b_clear);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        mMyNewView.clearCircles();

    }
}
