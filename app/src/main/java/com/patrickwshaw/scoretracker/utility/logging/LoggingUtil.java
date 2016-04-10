package com.patrickwshaw.scoretracker.utility.logging;

import android.util.Log;

/**
 * Created by Patrick on 1/3/2015.
 */
public class LoggingUtil
{
    private String TAG;
    private String className;

    //no one should call the default constructor
    private LoggingUtil(){};

    public LoggingUtil(String TAG, String className)
    {
        this.TAG = TAG;
        this.className = className;
    }

    public void logEnter(String methodName)
    {
        Log.d(TAG, className + " - " + methodName + " - Enter");
    }

    public void logExit(String methodName)
    {
        Log.d(TAG, className + " - " + methodName + " - Exit");
    }

    public void logInnerClassEnter(String innerClassName, String methodName)
    {
        Log.d(TAG, className + " - " + innerClassName + " - " + methodName + " - Enter");
    }

    public void logInnerClassExit(String innerClassName, String methodName)
    {
        Log.d(TAG, className + " - " + innerClassName + " - " + methodName + " - Exit");
    }

    public void debug(String message)
    {
        Log.d(TAG, message);
    }

    public void info(String message)
    {
        Log.i(TAG, message);
    }

    public void warn(String message)
    {
        Log.w(TAG, message);
    }

    public void error(String message)
    {
        Log.e(TAG, message);
    }

    public void d(String message)
    {
        debug(message);
    }

    public void i(String message)
    {
        info(message);
    }

    public void w(String message)
    {
        warn(message);
    }

    public void e(String message)
    {
        error(message);
    }
}
