package com.example.csfinalproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class Background extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
                openMain();
        });

        Intent mainIntent = getIntent();
        boolean isQuoteSelected = mainIntent.getExtras().getBoolean("isQuoteSelected", true);

        boolean isCat = mainIntent.getExtras().getBoolean("isCat", true);

        boolean isImageOn = mainIntent.getExtras().getBoolean("isImageOn", true);


        ConstraintLayout bgElement = findViewById(R.id.bg);
        //RandomColor randomColor = new RandomColor();


        // Set up constants for different background colors.
        // Enter variable color that will change randomly.
        String[] colors = new String[] {
                "#E0BBE4", "#957DAD", "#D291BC", "#FEC8D8", "#FFDFD3",
                "#EFB0C9", "#F4C2D7", "#F8DAE9", "#B9D6F3", "#A1C9F1", "#F1E8D9",
                "#B29DD9", "#FDFD98", "#FE6B64", "#77DD77", "#779ECB",
                //Pretty Pastel Color
                "#FDCFB3", "#CEC8E4", "#F9F7E8",
                //Pastel Pales Color
                "#FFDDDD", "#FFFFCF", "#D9FFDF", "#D9FFFF",
                //Pastel Backyard Color
                "#F7EF64", "#F3FAF1", "#E2EEC2", "#B4D7A2",
                //Pastel Forest Color
                "#B7C68B", "#F4F0CB", "#DED29E", "#B3A580", "#A29574",
                "#BED7D1", "#F7EBC3", "#FBD6C6", "#F8E1E7", "#F8D1E0",
                "#FF756D", "#FFF49C", "#F9FFCB", "#85DE77", "#FF9AA2", "#FFB7B2",
                "#FFDAC1", "#E2F0CB", "#B5EAD7", "#C7CEEA", "#769ECB", "#9DBAD5",
                "#FAF3DD", "#C8D6B9", "#8FC1A9", "#7CAA98", "#D99294", "#F5B7B7",
                "#F6CACB", "#D2E9DA", "#AFDAC1"
        };
        int rnd = new Random().nextInt(colors.length);
        bgElement.setBackgroundColor(Color.parseColor(colors[rnd]));




        TextView quote = findViewById(R.id.quote);
        if (!isQuoteSelected) {
            quote.setVisibility(View.GONE);
        } else {
            quote.setVisibility(View.VISIBLE);
            //Enter webApi quote things
            String url = "https://api.adviceslip.com/advice";
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject object = response.getJSONObject("slip");
                                String advice = object.get("advice").toString();
                                System.out.println(advice);
                                quote.setText(advice);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            error.printStackTrace();
                        }
                    });

            queue.add(jsonObjectRequest);

        }

        ImageView image = findViewById(R.id.image);
        if (!isImageOn) {
            image.setVisibility(View.GONE);
        } else if (!isCat) {
            //Enter random dog api generator
            String url = "https://dog.ceo/api/breeds/image/random";
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String message = response.get("message").toString();
                                //How to set image using message above such as https:\/\/images.dog.ceo\/breeds\/coonhound\/n02089078_4508.jpg?
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            error.printStackTrace();
                        }
                    });

            queue.add(jsonObjectRequest);


            image.setImageResource(R.drawable.download);
        } else {
            //Enter random cat api generator
            image.setImageResource(R.drawable.images);
        }
    }

    /** Takes user back to start menu.**/
    public void openMain() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }


}
