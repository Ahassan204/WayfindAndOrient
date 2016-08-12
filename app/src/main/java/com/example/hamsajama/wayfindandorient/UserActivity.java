package com.example.hamsajama.wayfindandorient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hamsajama.wayfindandorient.motionUtil.MotionDection;

public class UserActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.navigateButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent navigate = new Intent(UserActivity.this, RoutesActivity.class);
                startActivity(navigate);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
        new MotionDection(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
