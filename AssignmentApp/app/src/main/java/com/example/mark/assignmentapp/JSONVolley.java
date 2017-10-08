//Adapted from:
//https://www.thorntech.com/2016/03/parsing-json-android-using-volley-library/

package com.example.mark.assignmentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONVolley  extends AppCompatActivity{

    TextView results;

    String JsonURL = "https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=af7ae12c3e18f5b9ba7b48cfc839e8aeca0f4350";
    // This string will hold the results
    String data = "";
    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_volley);
        // Creates the Volley request queue
        requestQueue = Volley.newRequestQueue(this);

        // Casts results into the TextView found within the main layout XML with id jsonData
        results = (TextView) findViewById(R.id.jsonData);

        // Creating the JsonArrayRequest class called arrayreq, passing the required parameters
        //JsonURL is the URL to be fetched from
        JsonArrayRequest arrayreq = new JsonArrayRequest(JsonURL,
                // The second parameter Listener overrides the method onResponse() and passes
                //JSONArray as a parameter
                new Response.Listener<JSONArray>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            JSONArray stationArray = new JSONArray(response.toString());
                            // Iterates through the JSON Array getting objects and adding them
                            //to the list view until there are no more objects in colorArray
                            for (int i = 0; i < stationArray.length(); i++) {
                                //gets each JSON object within the JSON array
                                JSONObject jsonObject = stationArray.getJSONObject(i);


                                String stationNumber = jsonObject.getString("number");
                                String stationAddress = jsonObject.getString("address");
                                String availableBikes = jsonObject.getString("available_bikes");
                                String availableStands = jsonObject.getString("available_bike_stands");

                                // Adds strings from the current object to the data string
                                //spacing is included at the end to separate the results from
                                //one another
                                data += "Station Number: " + stationNumber +
                                        "\nStation Name : " + stationAddress +
                                        "\nAvailable Bikes: " + availableBikes +
                                        "\nAvailable Stands: " + availableStands + "\n\n";
                            }
                            // Adds the data string to the TextView "results"
                            results.setText(data);
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON array request "arrayreq" to the request queue
        requestQueue.add(arrayreq);
    }
}


