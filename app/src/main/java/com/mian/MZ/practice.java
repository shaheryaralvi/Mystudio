package com.mian.MZ;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class practice extends AppCompatActivity {

    TextInputLayout TextEmail, TextPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);


        TextEmail = (TextInputLayout) findViewById(R.id.TextEmail);
        TextPassword = (TextInputLayout) findViewById(R.id.TextPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateEmail() | !validatePassword())
                {
                    return;
                }
                Toast.makeText(practice.this,  "Login Successfully", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private boolean validateEmail()
    {
        String emailInput = TextEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            TextEmail.setError("Field can't be empty");
            return false;
        }
        else if(emailInput.length()>15)
        {
            TextEmail.setError("Email is too long");
            return false;
        }
        else {
            TextEmail.setError(null);
            return true;
        }
    }
    private boolean validatePassword()
    {
        String passwordInput = TextPassword.getEditText().getText().toString().trim();
        if(passwordInput.isEmpty())
        {
            TextPassword.setError("Field can't be empty");
            return false;
        }
        else
        {
            TextPassword.setError(null);
            return true;
        }

    }

}
