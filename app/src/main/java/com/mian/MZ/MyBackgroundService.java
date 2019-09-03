package com.mian.MZ;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MyBackgroundService extends Service {

    private static final String TAG = MyBackgroundService.class.getName();
    int counter;
    Timer timer ;
    TimerTask timerTask ;
    public MyBackgroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        counter = 0;
        Log.e(TAG, "Service started " );
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.e(TAG, "run: "+counter++ );
            }
        };
        timer.scheduleAtFixedRate(timerTask,1000,10000);




        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        Log.e(TAG, "Service ended " );
        timer.cancel();
        timerTask.cancel();
    }

}


