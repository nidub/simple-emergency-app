package com.appdid.emergencyservice;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class policestation extends AppCompatActivity {

    Long num = 119L;

    ImageButton policecall,policenavigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policestation);
        getSupportActionBar().setTitle("Police Station");


        policecall = findViewById(R.id.policecall);

        policenavigate = findViewById(R.id.policenavigate);


        policecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent calli = new Intent(Intent.ACTION_DIAL);
                calli.setData(Uri.parse("tel:"+ num));
                startActivity(calli);

            }
        });


        policenavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nav = new Intent(Intent.ACTION_VIEW);
                nav.setData(Uri.parse("https://www.google.com/maps/search/police1+station+near+me/"));
                startActivity(nav);


            }
        });







    }
}
