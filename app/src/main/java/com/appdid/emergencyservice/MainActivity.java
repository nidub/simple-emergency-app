package com.appdid.emergencyservice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    ImageView splashimg;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "myprefs";
    public static final  String value = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getPreferences(Context.MODE_PRIVATE);

        splashimg=findViewById(R.id.splashimg);
        Animation ZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomin);
        splashimg.startAnimation(ZoomIn);
        Animation ZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomout);
        splashimg.startAnimation(ZoomOut);

        boolean isLoggedIn   = sharedpreferences.getBoolean(MyPREFERENCES ,false);
        if(isLoggedIn){
            Intent homeintent = new Intent(MainActivity.this, Homeactivity.class);
            startActivity(homeintent);
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent loginintent = new Intent(MainActivity.this, Login.class);
                    startActivity(loginintent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
    }
}
