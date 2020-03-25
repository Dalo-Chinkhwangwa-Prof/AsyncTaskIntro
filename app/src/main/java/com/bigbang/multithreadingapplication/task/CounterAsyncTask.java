package com.bigbang.multithreadingapplication.task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import static com.bigbang.multithreadingapplication.util.DebugLogger.logDebug;
import static com.bigbang.multithreadingapplication.util.DebugLogger.logError;

public class CounterAsyncTask extends AsyncTask<Integer, String, Boolean> {

    public static final String UPDATE_KEY = "UPDATE_KEY";
    public static final String TASK_FILTER = "com.from.task";

    @SuppressLint("StaticFieldLeak")
    private Context applicationContext;

    public CounterAsyncTask(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected Boolean doInBackground(Integer... seconds) {

        for (int i = 1; i <= seconds[0]; i++) {

            try {
                logDebug("Seconds : " + i);
                publishProgress("Seconds : " + i);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                logError(e);
            }

        }
        return true;
    }


    @Override
    protected void onCancelled() {
        super.onCancelled();
        applicationContext = null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        applicationContext = null;

    }

    @Override
    protected void onProgressUpdate(String... values) { //Method used to update the UI!
        super.onProgressUpdate(values);

        Intent progressIntent = new Intent(TASK_FILTER);
        progressIntent.putExtra(UPDATE_KEY, values[0]);
        applicationContext.sendBroadcast(progressIntent);

    }
}
