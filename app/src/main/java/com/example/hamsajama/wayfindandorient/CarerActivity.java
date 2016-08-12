package com.example.hamsajama.wayfindandorient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hamsajama on 12/08/2016.
 */
public class CarerActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carer_screen);
    }
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}
