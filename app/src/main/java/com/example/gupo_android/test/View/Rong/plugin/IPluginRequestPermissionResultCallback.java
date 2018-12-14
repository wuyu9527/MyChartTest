//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.gupo_android.test.View.Rong.plugin;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.gupo_android.test.View.RongExtension;


public interface IPluginRequestPermissionResultCallback {
    int REQUEST_CODE_PERMISSION_PLUGIN = 255;

    boolean onRequestPermissionResult(Fragment var1, RongExtension var2, int var3, @NonNull String[] var4, @NonNull int[] var5);
}
