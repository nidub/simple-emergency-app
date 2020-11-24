package com.appdid.emergencyservice;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Ambulances extends AppCompatActivity {

    Long num = 101L;
    ImageButton firebrigadecall,firebrigadenav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulances);
        getSupportActionBar().setTitle("Fire Brigade");

        firebrigadecall = findViewById(R.id.firebrigadecall);
        firebrigadenav = findViewById(R.id.firebrigadenav);


        firebrigadecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent calli = new Intent(Intent.ACTION_DIAL);
                calli.setData(Uri.parse("tel:"+ num));
                startActivity(calli);

            }
        });


        firebrigadenav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nav = new Intent(Intent.ACTION_VIEW);
                nav.setData(Uri.parse("https://www.google.com/maps/search/firebrigade+near+me/"));
                startActivity(nav);


            }
        });

    }
}
