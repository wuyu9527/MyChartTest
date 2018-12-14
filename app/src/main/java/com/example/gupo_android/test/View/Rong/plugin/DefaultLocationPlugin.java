//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.gupo_android.test.View.Rong.plugin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.gupo_android.test.R;
import com.example.gupo_android.test.Util.PermissionCheckUtil;
import com.example.gupo_android.test.View.RongExtension;



public class DefaultLocationPlugin implements IPluginModule, IPluginRequestPermissionResultCallback {
    public DefaultLocationPlugin() {
    }

    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.rc_ext_plugin_location_selector);
    }

    public String obtainTitle(Context context) {
        return context.getString(R.string.rc_plugin_location);
    }

    public void onClick(Fragment currentFragment, RongExtension extension) {
        String[] permissions = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_NETWORK_STATE"};
        if (PermissionCheckUtil.checkPermissions(currentFragment.getActivity(), permissions)) {
            this.startLocationActivity(currentFragment, extension);
        } else {
            extension.requestPermissionForPluginResult(permissions, 255, this);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    private void startLocationActivity(Fragment fragment, RongExtension extension) {
        //跳地图
//        Intent intent = new Intent(fragment.getActivity(), AMapLocationActivity.class);
//        extension.startActivityForPluginResult(intent, 1, this);
    }

    public boolean onRequestPermissionResult(Fragment fragment, RongExtension extension, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PermissionCheckUtil.checkPermissions(fragment.getActivity(), permissions)) {
            this.startLocationActivity(fragment, extension);
        } else {
            extension.showRequestPermissionFailedAlter(PermissionCheckUtil.getNotGrantedPermissionMsg(fragment.getActivity(), permissions, grantResults));
        }

        return true;
    }
}
