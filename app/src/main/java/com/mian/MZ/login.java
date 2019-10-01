package com.mian.MZ;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    TextView btnRegister;
    EditText login_email, login_password;
    Button btn_login;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MyPrefLocalStorage localStorage = new MyPrefLocalStorage(login.this);

        sharedPreferences = localStorage.getSharedPreferences();
        editor = localStorage.getEditor();


        if (sharedPreferences.getBoolean("loggedIn", false)) {

            Intent intent = new Intent(login.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }


        login_email = (EditText) findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);

        btn_login = (Button) findViewById(R.id.btn_login);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loginFormValidation()) {

                    Intent intent = new Intent(login.this, HomeActivity.class);

                    //sending data to another activity using shared preferences
                    editor.putString("email",login_email.getText().toString()).apply();
                    editor.putString("password",login_password.getText().toString()).apply();

                    //sendind data to another activity using intent
                    /* intent.putExtra("email", login_email.getText().toString());
                    intent.putExtra("password", login_password.getText().toString());*/

                    editor.putBoolean("loggedIn", true).apply();

//                    startService(new Intent(getApplicationContext(), MyBackgroundService.class));
                    startActivity(intent);
                    finish();
                }

            }
        });


        checkPermissions();


    }

    public void checkPermissions() {


        String[] permission = new String[]{Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE};

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(login.this, permission, 1);

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

            if (grantResults.length > 0) {

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Camera Permission Allowed !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Camera Permission Denied !", Toast.LENGTH_LONG).show();
                }

                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Call Permission Allowed !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Call Permission Denied !", Toast.LENGTH_LONG).show();
                }

            }

        }

    }

    private boolean loginFormValidation() {
        boolean isValid = true;

        if (login_email.length() == 0) {
            login_email.setError("Field is required!");
            isValid = false;
        }
        if (login_email.length() > 0 && !isEmailValid(login_email.getText().toString())) {
            login_email.setError("Email is not valid!");
            login_password.setFocusable(true);
            isValid = false;
        }

        if (login_password.length() == 0) {
            login_password.setError("Field is required!");
            login_password.setFocusable(true);
            isValid = false;
        }
        if (login_password.length() < 6) {
            login_password.setError("Password should at least six characters long.");
            isValid = false;
        }
        if (login_password.getText().toString().equals(login_password.getText().toString().toUpperCase())) {
            login_password.setError("At least one character should be in lower case.");
            isValid = false;
        }
        if (login_password.getText().toString().equals(login_password.getText().toString().toLowerCase())) {
            login_password.setError("At least one character should be in upper case.");
            isValid = false;
        }

        return isValid;
    }

    public void openDialerWithNumber(String phone_no) {


    }

    public void makePhoneCall(String phone_no) {
        Intent intent = new Intent(Intent.ACTION_CALL);

        intent.setData(Uri.parse("tel:" + phone_no));

        if (ActivityCompat.checkSelfPermission(login.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    public void composeSmsMessage(String message, String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setType("vnd.android-dir/mms-sms");

        intent.putExtra("address", "12125551212"); // This ensures only SMS apps respond
        intent.putExtra("sms_body", message);

        startActivity(intent);

        /*if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }*/
    }

    public boolean isEmailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
