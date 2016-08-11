package com.example.hamsajama.wayfindandorient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hamsajama.wayfindandorient.motionUtil.MotionDection;

public class MainActivity extends AppCompatActivity  {

    //Tests

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MotionDection(this);
    }

    public void startNav(View view) {
        Intent navigate = new Intent(MainActivity.this, NavigateActivity.class);
        startActivity(navigate);
    }
}
