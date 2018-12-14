//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.gupo_android.test.View.Rong.common;

import android.util.Log;

public class RLog {
    private static final String TAG = "RongLog";

    public RLog() {
    }

    public static int v(String tag, String msg) {
        return Log.v("RongLog", "[ " + tag + " ] " + msg);
    }

    public static int d(String tag, String msg) {
        return Log.d("RongLog", "[ " + tag + " ] " + msg);
    }

    public static int i(String tag, String msg) {
        return Log.i("RongLog", "[ " + tag + " ] " + msg);
    }

    public static int w(String tag, String msg) {
        return Log.w("RongLog", "[ " + tag + " ] " + msg);
    }

    public static int e(String tag, String msg) {
        return Log.e("RongLog", "[ " + tag + " ] " + msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        return Log.e("RongLog", "[ " + tag + " ] " + msg, tr);
    }
}
