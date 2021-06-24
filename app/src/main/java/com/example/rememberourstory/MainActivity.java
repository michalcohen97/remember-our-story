package com.example.rememberourstory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * handle main screen
 */
public class MainActivity extends AppCompatActivity {

    private Button btnStart;

    /**
     * initialize screen
     * @param savedInstanceState- dynamic data of app activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //add listener to start button
        btnStart = findViewById(R.id.startBtn);
        //move to hero item list screen
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HeroesItemListActivity.class);
                startActivity(intent);
            }
        });


    }
}