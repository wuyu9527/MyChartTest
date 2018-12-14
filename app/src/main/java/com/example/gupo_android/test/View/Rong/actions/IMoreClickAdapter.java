//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.gupo_android.test.View.Rong.actions;

import android.support.v4.app.Fragment;
import android.view.ViewGroup;

import com.example.gupo_android.test.View.Rong.IClickActions;

import java.util.List;

public interface IMoreClickAdapter {
    void bindView(ViewGroup var1, Fragment var2, List<IClickActions> var3);

    void hideMoreActionLayout();

    void setMoreActionEnable(boolean var1);

    boolean isMoreActionShown();
}
