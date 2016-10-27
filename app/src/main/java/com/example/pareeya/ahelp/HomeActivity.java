package com.example.pareeya.ahelp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static com.example.pareeya.ahelp.R.id.imageView3;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // image animation
        // Load the ImageView that will host the animation and
        // set its background to our AnimationDrawable XML resource.
        ImageView img = (ImageView) findViewById(R.id.imageView3);
        img.setBackgroundResource(R.drawable.butalarm);

        // Get the background, which has been compiled to an AnimationDrawable object.
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        // Start the animation (looped playback by default).
        frameAnimation.start();

    }
    public void clickHomeGoSetting  (View view){
        startActivity(new Intent(HomeActivity.this,SettingActivity.class));

    }
}
