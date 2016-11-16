package com.android.dudupoo.placesparse;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.dudupoo.placesparse.pojo.place.Photo;
import com.android.dudupoo.placesparse.pojo.place.Place;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Place> placesArrayList = new ArrayList<>();
    ArrayList<Bitmap> placePhotoArrayList = new ArrayList<>();
    ArrayList<String> imageUrls= new ArrayList<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetPlacesTask getPlacesTask = new GetPlacesTask();
        getPlacesTask.execute(new String[]{"https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=50000&type="+R.string.TYPE+"&keyword="+R.string.KEYWORD+"&key="+R.string.API_KEY});

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        context = getApplicationContext();
        adapter = new RecyclerViewAdapter(placesArrayList, imageUrls, context);
        recyclerView.setAdapter(adapter);
    }

    private class GetPlacesTask extends AsyncTask<String, String, ArrayList<Place>>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Place> doInBackground(String... strings)
        {
            Place place = null;
            Photo placePhoto = null;
            try
            {
                URL url = new URL(strings[0]);
                HttpHandler httpHandler = new HttpHandler(url);
                String result = httpHandler.getURLResponse();
                JSONObject mainResponse = new JSONObject(result);

                JSONArray resultsKeyArray = mainResponse.getJSONArray("results");

                for (int i = 0; i<resultsKeyArray.length(); i++)
                {
                    JSONObject placeJson = resultsKeyArray.getJSONObject(i);
                    if(placeJson.has("photos"))
                    {
                        JSONArray photoArray = placeJson.getJSONArray("photos");
                        JSONObject photoObject = photoArray.getJSONObject(0);
                        placePhoto = new Photo(photoObject.getInt("width"), photoObject.getString("photo_reference"));
                    }

                    if(placeJson.has("rating"))
                    {
                        place = new Place(placeJson.getString("name"), placeJson.getDouble("rating"), placePhoto);
                        placesArrayList.add(place);
                    }

                }
                Log.d("PLACESARRAY", placesArrayList.toString());

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            return placesArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Place> placesArrayList)
        {
            super.onPostExecute(placesArrayList);

            for(Place place : placesArrayList)
            {
                Photo placePhoto = place.getPlacePhoto();
                if(placePhoto!=null)
                {
                    String urlString = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=" + placePhoto.getWidth() + "&photoreference=" + placePhoto.getPhoto_reference() + "&key=" + R.string.API_KEY;
                    imageUrls.add(urlString);
                    Log.d("PhotoURL", urlString);
                }
                else
                {
                    imageUrls.add("NULL_URL");
                }
            }
            adapter.notifyDataSetChanged();
        }
    }//End of GetPlacesTask
}

