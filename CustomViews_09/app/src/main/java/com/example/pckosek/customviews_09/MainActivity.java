package com.example.pckosek.customviews_09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mButton;
    private MyNewView mMyNewView;
    private MyResponseHelper mMyResponseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyNewView = findViewById(R.id.mnv_canvas);

        mButton = findViewById(R.id.b_clear);
        mButton.setOnClickListener(this);

        mMyResponseHelper = new MyResponseHelper();
    }

    @Override
    public void onClick(View v) {

        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        String url ="https://pk.sites.tjhsst.edu/";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, mMyNewView, mMyResponseHelper);
        mRequestQueue.add(stringRequest);
    }


    /* ------------------------------------------*/
    /*    HELPER CLASSES                         */

    class MyResponseHelper implements  Response.ErrorListener {

        public MyResponseHelper () {
        }


        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i("KOSEK", "error");
        }

    }
}
