package com.bigbang.multithreadingapplication.util;

import android.util.Log;

import static com.bigbang.multithreadingapplication.util.Constants.DEBUG_TAG;
import static com.bigbang.multithreadingapplication.util.Constants.ERROR_PREFIX;
import static com.bigbang.multithreadingapplication.util.Constants.ERROR_TAG;

public class DebugLogger {

    public static void logError(Exception e) {
        Log.d(ERROR_TAG, ERROR_PREFIX + e.getLocalizedMessage());
    }

    public static void logDebug(String debugMessage) {
        Log.d(DEBUG_TAG, debugMessage);
    }

}
