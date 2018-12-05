package com.example.gupo_android.test.App.init;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import java.util.List;

/**
 * Activity生命周期监听
 */
public class MyActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private static volatile MyActivityLifecycle instance;
    private String userId = "";

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static MyActivityLifecycle instance() {
        MyActivityLifecycle f = instance;
        if (instance == null) {
            Class var1 = MyActivityLifecycle.class;
            synchronized (MyActivityLifecycle.class) {
                f = instance;
                if (instance == null) {
                    f = new MyActivityLifecycle();
                    instance = f;
                }
            }
        }
        return f;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
//        if (!TextUtils.isEmpty(userId)
//                && !Constants.NO_PROTECT.contains(activity.getLocalClassName())
//                ) {
//            long protectSeconds = SPUtils.getInstance().getLong(userId + Constants.PROTECT_SECONDS) == -1L ? System.currentTimeMillis() : SPUtils.getInstance().getLong(userId + Constants.PROTECT_SECONDS);
//            int seconds = (int) ((System.currentTimeMillis() - protectSeconds) / 1000);
//            //Log.i("whx", "seconds:" + seconds);
//            if (!TextUtils.isEmpty(SPUtils.getInstance().getString(userId + Constants.password))
//                    && seconds >= Constants.APP_PROTECT_SECONDS
//                    ) {
//                ProtectAppActivity.startActivity(activity);
//            } else {
//                SPUtils.getInstance().put(userId + Constants.PROTECT_SECONDS, -1L);
//            }
//        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
//        if (!TextUtils.isEmpty(userId)
//                && !Constants.NO_PROTECT.contains(activity.getLocalClassName())) {
//            //app 进入后台
//            if (!isAppOnForeground(activity)) {
//                //Log.i("whx", activity.getLocalClassName() + "进入后台");
//                SPUtils.getInstance().put(userId + Constants.PROTECT_SECONDS, System.currentTimeMillis());
//            }
//        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        //Log.i("whx", "onActivityDestroyed:"+activity.getLocalClassName());

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground(Activity activity) {
        ActivityManager activityManager = (ActivityManager) activity.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = activity.getApplicationContext().getPackageName();
        assert activityManager != null;
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }
}
