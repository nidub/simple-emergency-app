package com.appdid.emergencyservice;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class Hospital extends AppCompatActivity {

    Long num = 108L;
    ImageButton ambulancescall,ambulancesnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        getSupportActionBar().setTitle("Hospital");

        ambulancescall = findViewById(R.id.ambulancescall);
        ambulancesnav = findViewById(R.id.ambulancesnav);


        ambulancescall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent calli = new Intent(Intent.ACTION_DIAL);
                calli.setData(Uri.parse("tel:"+ num));
                startActivity(calli);

            }
        });


        ambulancesnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nav = new Intent(Intent.ACTION_VIEW);
                nav.setData(Uri.parse("https://www.google.com/maps/search/hospital+near+me/"));
                startActivity(nav);


            }
        });



    }


}
