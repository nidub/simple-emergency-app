package com.appdid.emergencyservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Confirmpassword extends AppCompatActivity {

    EditText pwd,cpwd,fgemail,oldpsd;
    private DatabaseHelper databaseHelper;
    private Emergencyservice emergencyservice;
    LinearLayout confirmpwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmpassword);

        pwd=findViewById(R.id.ccpwd);
        cpwd=findViewById(R.id.ccfpwd);
        oldpsd=findViewById(R.id.oldpwd);

        confirmpwd=findViewById(R.id.confirmpwd);
        fgemail=findViewById(R.id.fgemail1);
        databaseHelper = new DatabaseHelper(Confirmpassword.this);
        emergencyservice = new Emergencyservice();

    }

    public void cpsubmit(View view) {

        String value1 = pwd.getText().toString().trim();
        String value2 = cpwd.getText().toString().trim();
        String email = fgemail.getText().toString().trim();
        String old = oldpsd.getText().toString().trim();
        if (!email.isEmpty()) {
            if (!value1.isEmpty()) {
                if (!value2.isEmpty()) {
                    if (value1.equals(value2)) {
                        if (databaseHelper.checkUser(email, old)) {
                            databaseHelper.updatePassword(email, value1);

                            Toast.makeText(this, "Password reset Successfully", Toast.LENGTH_SHORT).show();
                            emptyInputEditText();

                            Intent intent = new Intent(this, Login.class);
                            startActivity(intent);
                            finish();
                        } else
                            Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(this, "Password reset Unsuccessfully", Toast.LENGTH_SHORT).show();
                } else {
                    cpwd.setError("Enter Confirm Password");
                    cpwd.requestFocus();
                }
            } else {
                pwd.setError("Enter Password");
                pwd.requestFocus();
            }
        } else {
            fgemail.setError("Enter Email");
            fgemail.requestFocus();
        }

        /*if ((pwd.getText().toString().trim().contentEquals(cpwd.getText().toString().trim())) && pwd != null && cpwd != null){
            databaseHelper.updatePassword(email, value1);

            Toast.makeText(this, "password reset successfully", Toast.LENGTH_SHORT).show();
            emptyInputEditText();

            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }
        else
            Toast.makeText(this, "password not reset successfully", Toast.LENGTH_SHORT).show();*/
    }

    private void emptyInputEditText() {
        cpwd.setText("");
        pwd.setText("");
    }

}