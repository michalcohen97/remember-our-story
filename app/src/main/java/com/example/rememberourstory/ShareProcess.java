package com.example.rememberourstory;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

/**
 * handle share to story process
 */
public class ShareProcess {
    public final static int VIDEO = 0;
    public final static int IMAGE = 1;

    /**
     * connect to Share to Story API
     * @param resID- content ID index
     * @param type- type of content (image/video)
     * @param currentActivity- current Activity object
     */
    public static void share(int resID, int type, Activity currentActivity) {
        // handle case of user without Instagram app installed
        if (!isUserHaveInstagram(currentActivity.getPackageManager())) {
            noInstagram(currentActivity);
            return;
        }
        //parse URI of content
        Uri resUri;
        String MIMEtype = "";
        if (type == VIDEO) {
            resUri = Uri.parse("android.resource://" + currentActivity.getPackageName() + "/" + resID);
            MIMEtype = "video/mp4";
        } else {
            resUri = Uri.parse("android.resource://" + currentActivity.getPackageName() + "/" + resID);
            MIMEtype = "image/jpeg";
        }
        // create Intent Object to send to Instagram story
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType(MIMEtype);
        sendIntent.putExtra(Intent.EXTRA_STREAM, resUri);
        // send to Story
        currentActivity.startActivity(Intent.createChooser(sendIntent, "Share To Story:"));
    }
    /**
     * send log message in case user does not has Instagram installed
     * @param currentActivity- current Activity object
     */
    private static void noInstagram(Activity currentActivity) {
        Toast.makeText(currentActivity, "Oops! You don't have Instagram App.", Toast.LENGTH_LONG).show();
    }

    /**
     * check if Instagram is installed on user device
     * @param packageManager- devices' PackageManager object
     * @return
     */
    private static boolean isUserHaveInstagram(PackageManager packageManager) {
        try {
            packageManager.getPackageInfo("com.instagram.android", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void shareVideo(int videoID, Activity currentActivity) {
        Intent launchIntent = currentActivity.getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        if (launchIntent == null) {

            return;
        }
        Uri uriVid = Uri.parse("android.resource://" + currentActivity.getPackageName() + "/" + videoID);
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("video/mp4");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Video");
        sendIntent.putExtra(Intent.EXTRA_STREAM, uriVid);
        currentActivity.startActivity(Intent.createChooser(sendIntent, "Share To Story:"));
    }

    public static void shareImage(int imageID, Activity currentActivity) {
        Intent launchIntent = currentActivity.getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        if (launchIntent == null) {
            Toast.makeText(currentActivity, "Oops! You don't have Instagram App.", Toast.LENGTH_LONG).show();
            return;
        }
        Drawable mDrawable = ResourcesCompat.getDrawable(currentActivity.getResources(), imageID, null);
        Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
        String path = MediaStore.Images.Media.insertImage(currentActivity.getContentResolver(), mBitmap, "Emoticon", null);
        Uri imageURI = Uri.parse(path);
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TITLE, "Send message");
        sendIntent.setType("image/*");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Image");
        sendIntent.putExtra(Intent.EXTRA_STREAM, imageURI);
        currentActivity.startActivity(Intent.createChooser(sendIntent, "ASDSADASDASDASDASD"));
    }

}