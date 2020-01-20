package com.example.player.video;

import android.util.Log;

import com.example.player.BuildConfig;

public class Utils {
    static void printIfDebug(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }
}
