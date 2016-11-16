package com.android.dudupoo.placesparse;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler
{
    private URL url;
    public HttpHandler(URL url)
    {
        this.url = url;
    }

    public String getURLResponse()
    {
        String response = "";

        try
        {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            InputStream is = new BufferedInputStream(urlConnection.getInputStream());
            response = convertStreamIntoString(is);
            Log.d("RESPONSE", response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return response;
    }

    public String convertStreamIntoString(InputStream inputStream)
    {
        String response = "";
        String line;
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
            response = sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return response;
    }
}
