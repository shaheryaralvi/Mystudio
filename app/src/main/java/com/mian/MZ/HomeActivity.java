package com.mian.MZ;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    TextView textViewHome;
    Button btn_logout,startServiceBtn;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    JobScheduler jobScheduler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences("A", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Toast.makeText(HomeActivity.this,sharedPreferences.getString("name",null),Toast.LENGTH_LONG ).show();

        textViewHome = findViewById(R.id.textViewHome);
        btn_logout = findViewById(R.id.btn_Logout_Home);
        startServiceBtn= findViewById(R.id.startService);
        startServiceBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    jobScheduler = (JobScheduler) getApplicationContext()
                            .getSystemService(JOB_SCHEDULER_SERVICE);


                    ComponentName componentName = new ComponentName(getApplicationContext(),
                            MusicPlayerJobService.class);

                    JobInfo jobInfoObj = new JobInfo.Builder(1, componentName)
                            .setOverrideDeadline(0)
                            .setBackoffCriteria(1000, JobInfo.BACKOFF_POLICY_LINEAR)
                            .setPersisted(true)
                            .build();

                    jobScheduler.schedule(jobInfoObj);
                }else
                {
                    startService(new Intent(getApplicationContext(), MyBackgroundService.class));

                }
            }
        });
        //textViewHome.setText(getIntent().getStringExtra("email") + " : " + getIntent().getStringExtra("password"));
       // textViewHome.setText(sharedPreferences.getString("email", null) + " " + sharedPreferences.getString("password", null));

        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //   showDialog();

               // editor.clear().apply();


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        if (jobScheduler != null)
                        jobScheduler.cancelAll();
                    }else{
                         stopService(new Intent(getApplicationContext(), MyBackgroundService.class));

                    }

                //startActivity(new Intent(HomeActivity.this, login.class));
               // finish();

            }
        });


    }

    public void showDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Confirmation");
        builder.setMessage("Do you really want to close the App ?");
        AlertDialog dialog = null;

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finishAffinity();
                System.exit(0);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                builder.setCancelable(true);
            }
        });

        dialog = builder.create();
        dialog.show();

    }

}
