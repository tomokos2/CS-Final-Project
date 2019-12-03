package com.example.csfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Background extends AppCompatActivity {
    private Button backButton;
    private TextView quote;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        backButton = findViewById(R.id.backButton);
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
         */

        quote = findViewById(R.id.quote);
        if (!isQuoteSelected) {
            quote.setVisibility(View.GONE);
        } else {
            quote.setVisibility(View.VISIBLE);
        }

        //Insert random quote generator api, returns string quote.
        image = findViewById(R.id.image);
        if (!isImageOn) {
            image.setVisibility(View.GONE);
        } else if (!isCat) {
            image.setImageResource(R.drawable.download);
        } else {
            image.setImageResource(R.drawable.images);
        }

        //Insert random generated image from api.

    }

    public void openMain() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }


}
