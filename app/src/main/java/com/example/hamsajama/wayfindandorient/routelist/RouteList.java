package com.example.hamsajama.wayfindandorient.routelist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.hamsajama.wayfindandorient.R;
import com.example.hamsajama.wayfindandorient.routes.Route1;

import java.io.File;

public class RouteList extends AppCompatActivity {
//    String routeNames;
//    File routeFile;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_routelist);
//        routeNames = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/route_names/";
//        String fname = "route.txt";
//        routeFile = new File (routeNames, fname);
//
//        String [] routes = {"Acton Library","Middlesex University Students Union","Shop"};
//        Integer[] images = { R.drawable.actonlibrary,
//                R.drawable.mdxsu, R.drawable.shop2,};
//
//        ListAdapter routeAdapter = new CustomRouteAdapter(this,routes,images);
//        ListView routesListView = (ListView) findViewById(R.id.listView);
//        routesListView.setAdapter(routeAdapter);
//
//        routesListView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        if (position == 0) {
//                            Intent intent1 = new Intent(RouteList.this, Route1.class);
//
//                            startActivity(intent1);
//                        }
//                        if (position == 1) {
//                            Intent intent1 = new Intent(RouteList.this, Route2.class);
//                            startActivity(intent1);
//                        }
//
//                        if (position == 2) {
//                            Intent intent1 = new Intent(RouteList.this, Route3.class);
//                            startActivity(intent1);
//                        }
//
//                    }
//                }
//        );
//
//        Button routeButton = (Button)findViewById(R.id.newRouteButton);
//        routeButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//
//                Intent newRoute = new Intent(RouteList.this, AddRoute.class);
//
//                startActivity(newRoute);
//            }
//        });
//    }
}
