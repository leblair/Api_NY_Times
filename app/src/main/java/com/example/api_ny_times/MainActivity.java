package com.example.api_ny_times;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String URL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=france&api-key=QUTFOkYDjdAwxavWDdVZGVbbyEkb6Dhh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void init() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String status = response.optString("status");
                        String num_result = response.optString("num_results");
                        if (status.equals("OK") & Integer.parseInt(num_result) > 0) {
                            JSONArray jsonArray = response.optJSONArray("results");
                            List<Result> results = Arrays.asList(new GsonBuilder().create().fromJson(jsonArray.toString(),
                                    Result[].class));



                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
    }
}

/* Json
* public string status { get; set; }
        public string copyright { get; set; }
        public bool has_more { get; set; }
        public int num_results { get; set; }
        public List<Result> results { get; set; }*/