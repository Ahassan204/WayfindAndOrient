package com.example.hamsajama.wayfindandorient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Tests

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startNav(View view) {
        Intent navigate = new Intent(MainActivity.this, NavigateActivity.class);
        startActivity(navigate);
    }
}
