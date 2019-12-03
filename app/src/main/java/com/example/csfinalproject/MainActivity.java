package com.example.csfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button create;
    private Button colorTheme;
    private Button cat;
    private Button dog;
    private Boolean isQuoteSelected;
    private Boolean isImageOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        ConstraintLayout bgElement = findViewById(R.id.bg);
        bgElement.setBackgroundColor(color);
         */
        create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBackground();
            }
        });

    }
    public void quoteOnClick(View v) {
        Button quoteToggle = (Button) v;
        if (quoteToggle.getText().toString().equals(getResources().getString(R.string.quoteOn))) {
            quoteToggle.setText(getResources().getString(R.string.quoteOff));
            isQuoteSelected = false;
        } else {
            quoteToggle.setText(getResources().getString(R.string.quoteOn));
            isQuoteSelected = true;
        }
    }
    public void imageOnClick(View v) {
        Button imageToggle = (Button) v;
        cat = findViewById(R.id.cat);
        dog = findViewById(R.id.dog);
        if (imageToggle.getText().toString().equals(getResources().getString(R.string.imageOn))) {
            imageToggle.setText(getResources().getString(R.string.imageOff));
            isImageOn = false;

            cat.setVisibility(View.GONE);
            dog.setVisibility(View.GONE);
        } else {
            imageToggle.setText(getResources().getString(R.string.imageOn));
            isImageOn = true;
            cat.setVisibility(View.VISIBLE);
            dog.setVisibility(View.VISIBLE);
        }
    }
    public void openBackground() {
        Intent createIntent = new Intent(this, Background.class);
        createIntent.putExtra("isQuoteSelected", isQuoteSelected);
        startActivity(createIntent);
    }

}
