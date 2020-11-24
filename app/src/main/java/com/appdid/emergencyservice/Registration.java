package com.appdid.emergencyservice;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hbb20.CountryCodePicker;

public class Registration extends AppCompatActivity {

    EditText rusername,remail,rphonenumber,rpassword,rcpassword;
    Button rregister;
    CountryCodePicker ccp;
    private DatabaseHelper databaseHelper;
    private Emergencyservice emergencyservice;
    LinearLayout registration;
    ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        rusername=findViewById(R.id.regusername);
        remail=findViewById(R.id.regemail);
        rphonenumber=findViewById(R.id.regphonenumber);
        rpassword=findViewById(R.id.regpassword);
        rcpassword=findViewById(R.id.regcpassword);
        rregister=findViewById(R.id.regbutton);
        ccp=findViewById(R.id.ccp);
        pg=findViewById(R.id.progressbar2);

        registration=findViewById(R.id.registration);

        databaseHelper = new DatabaseHelper(Registration.this);
        emergencyservice = new Emergencyservice();

        rregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isvalidname() && isvalidemail() && isvalidphonenumber() && isvalidpassword()){
                    if (!databaseHelper.checkUser(remail.getText().toString().trim())) {
                        emergencyservice.setName(rusername.getText().toString().trim());
                        emergencyservice.setEmail(remail.getText().toString().trim());
                        emergencyservice.setPassword(rpassword.getText().toString().trim());

                        databaseHelper.insertData(emergencyservice);

                        Toast.makeText(Registration.this,"Successfully Registered", Toast.LENGTH_LONG).show();
                        emptyInputEditText();
                        Intent reghome = new Intent(Registration.this, Homeactivity.class);
                        pg.setVisibility(View.VISIBLE);
                        startActivity(reghome);

                    } else {
                        // Snack Bar to show error message that record already exists
                        Toast.makeText(Registration.this, "Properly enter Inputs", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(Registration.this,"Fields are empty or not valid!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void emptyInputEditText() {
        rusername.setText(null);
        remail.setText(null);
        rphonenumber.setText(null);
        rpassword.setText(null);
        rcpassword.setText(null);
    }

    boolean isvalidname(){return !(rusername.getText().toString().trim().equals(""));}
    boolean isvalidemail() { return (Patterns.EMAIL_ADDRESS.matcher(remail.getText().toString()).matches()) && remail != null; }
    boolean isvalidphonenumber(){return !(rphonenumber.getText().toString().equals(""));}
    boolean isvalidpassword(){return (rpassword.getText().toString().trim().contentEquals(rcpassword.getText().toString().trim()));}

    public void login(View view) {
        Intent intent= new Intent(Registration.this,Login.class);
        startActivity(intent);
    }
}