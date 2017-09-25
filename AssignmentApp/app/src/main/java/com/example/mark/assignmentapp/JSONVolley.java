package com.example.mark.assignmentapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class JSONVolley  extends AppCompatActivity{

    final Button button = (Button)findViewById(R.id.bike_request);

    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_volley);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dublinBike();
            }
        });
    }


    private void dublinBike() {
        RequestQueue q = Volley.newRequestQueue(this);
        String url = "https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=af7ae12c3e18f5b9ba7b48cfc839e8aeca0f4350";
        StringRequest request = getStringRequest(url);
        q.add(request);
    }

    private StringRequest getStringRequest(String url) {
        return new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("bikes", response);
            }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
        });
    }
}


