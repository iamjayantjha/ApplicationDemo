package com.manipal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

/**Coded By iamjayantjha on 18/04/2021
 * **/

public class SplashActivity extends AppCompatActivity {
    ImageView logo;
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.logo);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(mainIntent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            SplashActivity.this.finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}