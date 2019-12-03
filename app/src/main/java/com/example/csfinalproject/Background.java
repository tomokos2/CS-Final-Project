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
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        Intent mainIntent = getIntent();
        Boolean isQuoteSelected = mainIntent.getBooleanExtra("isQuoteSelected", true);
        quote = findViewById(R.id.quote);
        if (!isQuoteSelected) {
            quote.setVisibility(View.GONE);
        }
        //Insert random quote generator api, returns string quote.
        image = findViewById(R.id.image);
        image.setVisibility(View.VISIBLE);

        image.setImageResource(R.drawable.images);
        //Insert random generated image from api.
    }

    public void openMain() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }


}
