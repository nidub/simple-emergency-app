package com.appdid.emergencyservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Forgetpassword extends AppCompatActivity {

    EditText fgemail;
    private DatabaseHelper databaseHelper;
    private Emergencyservice emergencyservice;
    LinearLayout fgpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        fgemail=findViewById(R.id.fgemail);

        fgpwd=findViewById(R.id.forgetpwd);

        databaseHelper = new DatabaseHelper(Forgetpassword.this);
        emergencyservice = new Emergencyservice();
    }

    public void fpsubmit(View view) {
        if ((Patterns.EMAIL_ADDRESS.matcher(fgemail.getText().toString()).matches()) && fgemail != null){
            if (databaseHelper.checkUser(fgemail.getText().toString().trim())) {
                Intent fgpcp = new Intent(Forgetpassword.this, Confirmpassword.class);
                fgpcp.putExtra("EMAIL", fgemail.getText().toString().trim());
                emptyInputEditText();
                startActivity(fgpcp);
            }
            else {
                Toast.makeText(Forgetpassword.this, "Enter correct Email Id", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(Forgetpassword.this,"Fields are empty or not valid!",Toast.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        fgemail.setText("");
    }
}