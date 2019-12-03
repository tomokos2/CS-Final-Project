package com.example.csfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button create;

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

    public void openBackground() {
        Intent createIntent = new Intent(this, Background.class);
        startActivity(createIntent);
    }

}
