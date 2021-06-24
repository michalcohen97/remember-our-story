package com.example.instagramsdk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(v -> {
            shareStory();
        });
    }

    private void shareStory() {
        //This URI object has to point to a LOCAL file on the device.
        Uri contentUri = Uri.parse(
                "android.resource://"
                        + getPackageName()
                        + "/"
                        + R.raw.pic
        );
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
        shareIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(shareIntent, "sometext"));
        //The SHARE is not working, it seems like the pic is not pass to the target app.
    }
}