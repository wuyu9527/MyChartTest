//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.gupo_android.test.View.Rong.plugin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.example.gupo_android.test.View.RongExtension;


public interface IPluginModule {
    Drawable obtainDrawable(Context var1);

    String obtainTitle(Context var1);

    void onClick(Fragment var1, RongExtension var2);

    void onActivityResult(int var1, int var2, Intent var3);
}
