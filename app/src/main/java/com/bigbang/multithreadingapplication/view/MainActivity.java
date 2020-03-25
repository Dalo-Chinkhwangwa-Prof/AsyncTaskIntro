package com.bigbang.multithreadingapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigbang.multithreadingapplication.R;
import com.bigbang.multithreadingapplication.task.CounterAsyncTask;

import static com.bigbang.multithreadingapplication.task.CounterAsyncTask.TASK_FILTER;
import static com.bigbang.multithreadingapplication.task.CounterAsyncTask.UPDATE_KEY;
import static com.bigbang.multithreadingapplication.util.DebugLogger.logDebug;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private CounterAsyncTask counterAsyncTask;

    private ImageView imageView;

    Handler osHandler = new Handler(this);

    /*private BroadcastReceiver taskReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String progress = intent.getStringExtra(CounterAsyncTask.UPDATE_KEY);
            if(progress == null)
            {
              Toast.makeText(MainActivity.this, "COMPLETED!!!!", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(MainActivity.this, progress, Toast.LENGTH_SHORT).show();
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.main_imageview);

        /*osHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "openning New Activity", Toast.LENGTH_SHORT).show();
                imageView.setBackground(getDrawable(R.drawable.ic_launcher_background));
            }
        }, 5000);*/


        logDebug(Thread.currentThread().getName());
        Thread x = new Thread(){
            @Override
            public synchronized void run() {
                super.run();
                imageView.setBackground(getDrawable(R.drawable.ic_launcher_background));
                logDebug(Thread.currentThread().getName());
            }
        };

        x.start();
//        registerReceiver(taskReceiver, new IntentFilter(TASK_FILTER));

        counterAsyncTask = new CounterAsyncTask(osHandler);
        counterAsyncTask.execute(14);
    }

    @Override
    protected void onStop() {
        super.onStop();
        osHandler = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        if(taskReceiver != null)
//            unregisterReceiver(taskReceiver);
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        String update = msg.getData().getString(UPDATE_KEY);

        Toast.makeText(this, update, Toast.LENGTH_SHORT).show();

        return true;
    }

    //AsyncTask
}
