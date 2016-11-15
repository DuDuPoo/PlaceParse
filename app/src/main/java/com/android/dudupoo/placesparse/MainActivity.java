package com.android.dudupoo.placesparse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
//https://maps.googleapis.com/maps/api/place/nearbysearch/json?
// location=-33.8670522,151.1957362&
// radius=500&
// type=restaurant&
// keyword=cruise&
// key=AIzaSyBy8REIGr6qiBPBPS_fy2_UqEZ6Vs_h27F

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
