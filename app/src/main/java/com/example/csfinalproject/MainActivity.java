package com.example.csfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean isCat;
    private boolean isQuoteSelected;
    private boolean isImageOn;
    private RadioGroup imageGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isQuoteSelected = true;
        isCat = true;
        isImageOn = true;
        imageGroup = findViewById(R.id.imageGroup);
        imageGroup.setOnCheckedChangeListener((unused, checkedId) -> {
            if (checkedId == R.id.catChoice) {
                isCat = true;
            } else if (checkedId == R.id.dogChoice){
                isCat = false;
            }
        });
        Button create = findViewById(R.id.create);
        create.setOnClickListener(v -> {
            openBackground();
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
        imageGroup = findViewById(R.id.imageGroup);

        if (imageToggle.getText().toString().equals(getResources().getString(R.string.imageOn))) {
            imageToggle.setText(getResources().getString(R.string.imageOff));
            isImageOn = false;
            isCat = false;
            imageGroup.setVisibility(View.GONE);
        } else {
            imageToggle.setText(getResources().getString(R.string.imageOn));
            isImageOn = true;
            imageGroup.setVisibility(View.VISIBLE);
        }


    }
    public void openBackground() {
        Intent createIntent = new Intent(this, Background.class);
        if (isImageOn) {
            createIntent.putExtra("isImageOn", true);
        } else {
            createIntent.putExtra("isImageOn", false);
        }
        if (isCat) {
            createIntent.putExtra("isCat", true);
        } else {
            createIntent.putExtra("isCat", false);
        }
        if (isQuoteSelected) {
            createIntent.putExtra("isQuoteSelected", true);
        } else {
            createIntent.putExtra("isQuoteSelected", false);
        }
        startActivity(createIntent);
    }

}
