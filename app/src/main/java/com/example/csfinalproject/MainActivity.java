package com.example.csfinalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private boolean isCat;
    private boolean isDog;
    private boolean isQuoteSelected;
    private boolean isImageOn;
    private RadioGroup imageGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isQuoteSelected = true;
        isImageOn = true;
        imageGroup = findViewById(R.id.imageGroup);

        Button quoteToggle = findViewById(R.id.quoteToggle);
        Button imageButton = findViewById(R.id.isImageOn);
        try {
            Intent intent = getIntent();
            boolean thisCat = intent.getExtras().getBoolean("isCat", true);
            boolean thisImage = intent.getExtras().getBoolean("isImage", true);
            boolean thisQuote = intent.getExtras().getBoolean("isQuote", true);
            if (thisCat) {
                isCat = true;
                isDog = false;
                imageGroup.check(R.id.catChoice);
            } else {
                isCat = false;
                isDog = true;
                imageGroup.check(R.id.dogChoice);
            }
            if (thisImage) {
                isImageOn = true;
                imageButton.setText(getResources().getString(R.string.imageOn));
                imageGroup.setVisibility(View.VISIBLE);
            } else {
                isImageOn = false;
                imageButton.setText(getResources().getString(R.string.imageOff));
                imageGroup.setVisibility(View.GONE);
            }
            if (thisQuote) {
                isQuoteSelected = true;
                quoteToggle.setText(getResources().getString(R.string.quoteOn));
            } else {
                isQuoteSelected = false;
                quoteToggle.setText(getResources().getString(R.string.quoteOff));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        imageGroup.setOnCheckedChangeListener((unused, checkedId) -> {
            if (checkedId == R.id.catChoice) {
                isCat = true;
                isDog = false;
            } else if (checkedId == R.id.dogChoice){
                isCat = false;
                isDog = true;
            }
        });

        Button create = findViewById(R.id.create);
        create.setOnClickListener(v -> {
            if ((isCat || isDog) || !isImageOn) {
                openBackground();
            } else {
                //show an alert if neither cat or dog is chosen
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Please choose Dog or Cat first!")
                        .setTitle("Cannot create background");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                builder.create().show();
            }
        });

    }

    /** Changes whether the quote will be shown or not based on user selection.**/
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

    /** Changes whether an image will be shown or not based on user selection.**/
    public void imageOnClick(View v) {
        Button imageToggle = (Button) v;
        imageGroup = findViewById(R.id.imageGroup);

        if (imageToggle.getText().toString().equals(getResources().getString(R.string.imageOn))) {
            imageToggle.setText(getResources().getString(R.string.imageOff));
            isImageOn = false;
            isCat = false;
            isDog = false;
            imageGroup.setVisibility(View.GONE);
        } else {
            imageToggle.setText(getResources().getString(R.string.imageOn));
            isImageOn = true;
            imageGroup.setVisibility(View.VISIBLE);
        }
    }

    /** Starts the background activity.**/
    public void openBackground() {
        Intent createIntent = new Intent(this, Background.class);
        if (isImageOn) {
            createIntent.putExtra("isImageOn", true);
        } else {
            createIntent.putExtra("isImageOn", false);
        }
        if (isCat) {
            createIntent.putExtra("isCat", true);
            createIntent.putExtra("isDog", false);
        } else {
            createIntent.putExtra("isCat", false);
            createIntent.putExtra("isDog", true);
        }
        if (isQuoteSelected) {
            createIntent.putExtra("isQuoteSelected", true);
        } else {
            createIntent.putExtra("isQuoteSelected", false);
        }
        startActivity(createIntent);
    }

}
