package com.example.hamsajama.wayfindandorient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hamsajama.wayfindandorient.R;

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
        Intent userActivity = new Intent(HomeActivity.this, UserActivity.class);
        startActivity(userActivity);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
    public void startCarerActivity(View view) {
        Intent carerActivity = new Intent(HomeActivity.this, CarerActivity.class);
        startActivity(carerActivity);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
