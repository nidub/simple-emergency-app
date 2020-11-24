package com.appdid.emergencyservice;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class Homeactivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton mb1,mb2,mb3;
    ProgressBar pg;
    Pojo pojo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);

         mb1= findViewById(R.id.next1);
         mb2=findViewById(R.id.next2);
         mb3=findViewById(R.id.next3);

         pojo = new Pojo(this);

        mb1.setOnClickListener(this);
        mb2.setOnClickListener(this);
        mb3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.next1:
                Intent intent1= new Intent(Homeactivity.this,Ambulances.class);
                startActivity(intent1);
                break;
            case R.id.next2:
                Intent intent2= new Intent(Homeactivity.this,Hospital.class);
                startActivity(intent2);
                break;
            case R.id.next3:
                Intent intent3= new Intent(Homeactivity.this,policestation.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save){
            Intent intent = new Intent(Homeactivity.this,Login.class);
            pojo.clearData();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
