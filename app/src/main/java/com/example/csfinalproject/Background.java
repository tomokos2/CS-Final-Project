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

        /*
        ConstraintLayout bgElement = findViewById(R.id.bg);
        bgElement.setBackgroundColor(color);
        Set up constants for different background colors.
        Enter variable color that will change randomly.
        */

        TextView quote = findViewById(R.id.quote);
        if (!isQuoteSelected) {
            quote.setVisibility(View.GONE);
        } else {
            quote.setVisibility(View.VISIBLE);
            //Enter webApi quote things
        }

        ImageView image = findViewById(R.id.image);
        if (!isImageOn) {
            image.setVisibility(View.GONE);
        } else if (!isCat) {
            //Enter random cat api generator
            image.setImageResource(R.drawable.download);
        } else {
            //Enter random dog api generator
            image.setImageResource(R.drawable.images);
        }
    }

    /** Takes user back to start menu.**/
    public void openMain() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }


}
