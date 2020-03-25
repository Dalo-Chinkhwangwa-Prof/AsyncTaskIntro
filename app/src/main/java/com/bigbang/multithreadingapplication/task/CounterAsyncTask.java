package com.bigbang.multithreadingapplication.task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import static com.bigbang.multithreadingapplication.util.DebugLogger.logDebug;
import static com.bigbang.multithreadingapplication.util.DebugLogger.logError;

public class CounterAsyncTask extends AsyncTask<Integer, String, Boolean> {

    public static final String UPDATE_KEY = "UPDATE_KEY";
    public static final String COMPLETED_KEY = "COMPLETED_KEY";
    public static final String TASK_FILTER = "com.from.task";

    private Intent progressIntent = new Intent(TASK_FILTER);

    @SuppressLint("StaticFieldLeak")
    private Context applicationContext;

    private Handler handler;

    public CounterAsyncTask(Handler handler) {
        this.handler = handler;
    }

//    public CounterAsyncTask(Context applicationContext) {
//        this.applicationContext = applicationContext;
//    }

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

//        Intent complete = new Intent(TASK_FILTER);
//        complete.putExtra(COMPLETED_KEY, aBoolean);
//        applicationContext.sendBroadcast(complete);
//
//        applicationContext = null;

    }

    @Override
    protected void onProgressUpdate(String... values) { //Method used to update the UI!
        super.onProgressUpdate(values);

        Message updateMessage = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(UPDATE_KEY, values[0]);
        updateMessage.setData(bundle);
        handler.sendMessage(updateMessage);
//        progressIntent.putExtra(UPDATE_KEY, values[0]);
//        applicationContext.sendBroadcast(progressIntent);

    }
}
