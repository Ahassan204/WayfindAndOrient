package com.example.hamsajama.wayfindandorient.activities;

import com.example.hamsajama.wayfindandorient.R;
import com.example.hamsajama.wayfindandorient.adapter.CustomAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by hamsajama on 11/08/2016.
 */
public class RoutesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routelist);
        String[] foods = {"Home", "Park", "School", "Doctor", "Museum", "Cinema"};
        // Replace the Array adapter with your custom adapter.
        // ListAdapter theListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foods);
        ListAdapter customListAdapter = new CustomAdapter(this,foods);// Pass the food arrary to the constructor.
        ListView customListView = (ListView) findViewById(R.id.custom_listview);
        customListView.setAdapter(customListAdapter);

        customListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        String food = String.valueOf(parent.getItemAtPosition(position));
//                        Toast.makeText(RoutesActivity.this, food, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RoutesActivity.this,NavigateActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

}
