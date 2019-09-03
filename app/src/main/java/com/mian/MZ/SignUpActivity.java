package com.mian.MZ;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText signup_first_name,signup_last_name,signup_email,signup_password,signup_confirm_password,signup_phone_number,signup_address,signup_cnic;

    TextView btnLogin;

    Button signup_btn;

    RadioGroup signup_radiobtn;

    CheckBox signup_checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup_first_name = findViewById(R.id.signup_first_name);
        signup_last_name = findViewById(R.id.signup_last_name);
        signup_email = findViewById(R.id.signup_email);
        signup_password = findViewById(R.id.signup_password);
        signup_confirm_password = findViewById(R.id.signup_confirm_password);
        signup_phone_number = findViewById(R.id.signup_phone_number);
        signup_address = findViewById(R.id.signup_address);
        signup_cnic = findViewById(R.id.signup_cnic);
        signup_btn = findViewById(R.id.signup_btn);
        signup_radiobtn = findViewById(R.id.signup_radiobtn);
        signup_checkbox = findViewById(R.id.signup_checkbox);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SignupFormValidation();
            }
        });

    }
    private boolean SignupFormValidation() {
        boolean isValid = true;

        if (signup_email.length() == 0) {
            signup_email.setError("Field is required!");
            isValid = false;
        }
        if (signup_first_name.length() == 0) {
            signup_first_name.setError("Field is required!");
            isValid = false;
        }
        if (signup_last_name.length() == 0) {
            signup_last_name.setError("Field is required!");
            isValid = false;
        }
        if (signup_email.length() > 0 && !isEmailValid(signup_email.getText().toString())) {
            signup_email.setError("Email is not valid!");
            isValid = false;
        }

        if (signup_password.length() == 0) {
            signup_password.setError("Field is required!");
            isValid = false;
        }
        if (signup_password.length() < 6) {
            signup_password.setError("Password should at least six characters long.");
            isValid = false;
        }
        if (signup_password.getText().toString().equals(signup_password.getText().toString().toUpperCase())) {
            signup_password.setError("At least one character should be in lower case.");
            isValid = false;
        }
        if (signup_password.getText().toString().equals(signup_password.getText().toString().toLowerCase())) {
            signup_password.setError("At least one character should be in upper case.");
            isValid = false;
        }
        if(!signup_confirm_password.getText().toString().equals(signup_password.getText().toString()))
        {
            signup_confirm_password.setError("Password not matched.");
        }
        if (signup_phone_number.length() == 0) {
            signup_phone_number.setError("Field is required!");
            isValid = false;
        }
        if (signup_address.length() == 0) {
            signup_address.setError("Field is required!");
            isValid = false;
        }
        if (signup_cnic.length() == 0) {
            signup_cnic.setError("Field is required!");
            isValid = false;
        }
        int isSelected = signup_radiobtn.getCheckedRadioButtonId();
        if (isSelected == -1)
        {
            Toast.makeText(SignUpActivity.this, "Must choose gender.", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if(!signup_checkbox.isChecked())
        {
            Toast.makeText(SignUpActivity.this, "Must click the check button.", Toast.LENGTH_SHORT).show();
        }


        return isValid;
    }
    public boolean isEmailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
