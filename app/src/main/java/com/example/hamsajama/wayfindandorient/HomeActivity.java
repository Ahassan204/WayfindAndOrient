package com.example.hamsajama.wayfindandorient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hamsajama.wayfindandorient.motionUtil.MotionDection;

/**
 * Created by hamsajama on 12/08/2016.
 */
public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void startUserActivity(View view) {
        Intent navigate = new Intent(HomeActivity.this, UserActivity.class);
        startActivity(navigate);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
    public void startCarerActivity(View view) {
        Intent navigate = new Intent(HomeActivity.this, CarerActivity.class);
        startActivity(navigate);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
