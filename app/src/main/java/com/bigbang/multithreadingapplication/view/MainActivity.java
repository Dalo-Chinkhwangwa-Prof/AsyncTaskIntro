package com.bigbang.multithreadingapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import com.bigbang.multithreadingapplication.R;
import com.bigbang.multithreadingapplication.task.CounterAsyncTask;

import static com.bigbang.multithreadingapplication.task.CounterAsyncTask.TASK_FILTER;

public class MainActivity extends AppCompatActivity {

    private CounterAsyncTask counterAsyncTask;

    private BroadcastReceiver taskReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String progress = intent.getStringExtra(CounterAsyncTask.UPDATE_KEY);
            Toast.makeText(MainActivity.this, progress, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(taskReceiver, new IntentFilter(TASK_FILTER));

        counterAsyncTask = new CounterAsyncTask(this.getApplicationContext());
        counterAsyncTask.execute(14);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(taskReceiver != null)
            unregisterReceiver(taskReceiver);
    }

    //AsyncTask
}
