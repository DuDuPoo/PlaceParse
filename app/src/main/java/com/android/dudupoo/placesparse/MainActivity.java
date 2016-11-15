package com.android.dudupoo.placesparse;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
//https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyBy8REIGr6qiBPBPS_fy2_UqEZ6Vs_h27E

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<String> placesNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetPlacesTask getPlacesTask = new GetPlacesTask();
        getPlacesTask.execute(new String[]{"https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyBy8REIGr6qiBPBPS_fy2_UqEZ6Vs_h27E"});

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    private class GetPlacesTask extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings)
        {
            String result = "";

            try
            {
                URL url = new URL(strings[0]);
                HttpHandler httpHandler = new HttpHandler(url);
                result = httpHandler.getURLResponse();
                JSONObject mainResponse = new JSONObject(result);
                JSONArray resultsKeyArray = mainResponse.getJSONArray("results");
                Log.d("ResultsKeyArray", resultsKeyArray.toString());
                JSONObject placeJson = resultsKeyArray.getJSONObject(0);
                Log.d("PLACE", placeJson.toString());
                String place= placeJson.getString("name");
                placesNames.add(place);

                Log.d("NAMES", placesNames.toString());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            adapter = new RecyclerViewAdapter(placesNames);
            Log.d("NAMESARRAY", placesNames.toString());
            recyclerView.setAdapter(adapter);
        }
    }

}

