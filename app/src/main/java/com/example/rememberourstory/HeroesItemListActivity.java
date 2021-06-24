package com.example.rememberourstory;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageView;
/**
 * handle hero item list screen
 */
public class HeroesItemListActivity extends AppCompatActivity {
    private Button returnBtn;
    private ImageView malkaHeroImage;
    /**
     * initialize screen
     * @param savedInstanceState- dynamic data of app activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heros_item_list);
        initVars();
        setOnClick();
    }

    /**
     * add event listener to screen's buttons
     */
    private void setOnClick() {
        // return to main screen
        returnBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HeroesItemListActivity.this, MainActivity.class);
            startActivity(intent);
        });
        //move to chosen hero activity page
        malkaHeroImage.setOnClickListener(v -> {
            Intent intent = new Intent(HeroesItemListActivity.this, StoryActivity.class);
            startActivity(intent);
        });
    }

    /**
     * initialize pick hero screen
     */
    private void initVars() {
        //add return btn
        returnBtn = findViewById(R.id.returBtn);
        //add heroes clickable images
        malkaHeroImage = findViewById(R.id.heroImage);
        malkaHeroImage.bringToFront();
    }
}