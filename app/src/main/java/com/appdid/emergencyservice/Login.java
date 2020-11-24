package com.appdid.emergencyservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity {

    EditText email,password;
    Button login;
    LinearLayout loginactivity;
    private DatabaseHelper databaseHelper;
    private Emergencyservice emergencyservice;
    ProgressBar progressBar;
//    ProgressDialog progressDialog;

    SharedPreferences sharedpreferences;
    Pojo pojo;
    public static final String MyPREFERENCES = "myprefs";
    public static final  String value = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.logemail);
        password=findViewById(R.id.logpassword);
        login=findViewById(R.id.logbutton);
        progressBar = findViewById(R.id.progressbar1);
        //progressBar.setVisibility(View.GONE);

        loginactivity=findViewById(R.id.loginactivity);

        databaseHelper = new DatabaseHelper(Login.this);
        emergencyservice = new Emergencyservice();

        pojo = new Pojo(this);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if( isvalidemail() && isvalidpassword()){
                    String useremailvalue = email.getText().toString().trim();
                    String passwordvalue = password.getText().toString().trim();

                    if (databaseHelper.checkUser(useremailvalue, passwordvalue)) {


                        Intent accountsIntent = new Intent(Login.this, Homeactivity.class);
                        accountsIntent.putExtra("EMAIL", useremailvalue);
                        emptyInputEditText();

//                        sharepref.setSession(true);

                        pojo.setPojo("Email", useremailvalue);
                        pojo.setPojo("Pass", passwordvalue);
                        progressBar.setVisibility(View.VISIBLE);
                        startActivity(accountsIntent);

                    } else {
                        // Snack Bar to show success message that record is wrong
                        Toast.makeText(Login.this,"Wrong Email Id or Password!",Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(Login.this,"Fields are empty or not valid!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void emptyInputEditText() {
        email.setText(null);
        password.setText(null);
    }

    boolean isvalidemail() { return (Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) && email != null; }
    boolean isvalidpassword(){return !(password.getText().toString().trim().equals(""));}

    public void register(View view) {
        Intent intent= new Intent(Login.this,Registration.class);
        startActivity(intent);
    }

    public void forgetpassword(View view) {
        Intent intent= new Intent(Login.this,Confirmpassword.class);
        startActivity(intent);
    }
}