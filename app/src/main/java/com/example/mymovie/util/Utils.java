package com.example.mymovie.util;

import android.util.Log;

public class Utils {

    private static final boolean loggingDebug = false;
    private static final boolean loggingError = true;
    private static final boolean loggingInfo = true;
    private static final boolean loggingWarn = false;

    public static final String languagePattern = "([a-z]{2})-([A-Z]{2})";
    public static final String regionPattern = "^[A-Z]{2}$";

    public static boolean pageAuth(int page){
        if (page > 0 && page <= 1000){
            return true;
        } else {
            return false;
        }
    }

    public static void loggingDebug(String title, String message) {
        if (loggingDebug) {
            Log.d(title, "==== Debug Message : " + message);
        }
    }

    public static void loggingError(String title, String message) {
        if (loggingError) {
            Log.e(title, "==== Error Message : " + message);
        }
    }

    public static void loggingInfo(String title, String message) {
        if (loggingInfo) {
            Log.i(title, "==== Info Message : " + message);
        }
    }

    public static void loggingWarn(String title, String message) {
        if (loggingWarn) {
            Log.d(title, "==== Warn Message : " + message);
        }
    }
}
