package com.mian.MZ;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


public class BroadCastReceivingActivity extends AppCompatActivity {

    TextView textView;
    RecyclerView recyclerView;
    BroadCastReceiver broadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_reciver);

        textView = findViewById(R.id.textView);
        recyclerView = findViewById(R.id.recyclerView);

       }

    @Override
    protected void onStart() {
        super.onStart();
        broadCastReceiver = new BroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadCastReceiver,intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadCastReceiver);

    }
}
