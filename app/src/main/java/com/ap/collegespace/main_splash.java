package com.ap.collegespace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class main_splash extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        final GifImageView loder = (GifImageView)findViewById(R.id.splash_loader);
        loder.setAnimatedGif(R.raw.main_ld, GifImageView.TYPE.FIT_CENTER);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(main_splash.this, main.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, 3000);
    }
}
