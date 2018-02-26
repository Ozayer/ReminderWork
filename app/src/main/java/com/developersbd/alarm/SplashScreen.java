package com.developersbd.alarm;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;




public class SplashScreen extends ActionBarActivity {
    ProgressBar mprogressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        ImageView img = (ImageView)findViewById(R.id.imageView);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_down);
        img.setAnimation(anim);

        mprogressbar = (ProgressBar)findViewById(R.id.progressBar);
        ObjectAnimator anim1 = ObjectAnimator.ofInt(mprogressbar, "progress",0,100);
        anim1.setDuration(4000);
        anim1.setInterpolator(new DecelerateInterpolator());
        anim1.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, LanguageSelection.class));
                finish();
            }
        },3000);
    }
    }

